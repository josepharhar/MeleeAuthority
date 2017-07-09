class StatsTable extends React.Component {
  getDefaultCharIds() {
    return Object.keys(this.props.attributes);
  }

  constructor(props) {
    super(props);
    this.state = {
      charIds: this.getDefaultCharIds(),
      sortedAttribute: '',
      sortedIncreasing: false
    };
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
    const attribute = e.target.id;
    // need to create a mapping between charId and this category,
    // then sort charIds based on values of this category
    const defaultCharIds = this.getDefaultCharIds();
    const attributes = this.props.attributes;

    var increasing = true;
    if (this.state.sortedAttribute == attribute) {
      increasing = !this.state.sortedIncreasing;
    }

    const compareCharAndValue = function(increasing, a, b) {
      if (increasing) {
        return b.value - a.value;
      } else {
        return a.value - b.value;
      }
    };
    const compareFunc = compareCharAndValue.bind(this, increasing);

    const sortedCharIds = defaultCharIds.map(function(charId) {
      return {
        charId: charId,
        value: attributes[charId][attribute]
      }
    })
    .sort(compareFunc)
    .map(function(charAndValue) {
      return charAndValue.charId;
    });

    this.setState({
      charIds: sortedCharIds,
      sortedAttribute: attribute,
      sortedIncreasing: increasing
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
    const state = this.state;
    const headerRow = ['charId'].concat(this.filteredAttributeKeys()).map(function(key) {
      var columnText = key;

      if (columnText == 'charId') {
        columnText = 'Character';
      }

      if (key == state.sortedAttribute) {
        if (state.sortedIncreasing) {
          columnText += ' \u25b2';
        } else {
          columnText += ' \u25bc';
        }
      }

      return <th onClick={handleClick} id={key}>{columnText}</th>;
    });

    const characterRows = this.createCharacterRows(this.state.charIds);

    // TODO use class attribute-table?
    return (
      <table className='table table-hover table-bordered'>
        <thead>
          <tr>{headerRow}</tr>
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
