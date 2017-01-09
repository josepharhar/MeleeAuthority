var React = require('react');
var ButtonList = require('./ButtonList');

var CharacterAttributes = React.createClass({
  render: function() {
    var properties = [];
    for (var key in this.props.attributes) {
      var value = this.props.attributes[key];
      properties.push({
        name: key,
        value: value
      });
    }

    var rows = properties.map(function(row) {
      return (
        <tr>
          <td>{row.name}</td>
          <td>{row.value}</td>
        </tr>
      );
    });

    return <table><tbody>{rows}</tbody></table>;
  }
});

var CharacterLayout = React.createClass({
  render: function() {
    var charId = this.props.character.id;
    var buttons = this.props.animations.map(function(animation) {
      // TODO (no description) should not be considered here, in java or something instead
      if (animation.description != '(No Description)') {
        return {
          name: animation.description,
          link: '/characters/' + charId + '/' + animation.internalName
        };
      }
    });

    return (
      <html>
        <head>
          <title>{this.props.character.fullName}</title>
        </head>
        <body>
          <h1>{this.props.character.fullName}</h1>
          <CharacterAttributes attributes={this.props.attributes}/>
          <ButtonList buttons={buttons}/>
        </body>
      </html>
    );
  }
});

module.exports = CharacterLayout;
