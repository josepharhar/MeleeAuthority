downloadJson(
  [
    '/json/charIdToName.json',
    '/json/charIdToSubactionIdToStats.json',
    '/json/charIdToSubactionIdToInfo.json'
  ],
  (jsons) => {
    const charIdToName = jsons[0];
    const charIdToSubactionIdToStats = jsons[1];
    const charIdToSubactionIdToInfo = jsons[2];
    window.charIdToSubactionIdToInfo = jsons[2];

    const sampleStats = charIdToSubactionIdToStats['Ca'][0];

    const columnIds = ['Character', 'Animation'].concat(Object.keys(sampleStats));
    // TODO this shouldnt be necessary?
    const columnIdToName = {};
    columnIds.forEach((columnId) => {
      columnIdToName[columnId] = columnId;
    });
    
    const entryIdToColumnIdToValue = {};
    Object.keys(charIdToSubactionIdToStats).forEach((charId) => {
      const charName = charIdToName[charId];

      Object.keys(charIdToSubactionIdToStats[charId]).forEach((subactionId) => {
        const info = charIdToSubactionIdToInfo[charId][subactionId];

        if (info['description']['viewCategory'] != 'ADVANCED') {
          const entryId = charId + subactionId;
          entryIdToColumnIdToValue[entryId] = {};
          columnIds.forEach((columnId) => {
            entryIdToColumnIdToValue[entryId][columnId] = charIdToSubactionIdToStats[charId][subactionId][columnId];
          });
          entryIdToColumnIdToValue[entryId]['Character'] = charIdToName[charId];
          entryIdToColumnIdToValue[entryId]['Animation'] = info['description']['description'];
        }
      });
    });

    const entryIdToName = charIdToName;
    window.entryIdToColumnIdToValue = entryIdToColumnIdToValue;

    const rowFilterName = 'row-filter'

    /*var table;
    const onchange = (e) => {
      const text = document.getElementById(rowFilterName).value;
      table.addRowFilter(text);
    };*/

    const statsContainer = (
      <div>
        <div className="form-group">
          <label>Row Filter:</label>
          <input id={rowFilterName} type="text" className="form-control" onChange={onchange}></input>
        </div>
        <Table
          columnIds={columnIds}
          columnNames={columnIdToName}
          columnValues={entryIdToColumnIdToValue}
          entryIdToName={entryIdToName} />
      </div>
    );

    const table = ReactDOM.render(
        statsContainer,
        document.getElementById('stats-container'));
    table.addRowFilter('Marth');
    window.table = table;

    // TODO could this be done faster if we could apply the filter before rendering?
    //table.addFilter('Character', 'Marth');
});
