var React = require('react');
var ButtonList = require('./ButtonList');
var HeadScripts = require('./HeadScripts');
var BodyScripts = require('./BodyScripts');

var CharacterAttributes = React.createClass({
  render: function() {
    var properties = [];
    for (var key in this.props.attributes) {
      var attributeDefinition = this.props.attribute_definitions[key];
      // TODO BASIC
      if (attributeDefinition.viewCategory == 'BASIC') {
        var value = this.props.attributes[key];
        properties.push({
          name: attributeDefinition.fullName,
          value: value
        });
      }
    }

    var rows = properties.map(function(row) {
      return (
        <tr>
          <td>{row.name}</td>
          <td>{row.value}</td>
        </tr>
      );
    });

    return <table className="table table-hover table-bordered"><tbody>{rows}</tbody></table>;
  }
});

var CharacterLayout = React.createClass({
  render: function() {
    var charId = this.props.charId;
    var buttons = this.props.animations.map(function(animation) {
      // TODO send all of these to the webpage and let it be filtered there instead
      console.log('viewCategory: ' + animation.description.viewCategory);
      if (animation.description.viewCategory == 'BASIC') {
        return {
          name: animation.description.description,
          link: '/characters/' + charId + '/' + animation.internalName
        };
      }
    });

    return (
      <html>
        <head>
          <title>{this.props.character}</title>
          <HeadScripts />
        </head>
        <body>
          <h1>{this.props.character}</h1>
          <CharacterAttributes
            attributes={this.props.attributes}
            attribute_definitions={this.props.attribute_definitions}/>
          <ButtonList buttons={buttons}/>
          <BodyScripts />
        </body>
      </html>
    );
  }
});

module.exports = CharacterLayout;
