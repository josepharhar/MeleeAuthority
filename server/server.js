var express = require('express');
var reactViews = require('express-react-views');
var morgan = require('morgan');
var mysql = require('mysql');

var connection = mysql.createConnection({
  host: 'localhost',
  user: 'jarhar',
  password: 'jarhar',
  database: 'melee',
  multipleStatements: true
});
connection.connect(function(err) {
  if (err) {
    console.log('error connecting to DB: ' + err.stack);
  } else {
    console.log('successfully connected to DB with threadId: ' + connection.threadId);
  }
});

var app = express();
app.use(morgan('dev'));
app.set('view engine', 'jsx');
app.engine('jsx', reactViews.createEngine());

app.use('/static', express.static('static'));

app.get('/', function(req, res) {
  res.redirect('/characters');
});

app.get('/characters', function(req, res) {
  connection.query("SELECT * FROM Characters", function(err, results) {
    if (err) {
      console.log('query error: ' + err.stack);
    } else {
      // TODO make a react component for this page
      // it should probably use another "layout" component for all pages
      res.render('CharactersListLayout', {
        characters: results
      });
    }
  });
});

app.get('/characters/:charId', function(req, res) {
  var charId = req.params.charId.toLowerCase();
  // TODO what if there is no view for character?
  // TODO sql input cleaning?
  connection.query(
    "SELECT * FROM Characters WHERE id = '" + charId + "';"
      + "SELECT * FROM CharacterAttributes WHERE id = '" + charId + "';"
      + "SELECT * FROM Animations WHERE charId = '" + charId + "'",
    function(err, results) {
      if (err) {
        console.log('query error: ' + err.stack);
      } else {
        res.render('CharacterLayout', {
          character: results[0][0],
          attributes: results[1][0],
          animations: results[2]
        });
      }
  });

  // TODO should res.render() be called here in case connection.query() times out or something?
  //res.render('character', { character: character });
});

app.get('/characters/:charId/:animation', function(req, res) {
  var charId = req.params.charId, animation = req.params.animation;
  // TODO input checking
  connection.query(
    // TODO remove case sensitivity from animation internalName for this query
    // TODO select only columns to be showed on webpage
    "SELECT * FROM Characters WHERE id = '" + charId + "';"
      + "SELECT H.groupId AS 'Group', H.hitboxId AS id, H.bone AS Bone, H.damage AS Damage,"
      + " H.zoffset AS 'Z Offset', H.yoffset AS 'Y Offset', H.xoffset AS 'X Offset',"
      + " H.angle AS Angle, H.knockbackScaling AS 'Knockback Scaling',"
      + " H.fixedKnockback AS 'Fixed Knockback', H.baseKnockback AS 'Base Knockback',"
      + " H.shieldDamage AS 'Shield Damage'"
      + " FROM Animations A JOIN Hitboxes H on H.charId = A.charId AND H.subActionId = A.subActionId"
      + " WHERE A.charId = '" + charId + "' AND A.internalName = '" + animation + "';"
      + "SELECT F.frame AS Frame, F.hitbox AS Hitbox, F.iasa AS IASA, F.autocancel AS Autocancel"
      + " FROM Animations A JOIN FrameStrips F on F.charId = A.charId AND F.subActionId = A.subActionId"
      + " WHERE A.charId = '" + charId + "' AND A.internalName = '" + animation + "';"
      + "SELECT * FROM Animations WHERE charId = '" + charId + "' AND internalName = '" + animation + "'",
    function(err, results) {
      if (err) {
        console.log('query error: ' + err.stack);
      } else {
        res.render('AnimationLayout', {
          character: results[0][0],
          hitboxes: results[1],
          frameStrip: results[2],
          animation: results[3][0]
        });
      }
  });
});

app.listen(process.env.PORT || 8080, function() {
  console.log('server started');
});
