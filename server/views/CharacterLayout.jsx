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
    var animations = this.props.animations.map(function(animation) {
      if (animation.description != '(No Description') {
        return animation.description;
      }
    });
    return (
      <html>
        <head>
          <title>{this.props.character}</title>
        </head>
        <body>
          <h1>{this.props.character}</h1>
          <CharacterAttributes attributes={this.props.attributes}/>
          <ButtonList animations={animations}/>
        </body>
      </html>
    );
  }
});

module.exports = CharacterLayout;
