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
    const columnIds = Object.keys(sampleStats);
    const columns = ['Character', 'Animation'].concat(columnIds).map((columnId) => {
      return { title: columnId };
    });
    
    const data = [];
    Object.keys(charIdToSubactionIdToStats).forEach((charId) => {
      const charName = charIdToName[charId];

      Object.keys(charIdToSubactionIdToStats[charId]).forEach((subactionId) => {
        const info = charIdToSubactionIdToInfo[charId][subactionId];

        if (info['description']['viewCategory'] != 'ADVANCED') {
          const columnValues = [];
          columnValues.push(charId);
          columnValues.push(subactionId);
          columnIds.forEach((columnId) => {
            columnValues.push(charIdToSubactionIdToStats[charId][subactionId][columnId]);
          });
          data.push(columnValues);
        }
      });
    });

    const entryIdToName = charIdToName;

    $(document).ready(() => {
      $('table').DataTable({
        columns: columns,
        data: data
      });
      $('.spinner-container').remove();
    });
});
