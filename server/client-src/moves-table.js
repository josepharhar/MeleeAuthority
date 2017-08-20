downloadJson(
  [
    '/json/attributes.json',
    '/json/attributeKeys.json',
    '/json/characters.json',
    '/json/animations.json'
    //'/json/attributeDefinitions.json'
  ],
  (jsons) => {
    const attributes = jsons[0];
    const attributeKeys = jsons[1];
    const characters = jsons[2];
    const animations = jsons[3];
    window.animations = animations;

    const sampleStats = animations['Ca'][0].stats;

    const columnIds = ['Character', 'Animation'].concat(Object.keys(sampleStats));
    const columnNames = {};
    columnIds.forEach((columnId) => {
      columnNames[columnId] = columnName;
    });
    
    const columnValues = {};

    const entryIdToName = {};

    ReactDOM.render(<Table
        columnIds={columnIds}
        columnNames={columnNames}
        columnValues={columnValues}
        entryIdToName={entryIdToName} />,
        document.getElementById('stats-container'));
});
