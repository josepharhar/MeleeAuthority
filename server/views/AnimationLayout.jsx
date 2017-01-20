var React = require('react');
var HeadScripts = require('./HeadScripts');
var BodyScripts = require('./BodyScripts');

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

class HitboxesTable extends React.Component {
  render() {
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
      <table className="attribute-table table table-hover table-bordered">
        <thead><tr>{tableHead}</tr></thead>
        <tbody>{tableBody}</tbody>
      </table>
    );
  }
}

class FrameStripTable extends React.Component {
  render() {
    var frameStrip = this.props.frameStrip;
    var rows = getKeys(frameStrip).map(function(key) {
      // for each key, create a row
      var row = [];
      for (var i = 0; i < frameStrip.length; i++) {
        row.push(frameStrip[i][key]);
      }
      var rofl = row.map(function(entry) {
        if (key == 'Frame') {
          // TODO there should be a better way to do this
          return <th>{entry}</th>;
        }
        if (entry == '1') {
          return <td className="frame-strip-true"></td>;
        }
        if (entry == '0') {
          return <td></td>;
        }
        // this should never happen? or is this for frame numbers?
        return <td>{entry}</td>;
      });
      return <tr><th>{key}</th>{rofl}</tr>;
    });

    return (
      <table className="frame-strip table table-hover table-bordered">
        <tbody>{rows}</tbody>
      </table>
    );
  }
}

class AnimationCommandsTable extends React.Component {
  render() {
    // TODO this is the same as attribute table, put in common component

    var commands = this.props.commands;

    var properties = Object.keys(commands[0]);
    var rows = Object.keys(commands).map(function(key) {
      var row = properties.map(function(property) {
        return <td>{commands[key][property]}</td>;
      });
      return <tr>{row}</tr>;
    });

    var propertiesRow = properties.map(function(property) {
      return <th>{property}</th>;
    });

    return (
      <table className="attribute-table table table-hover table-bordered">
        <thead>{propertiesRow}</thead>
        <tbody>{rows}</tbody>
      </table>
    );
  }
}

class AnimationLayout extends React.Component {
  render() {
    var animation = this.props.animation;
    console.log('animation: ' + Object.keys(animation));
    var title = this.props.character + ' - ' + animation.description.description;

    var hitboxTables = Object.keys(animation.frameToHitboxes).map(function(frame) {
      // TODO decide what to do with multiple hitbox groups, like color each differently
      return <HitboxesTable hitboxes={animation.frameToHitboxes[frame]}/>;
    });

    return (
      <html>
        <head>
          <title>{title}</title>
          <HeadScripts />
        </head>
        <body>
          <h1>{title}</h1>
          <h2>Hitboxes</h2>
          {hitboxTables}
          <h2>Frame Strip</h2>
          <FrameStripTable frameStrip={animation.frameStrip}/>
          <h2>Animation Commands</h2>
          <AnimationCommandsTable commands={animation.commands}/>
          <BodyScripts />
        </body>
      </html>
    );
  }
}

module.exports = AnimationLayout;
