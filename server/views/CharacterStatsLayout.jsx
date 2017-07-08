var React = require('react');
var HeadScripts = require('./HeadScripts');
var BodyScripts = require('./BodyScripts');

class StatsTable extends React.Component {
  render() {
    const attributes = this.props.attributes;
    const characters = this.props.characters;
    const attributeDefinitions = this.props.attributeDefinitions;

    const attributeKeys = Object.keys(attributeDefinitions).filter(function(key) {
      return attributeDefinitions[key].viewCategory == 'BASIC';
    });

    console.log('attributeKeys: ' + JSON.stringify(attributeKeys));

    const headerRow = attributeKeys.map(function(key) {
      return (
        <th>{key}</th>
      );
    });

    const characterRows = Object.keys(attributes).map(function(charId) {
      const row = attributeKeys.map(function(key) {
        return <td>{attributes[charId][key]}</td>;
      });

      return (
        <tr>
          <td>{characters[charId]}</td>
          {row}
        </tr>
      );
    });

    return (
      <table>
        <tr>
          <th>Character</th>
          {headerRow}
        </tr>
        {characterRows}
      </table>
    );
  }
}

class CharacterStatsLayout extends React.Component {
  render() {
    return (
      <html>
        <head>
          <title>Character Stats</title>
          <HeadScripts />
        </head>
        <body>
          <div className="container">
            <h1>Character Stats</h1>
            <StatsTable attributes={this.props.attributes}
              characters={this.props.characters}
              attributeDefinitions={this.props.attributeDefinitions}/>
          </div>
          <BodyScripts />
          <script src="//cdnjs.cloudflare.com/ajax/libs/list.js/1.5.0/list.min.js"></script>
          <script src="/static/character-stats.js"></script>
        </body>
      </html>
    );
  }
}

module.exports = CharacterStatsLayout;
