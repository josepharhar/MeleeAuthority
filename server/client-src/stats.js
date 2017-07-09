class StatsTable extends React.Component {
  render() {
    const attributes = this.props.attributes;
    const characters = this.props.characters;
    const attributeDefinitions = this.props.attributeDefinitions;

    const attributeKeys = this.props.attributeKeys.filter(function(key) {
      return attributeDefinitions[key].viewCategory == 'BASIC';
    });

    const headerRow = attributeKeys.map(function(key) {
      return <th>{key}</th>;
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

    // TODO use class attribute-table?
    return (
      <table className='table table-hover table-bordered'>
        <thead>
          <tr>
            <th>Character</th>
            {headerRow}
          </tr>
        </thead>
        <tbody>{characterRows}</tbody>
      </table>
    );
  }
}

const urls = ['/json/attributes.json', '/json/attributeKeys.json', '/json/characters.json', '/json/attributeDefinitions.json'];

downloadJson(urls, function(jsons) {
  ReactDOM.render(<StatsTable
      attributes={jsons[0]}
      attributeKeys={jsons[1]}
      characters={jsons[2]}
      attributeDefinitions={jsons[3]} />,
      document.getElementById('stats-container'));
});
