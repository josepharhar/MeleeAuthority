const express = require('express');
const reactViews = require('express-react-views');
const morgan = require('morgan');
const fs = require('fs');

const charIdToName = require('./json/charIdToName.json');
const attributeDefinitions = require('./json/attributeDefinitions.json');
const attributes = require('./json/attributes.json');
const animations = require('./json/animations.json');
const attributeKeys = require('./json/attributeKeys.json');

const spawn = require('child_process').spawn;
spawn('babel', ['client-src', '-d', 'client-build', '--watch']);

const app = express();
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
  const charId = req.params.charId;
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
    charIdToName: charIdToName
  });
});

app.get('/characters/:charId', function(req, res) {
  //const charId = req.params.charId.toLowerCase();
  const charId = req.params.charId;
  if (!charIdToName.hasOwnProperty(charId)) {
    // TODO return 404 or something
    console.log('could not find charId "' + charId + '"');
    return;
  }

  res.render('CharacterLayout', {
    charId: charId,
    charName: charIdToName[charId],
    attributes: attributes[charId],
    animations: animations[charId],
    attribute_definitions: attributeDefinitions
  });
});

const findAnimation = function(animations, subActionId) {
  for (var i = 0; i < animations.length; i++) {
    if (animations[i].subActionId == subActionId) {
      return animations[i];
    }
  }
  return null;
};

app.get('/characters/:charId/:subActionId', function(req, res) {
  const charId = req.params.charId;
  if (!charIdToName.hasOwnProperty(charId)) {
    // TODO return 404
    console.log('could not find charId "' + charId + '"');
    return;
  }

  const animation = findAnimation(animations[charId], req.params.subActionId);
  if (animation == null) {
    // TODO return 404
    console.log('could not find subAction "' + req.params.subActionId + '" for character "' + charId + '"');
    return;
  }

  res.render('AnimationLayout', {
    charName: charIdToName[charId],
    animation: animation
  });
});

app.listen(process.env.PORT || 8080, function() {
  console.log('server started');
});
