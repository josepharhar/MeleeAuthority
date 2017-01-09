var express = require('express');
var reactViews = require('express-react-views');
var morgan = require('morgan');
var mysql = require('mysql');

var connection = mysql.createConnection({
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
        var charInfo = results[0];
        var attributes = results[0];
        res.render('CharacterLayout', {
          character: results[0][0].fullName,
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
    /*connection.query(
    "SELECT * FROM Characters WHERE id = '" + charId + "';"
      + "SELECT * FROM Animations WHERE internalName = '" + animation + "';"
      + "SELECT * FROM */
});

app.listen(process.env.PORT || 8080, function() {
  console.log('server started');
});
