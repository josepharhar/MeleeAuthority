var express = require('express');
var reactViews = require('express-react-views');
var morgan = require('morgan');
var babel = require('babel-core');
var fs = require('fs');

var characters = require('./json/characters.json');
var attributeDefinitions = require('./json/attributeDefinitions.json');
var attributes = require('./json/attributes.json');
var animations = require('./json/animations.json');
var attributeKeys = require('./json/attributeKeys.json');

var app = express();
app.use(morgan('dev'));
app.set('view engine', 'jsx');
app.engine('jsx', reactViews.createEngine());

app.use('/static', express.static('static'));
app.use('/json', express.static('json'));
app.use('/client-build', express.static('client-build'));

app.get('/', function(req, res) {
  res.render('HomeLayout');
});

app.get('/character-stats', function(req, res) {
  res.render('CharacterStatsLayout', {});
});

app.get('/moves-table', function(req, res) {
  res.render('MovesTableLayout', {});
});

app.get('/character-table/:charId', function(req, res) {
  var charId = req.params.charId;
  if (!character.hasOwnProperty(charId)) {
    // TODO return 404 or something
    console.log('could not find charId "' + charId + '"');
    return;
  }

  res.render('CharacterTableLayout', {
    charId: charId
  });
});

app.get('/characters', function(req, res) {
  res.render('CharactersListLayout', {
    characters: characters
  });
});

app.get('/characters/:charId', function(req, res) {
  //var charId = req.params.charId.toLowerCase();
  var charId = req.params.charId;
  if (!characters.hasOwnProperty(charId)) {
    // TODO return 404 or something
    console.log('could not find charId "' + charId + '"');
    return;
  }

  res.render('CharacterLayout', {
    charId: charId,
    character: characters[charId],
    attributes: attributes[charId],
    animations: animations[charId],
    attribute_definitions: attributeDefinitions
  });
});

var findAnimation = function(animations, subActionId) {
  for (var i = 0; i < animations.length; i++) {
    if (animations[i].subActionId == subActionId) {
      return animations[i];
    }
  }
  return null;
};

app.get('/characters/:charId/:subActionId', function(req, res) {
  var charId = req.params.charId;
  if (!characters.hasOwnProperty(charId)) {
    // TODO return 404
    console.log('could not find charId "' + charId + '"');
    return;
  }

  var animation = findAnimation(animations[charId], req.params.subActionId);
  if (animation == null) {
    // TODO return 404
    console.log('could not find subAction "' + req.params.subActionId + '" for character "' + charId + '"');
    return;
  }

  res.render('AnimationLayout', {
    character: characters[charId],
    animation: animation
  });
});

app.listen(process.env.PORT || 8080, function() {
  console.log('server started');
});
