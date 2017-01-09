var React = require('react');

var CharacterStats = React.createClass({
  render: function() {
    var properties = [];
    for (var key in this.props.stats) {
      var value = this.props.stats[key];
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

    return <table>{rows}</table>;
  }
});

module.exports = CharacterStats;
