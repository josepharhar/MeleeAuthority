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
          <th>{row.name}</th>
          <td>{row.value}</td>
        </tr>
      );
    });

    return <table className="attribute-table table table-hover table-bordered"><tbody>{rows}</tbody></table>;
  }
});

var CharacterLayout = React.createClass({
  render: function() {
    var charId = this.props.charId;

    var categoriesToButtons = {};
    // TODO this is a hacky way of arbitrarily ordering the button categories
    categoriesToButtons['Aerial'] = [];
    categoriesToButtons['Smash'] = [];
    categoriesToButtons['Tilt'] = [];
    categoriesToButtons['Dodge'] = [];
    categoriesToButtons['Tech'] = [];
    categoriesToButtons['Ledge'] = [];

    this.props.animations.forEach(function(animation) {
      // TODO send all of these to the webpage and let it be filtered there instead
      if (animation.description.viewCategory == 'BASIC') {
        if (!categoriesToButtons[animation.description.category]) {
          categoriesToButtons[animation.description.category] = [];
        }

        categoriesToButtons[animation.description.category].push({
          name: animation.description.description,
          link: '/characters/' + charId + '/' + animation.subActionId
        });
      }
    });

    var buttons = Object.keys(categoriesToButtons).map(function(key) {
      return (
        <div>
          <h3>{key}</h3>
          <ButtonList buttons={categoriesToButtons[key]}/>
        </div>
      );
    });

    return (
      <html>
        <head>
          <title>{this.props.character}</title>
          <HeadScripts />
        </head>
        <body>
          <div className="container">
            <h1>{this.props.character}</h1>
            <div className="flex-container">
              <div className="flex-item">
                <h2>Attributes</h2>
                <CharacterAttributes
                  attributes={this.props.attributes}
                  attribute_definitions={this.props.attribute_definitions}/>
              </div>
              <div className="flex-item">
                {buttons}
              </div>
            </div>
          </div>
          <BodyScripts />
        </body>
      </html>
    );
  }
});

module.exports = CharacterLayout;
