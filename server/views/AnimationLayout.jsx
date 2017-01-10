var React = require('react');
var HeadScripts = require('./HeadScripts');
var BodyScripts = require('./BodyScripts');

var frameStripStyleTrue = {
  'padding': '1px',
  'background-color': 'red'
};

var frameStripStyleFalse = {
  'padding': '1px'
};

var toTableHead = function(entries) {
  return entries.map(function(entry) {
    return <th>{entry}</th>;
  });
};

var toTableData = function(entries) {
  return entries.map(function(entry) {
    return <td>{entry}</td>;
  });
};

var getKeys = function(tuples) {
  var keys = [];
  if (tuples.length < 1) {
    // this should never happen
    return keys;
  }
  for (var key in tuples[0]) {
    keys.push(key);
  }
  return keys;
}

var HitboxesTable = React.createClass({
  render: function() {
    var tableHead = toTableHead(getKeys(this.props.hitboxes));

    var tableBody = this.props.hitboxes.map(function(hitbox) {
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
      <table className="table table-hover table-bordered">
        <thead><tr>{tableHead}</tr></thead>
        <tbody>{tableBody}</tbody>
      </table>
    );
  }
});

var FrameStripTable = React.createClass({
  render: function() {
    var frameStrip = this.props.frameStrip;
    // turn columns into rows
    var keys = getKeys(frameStrip);

    var asdf = keys.map(function(key) {
      // for each key, create a row
      var row = [];
      for (var i = 0; i < frameStrip.length; i++) {
        row.push(frameStrip[i][key]);
      }
      var rofl = row.map(function(entry) {
        return <td>{entry}</td>;
      });
      return (
        <tr>
          <th>{key}</th>
          {rofl}
        </tr>
      );
    });

    var style = {
      'font-size': '12px',
      'cellpadding': '0',
      'cellspacing': '0',
      'padding': '0',
      'margin': '0',
      'border-collapse': 'collapse'
    };
    return (
      <table className="table table-hover table-bordered" style={style}>
        <tbody>{asdf}</tbody>
      </table>
    );
  }
});

var AnimationLayout = React.createClass({
  render: function() {
    console.log('animation: ' + JSON.stringify(this.props.animation));
    var title = this.props.character.fullName + ' - ' + this.props.animation.description;
    return (
      <html>
        <head>
          <title>{title}</title>
          <HeadScripts />
        </head>
        <body>
          <h1>{title}</h1>
          <h2>Hitboxes</h2>
          <HitboxesTable hitboxes={this.props.hitboxes}/>
          <h2>Frame Strip</h2>
          <FrameStripTable frameStrip={this.props.frameStrip}/>
          <BodyScripts />
        </body>
      </html>
    );
  }
});

module.exports = AnimationLayout;
