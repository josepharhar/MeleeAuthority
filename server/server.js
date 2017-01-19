var express = require('express');
var reactViews = require('express-react-views');
var morgan = require('morgan');

var characters = require('./json/characters.json');
var attribute = require('./json/attributes.json');
var character_attributes = require('./json/character_attributes.json');
var animations = require('./json/animations.json');

var app = express();
app.use(morgan('dev'));
app.set('view engine', 'jsx');
app.engine('jsx', reactViews.createEngine());

app.use('/static', express.static('static'));

app.get('/', function(req, res) {
  res.redirect('/characters');
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
    attributes: character_attributes[charId],
    animations: animations[charId]
  });
});

var findAnimation = function(animations, animationId) {
  for (var i = 0; i < animations.length; i++) {
    if (animations[i].internalName == animationId) {
      return animations[i];
    }
  }
  return null;
};

app.get('/characters/:charId/:animation', function(req, res) {
  var charId = req.params.charId;
  if (!characters.hasOwnProperty(charId)) {
    // TODO return 404
    console.log('could not find charId "' + charId + '"');
    return;
  }

  var animation = findAnimation(animations[charId], req.params.animation);
  if (animation == null) {
    // TODO return 404
    console.log('could not find animation "' + req.params.animation + '"');
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
