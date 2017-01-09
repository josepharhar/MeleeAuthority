var React = require('react');

var AnimationLayout = React.createClass({

  render: function() {
    console.log('this.props.animation: ' + JSON.stringify(this.props.animation));

    var hitboxesTableHead = [];
    for (var key in this.props.animation[0]) {
      hitboxesTableHead.push(this.props.animation[0][key]);
    }
    hitboxesTableHead = hitboxesTableHead.map(function(entry) {
      return (
        <td>
      );
    });

    var hitboxesTableBody = this.props.animation.map(function(hitbox) {
      var columns = [];
      for (var key in hitbox) {
        columns.push(hitbox[key]);
      }
      columns = columns.map(function(entry) {
        return <td>{entry}</td>;
      });

      var header = [];
      for (var key in hitbox) {
        header.push(key);
      }
      header = header.map(function(entry) {
        return <td>{entry}</td>
      });

      return (
        <tr>{columns}</tr>
      );
    });

    return (
      <html>
        <head>
          <title>animationnamegoeshere</title>
        </head>
        <body>
          <table><tbody>{hitboxes}</tbody></table>
        </body>
      </html>
    );
  }
});

module.exports = AnimationLayout;
