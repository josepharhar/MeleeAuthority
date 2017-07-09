class StatsTable extends React.Component {
  getDefaultCharIds() {
    return Object.keys(this.props.attributes);
  }

  constructor(props) {
    super(props);
    this.state = { charIds: this.getDefaultCharIds() };
    this.handleSortClick = this.handleSortClick.bind(this);
  }

  createCharacterRows(charIds) {
    const attributeDefinitions = this.props.attributeDefinitions;
    const attributes = this.props.attributes;
    const characters = this.props.characters;
    const attributeKeys = this.filteredAttributeKeys();

    return charIds.map(function(charId) {
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
  }

  handleSortClick(e) {
    const attribute = e.target.innerText;
    // need to create a mapping between charId and this category,
    // then sort charIds based on values of this category
    const defaultCharIds = this.getDefaultCharIds();
    const attributes = this.props.attributes;

    const compareCharAndValue = function(a, b) {
      return a.value - b.value;
    };

    const sortedCharIds = defaultCharIds.map(function(charId) {
      return {
        charId: charId,
        value: attributes[charId][attribute]
      }
    })
    .sort(compareCharAndValue)
    .map(function(charAndValue) {
      return charAndValue.charId;
    });

    this.setState({
      charIds: sortedCharIds
    });
  }

  filteredAttributeKeys() {
    const attributeDefinitions = this.props.attributeDefinitions;
    return this.props.attributeKeys.filter(function(key) {
      return attributeDefinitions[key].viewCategory == 'BASIC';
    });
  }

  render() {
    const handleClick = this.handleSortClick;
    const headerRow = this.filteredAttributeKeys().map(function(key) {
      return <th onClick={handleClick}>{key}</th>;
    });

    const characterRows = this.createCharacterRows(this.state.charIds);

    // TODO use class attribute-table?
    return (
      <table className='table table-hover table-bordered'>
        <thead>
          <tr>
            <th onClick={handleClick}>Character</th>
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
