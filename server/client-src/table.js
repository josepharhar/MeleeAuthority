class Table extends React.Component {
  // this.props.columnIds = array of column ids in viewing order
  // this.props.columnNames = map of column ids to column names
  // this.props.columnValues = map of entry id to map of column ids to values (entry id = charId)
  //
  // ?
  // this.props.entryIdToName = map of entry id to visible entry name (first column)
  //
  // ?
  // this.props.entryColumnName = name of entry name type (Character)
  //
  // this.state.sortColumnId = column id to sort by
  // this.state.sortIncreasing = direction to sort entries by
  // this.state.pinnedEntryIds = entry ids to be pinned at the top of the page
  //
  // ?
  // this.state.visibleEntryIds = entry ids which have not been filtered out
  constructor(props) {
    super(props);

    this.state = {
      sortColumnId: this.props.columnIds[0],
      sortIncreasing: true,
      pinnedEntryIds: []
    }
  }

  sort() {
    console.log('todo');
  }

  filter(regex) {
    console.log('todo');
  }

  // TODO make comparison function work for numbers and strings

  render() {
    const handleClick = this.sort.bind(this);
    const state = this.state;
    const props = this.props;
    const headerRow = this.props.columnIds.map(function(columnId) {
      return <th onClick={handleClick} id={columnId}>{props.columnNames[columnId]}</th>;
      //return <th onClick={handleClick} id={columnId}>{columnId}</th>;
    });

    const rows = Object.keys(props.columnValues).map(function(entryId) {
      const row = props.columnIds.map(function(columnId) {
        return <td>{props.columnValues[entryId][columnId]}</td>;
      });
      //return <tr><td>{entryId}</td>{row}</tr>;
      return <tr>{row}</tr>;
    });

    // TODO add className attribute-table for monospacing?
    return (
      <table className='table table-hover table-bordered'>
        <thead><tr>{headerRow}</tr></thead>
        <tbody>{rows}</tbody>
      </table>
    );
  }
}
