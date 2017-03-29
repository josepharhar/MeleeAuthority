var React = require('react');
var ButtonList = require('./ButtonList');
var HeadScripts = require('./HeadScripts');
var BodyScripts = require('./BodyScripts');

var CharactersListLayout = React.createClass({
  render: function() {
    var characters = this.props.characters;
    var buttons = Object.keys(characters).sort().map(function(charId) {
      return {
        name: characters[charId],
        link: '/characters/' + charId
      };
    });

    return (
      <html>
        <head>
          <title>Characters List</title>
          <HeadScripts />
        </head>
        <body>
          <div className="container">
            <ButtonList buttons={buttons}/>
          </div>
          <BodyScripts />
        </body>
      </html>
    );
  }
});

module.exports = CharactersListLayout;
