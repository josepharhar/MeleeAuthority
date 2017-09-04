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
    window.asdf = charIdToSubactionIdToInfo;

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
          columnValues.push(charIdToName[charId]);
          columnValues.push(info['description']['description']);
          columnIds.forEach((columnId) => {
            var value = charIdToSubactionIdToStats[charId][subactionId][columnId];
            if (value == -1) {
              value = '';
            }
            columnValues.push(value);
          });
          data.push(columnValues);
        }
      });
    });

    $(document).ready(() => {
      $('table').DataTable({
        columns: columns,
        data: data
      });
      $('.spinner-container').remove();
    });
});
