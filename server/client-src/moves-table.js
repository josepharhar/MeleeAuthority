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

    const sampleStats = charIdToSubactionIdToStats['Ca'][0];

    const columnIds = ['Character', 'Animation'].concat(Object.keys(sampleStats));
    // TODO this shouldnt be necessary?
    const columnIdToName = {};
    columnIds.forEach((columnId) => {
      columnIdToName[columnId] = columnId;
    });
    
    const entryIdToColumnIdToValue = {};
    Object.keys(charIdToSubactionIdToStats).forEach((charId) => {
      Object.keys(charIdToSubactionIdToStats[charId]).forEach((subactionId) => {
        const entryId = charId + subactionId;
        if (!entryIdToColumnIdToValue[entryId]) {
          entryIdToColumnIdToValue[entryId] = {};
        }
        entryIdToColumnIdToValue[entryId]['Character'] = charIdToName[charId];
        entryIdToColumnIdToValue[entryId]['Animation'] = charIdToSubactionIdToInfo[charId][subactionId]['description'];
        columnIds.forEach((columnId) => {
          entryIdToColumnIdToValue[entryId][columnId] = charIdToSubactionIdToStats[charId][subactionId][columnId];
        });
      });
    });

    const entryIdToName = {};

    ReactDOM.render(<Table
        columnIds={columnIds}
        columnNames={columnIdToName}
        columnValues={entryIdToColumnIdToValue}
        entryIdToName={entryIdToName} />,
        document.getElementById('stats-container'));
});
