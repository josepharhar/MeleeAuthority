var express = require('express');
var reactViews = require('express-react-views');
var morgan = require('morgan');
var mysql = require('mysql');

var connection = mysql.createConnection({
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

app.get('/characters/:charId', function(req, res) {
  var charId = req.params.charId.toLowerCase();
  // TODO what if there is no view for character?
  // TODO sql input cleaning?
  var query = 'SELECT * FROM Characters WHERE id = \'' + charId + '\'';
  connection.query(query, function(infoErr, infoResults) {
    if (infoErr) {
      console.log('query error: ' + err.stack);
    } else {
      query = 'SELECT * FROM CharacterAttributes WHERE id = \'' + charId + '\'';
      connection.query(query, function(attributesErr, attributesResults) {
        if (attributesErr) {
          console.log('query error: ' + err.stack);
        } else {
          console.log('infoResults: ' + JSON.stringify(infoResults));
          console.log('attributesResults: ' + JSON.stringify(attributesResults));
          res.render('CharacterLayout', {
            character: infoResults[0].fullName,
            attributes: attributesResults[0]
          });
        }
      });
    }
  });

  // TODO should res.render() be called here in case connection.query() times out or something?
  //res.render('character', { character: character });
});

app.listen(process.env.PORT || 8080, function() {
  console.log('server started');
});
