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

    // filter out empty rows, Frame is always needed
    var keys = getKeys(frameStrip).filter(function(key) {
      if (key == 'Frame') {
        return true;
      }
      for (var i = 0; i < frameStrip.length; i++) {
        if (frameStrip[i][key] == '1') {
          return true;
        }
      }
      return false;
    });

    var MAX_TABLE_LENGTH = 25;
    var numTables = Math.ceil(frameStrip.length / MAX_TABLE_LENGTH);
    var tables = [];

    for (var i = 0; i < numTables; i++) {
      // for each key, create a row
      var rows = keys.map(function(key) {
        var row = [];
        for (var j = i * MAX_TABLE_LENGTH; j < frameStrip.length && j < (i + 1) * MAX_TABLE_LENGTH; j++) {
          row.push(frameStrip[j][key]);
        }

        row = row.map(function(value) {
          if (key == 'Frame') {
            return <th>{value + 1}</th>;
          } else if (value == '1') {
            return <td className="frame-strip-true"></td>;
          } else if (value == '0') {
            return <td></td>;
          } else {
            // this should never happen
            return <td>{value}</td>;
          }
        });

        return <tr><th>{key}</th>{row}</tr>;
      });

      tables.push(
        <table className="frame-strip table table-hover table-bordered">
          <tbody>{rows}</tbody>
        </table>
      );
    }

    return <div>{tables}</div>;
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
          <div className="container">
            <h1>{title}</h1>
            <div className="flex-container">
              <div className="flex-item">
                <h2>Hitboxes</h2>
                {hitboxTables}
              </div>
              <div className="flex-item">
                <h2>Frame Strip</h2>
                <FrameStripTable frameStrip={animation.frameStrip}/>
              </div>
              <div className="flex-item">
                <h2>Animation Commands</h2>
                <AnimationCommandsTable commands={animation.commands}/>
              </div>
            </div>
          </div>
          <BodyScripts />
        </body>
      </html>
    );
  }
}

module.exports = AnimationLayout;
