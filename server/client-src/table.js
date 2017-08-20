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
  /*filter(regex) {
    console.log('todo');
  }*/

  handleColumnClick(e) {
    const newSortColumnId = e.target.id;
    if (this.state.sortColumnId == newSortColumnId) {
      this.state.sortIncreasing = !this.state.sortIncreasing;
    } else {
      this.state.sortColumnId = e.target.id;
    }
    this.setState(this.state);
  }

  // TODO make comparison function work for numbers and strings

  render() {
    const state = this.state;
    const props = this.props;
    const handleColumnClick = this.handleColumnClick.bind(this);

    const headerRow = this.props.columnIds.map((columnId) => {
      var columnText = props.columnNames[columnId];
      if (columnId == state.sortColumnId) {
        if (state.sortIncreasing) {
          columnText += ' \u25B2';
        } else {
          columnText += ' \u25BC';
        }
      }
      return <th onClick={handleColumnClick} id={columnId}>{columnText}</th>;
    });

    const rows = Object.keys(props.columnValues)
      .sort((entryIdOne, entryIdTwo) => {
        const valueOne = props.columnValues[entryIdOne][state.sortColumnId];
        const valueTwo = props.columnValues[entryIdTwo][state.sortColumnId];
        if (this.state.sortIncreasing) {
          if (valueOne.localeCompare) {
            return valueOne.localeCompare(valueTwo);
          }
          return valueOne - valueTwo;
        }
        if (valueTwo.localeCompare) {
          return valueTwo.localeCompare(valueTwo);
        }
        return valueTwo - valueOne;
      })
      .map((entryId) => {
        const row = props.columnIds.map((columnId) => {
          return <td>{props.columnValues[entryId][columnId]}</td>;
        });
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
