var React = require('react');
var CharacterStats = require('./CharacterStats');

var CharacterLayout = React.createClass({
  render: function() {
    return (
      <html>
        <head>
          <title>{this.props.character}</title>
        </head>
        <body>
          <h1>{this.props.character}</h1>
          <CharacterStats stats={this.props.attributes}/>
        </body>
      </html>
    );
  }
});

module.exports = CharacterLayout;
