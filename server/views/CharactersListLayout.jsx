var React = require('react');
var ButtonList = require('./ButtonList');

var CharactersListLayout = React.createClass({
  render: function() {
    var characters = this.props.characters;
    var buttons = Object.keys(characters).map(function(charId) {
      return {
        name: characters[charId],
        link: '/characters/' + charId
      };
    });

    return (
      <html>
        <head>
          <title>Characters List</title>
        </head>
        <body>
          <ButtonList buttons={buttons}/>
        </body>
      </html>
    );
  }
});

module.exports = CharactersListLayout;
