// TODO add hover text/links to rows/values
class Table extends React.Component {
  // this.props.columnIds = array of column ids in viewing order
  // this.props.columnNames = map of column ids to column names
  // this.props.columnValues = map of entry id to map of column ids to values (entry id = charId)
  //
  // ?
  // this.props.nameOverrideColumns = array of column ids which have overriden values for their entries
  // this.props.entryIdToName = map of entry id to visible entry name (first column)
  //
  // ?
  // this.props.entryColumnName = name of entry name type (Character)
  //
  // this.props.pin = boolean to enable pin feature
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
      pinnedEntryIds: []
    }
  }

  render() {
    const state = this.state;
    const props = this.props;
    const nameOverrideColumns = new Set(this.props.nameOverrideColumns);

    const headerRow = this.props.columnIds.map((columnId) => {
      var columnText = props.columnNames[columnId];
      return <th key={columnId} id={columnId}>{columnText}</th>;
    });

    const compare = (one, two) => {
      if (one.localeCompare) {
        return one.localeCompare(two);
      }
      return one - two;
    }

    const rows = Object.keys(props.columnValues)
      .map((entryId) => {
        const row = props.columnIds.map((columnId) => {
          const value = props.columnValues[entryId][columnId];
          const key = entryId + columnId;
          return <td key={key}>{value}</td>;
        });
        return <tr key={entryId}>{row}</tr>;
      });

    // TODO add className attribute-table for monospacing?
    return (
      <table id='table' className='table table-hover table-bordered'>
        <thead><tr>{headerRow}</tr></thead>
        <tbody>{rows}</tbody>
      </table>
    );
  }
}
