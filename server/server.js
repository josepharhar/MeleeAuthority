var express = require('express');
var babel = require('babel-core');
var reactViews = require('express-react-views');
var morgan = require('morgan');

var app = express();
app.use(morgan('dev'));
app.set('view engine', 'jsx');
app.engine('jsx', reactViews.createEngine());

app.use('/static', express.static('static'));

app.get('/characters/:character', function(req, res) {
  var character = req.params.character;
  // TODO what if there is no view for character?
  res.render('character', { character: character });
});

app.listen(process.env.PORT || 8080, function() {
  console.log('server started');
});
