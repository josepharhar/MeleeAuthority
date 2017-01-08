var React = require('react');
var ReactDOM = require('react-dom');

var Character = React.createClass({
  render: function() {
    return (
      <html>
        <head>
          <title>{this.props.character}</title>
        </head>
        <body>
          <p>This is the character page for {this.props.character}</p>
        </body>
      </html>
    );
  }
});

module.exports = Character;
