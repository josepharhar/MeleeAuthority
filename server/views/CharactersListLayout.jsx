var React = require('react');
var ButtonList = require('./ButtonList');

var CharactersListLayout = React.createClass({
  render: function() {
    var buttons = this.props.characters.map(function(character) {
      return {
        name: character.fullName,
        link: '/characters/' + character.id
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
