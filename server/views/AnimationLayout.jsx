const React = require('react');
const Layout = require('./Layout');

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
        // TODO make this not rely on 'angle' literal
        if (key == 'angle') {
          var no_padding_style = { padding: '0px' };
          columns.push(
            <td className='angle-td' style={no_padding_style}>
              <canvas className='angle-canvas' width="32" height="32" data-angle={hitbox[key]} />
            </td>
          );
        } else {
          columns.push(
            <td>{hitbox[key]}</td>
          );
        }
      }

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

class StatsTable extends React.Component {
  render() {
    var stats = this.props.stats;
    var variableToLabel = { // TODO delet this
      'frameCount': 'Total Frames',
      'subActionId': 'Sub Action Id',
      'internalName': 'Sub Action Name',
      'motherCommand.undefined0x10': 'motherCommand.undefined0x10',
      'motherCommand.undefined0x14': 'motherCommand.undefined0x14',
      'ajDataHeader.undefined0x10': 'ajDataHeader.undefined0x10',
      'ajDataHeader.undefined0x14': 'ajDataHeader.undefined0x14',
      'ajDataHeader.undefined0x18': 'ajDataHeader.undefined0x18',
      'ajDataHeader.undefined0x1C': 'ajDataHeader.undefined0x1C'
    };

    var asdf = Object.keys(stats).map(function(key) {
      if (variableToLabel.hasOwnProperty(key)) {
        key = variableToLabel[key];
      }
      return (
        <tr>
          <th>{key}</th>
          <td>{stats[key]}</td>
        </tr>
      );
    });

    return (
      <table className="attribute-table table table-hover table-bordered">
        <tbody>
          {asdf}
        </tbody>
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
      <Layout title={title}>
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
          <div className="flex-item">
            <h2>Stats</h2>
            <StatsTable stats={animation.stats}/>
          </div>
        </div>
        <script src='/static/animation.js'></script>
      </Layout>
    );
  }
}

module.exports = AnimationLayout;
