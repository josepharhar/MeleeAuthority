var React = require('react');

var toTableData = function(entries) {
  return entries.map(function(entry) {
    return <td>{entry}</td>;
  });
};

var HitboxesTable = React.createClass({
  render: function() {
    var tableHead = [];
    for (var key in this.props.animation[0]) {
      tableHead.push(key);
    }
    tableHead = toTableData(tableHead);

    var tableBody = this.props.animation.map(function(hitbox) {
      var columns = [];
      for (var key in hitbox) {
        columns.push(hitbox[key]);
      }
      columns = toTableData(columns);

      return (
        <tr>{columns}</tr>
      );
    });

    return (
      <table>
        <thead><tr>{tableHead}</tr></thead>
        <tbody>{tableBody}</tbody>
      </table>
    );
  }
});

var FrameStripTable = React.createClass({
  render: function() {
    return <p>framestriptable</p>;
  }
});

var AnimationLayout = React.createClass({
  render: function() {
    console.log('framestrip: ' + JSON.stringify(this.props.frameStrip));
    return (
      <html>
        <head>
          <title>animationnamegoeshere</title>
        </head>
        <body>
          <HitboxesTable animation={this.props.animation}/>
          <HitboxesTable animation={this.props.frameStrip}/>
        </body>
      </html>
    );
  }
});

module.exports = AnimationLayout;
