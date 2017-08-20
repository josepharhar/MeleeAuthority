const urls = ['/json/attributes.json', '/json/attributeKeys.json', '/json/characters.json', '/json/attributeDefinitions.json'];

downloadJson(urls, function(jsons) {
  const attributes = jsons[0];
  const attributeKeys = jsons[1];
  const characters = jsons[2];
  const attributeDefinitions = jsons[3];

  const columnIds = ['charId'].concat(attributeKeys.filter(function(key) {
    return attributeDefinitions[key].viewCategory == 'BASIC';
  }));
  var columnNames = {};
  columnIds.forEach(function(columnId) {
    var columnName = columnId;
    if (columnId == 'charId') {
      columnName = 'Character';
    }
    columnNames[columnId] = columnName;
  });
  var columnValues = attributes;
  //for (var charId in Object.keys(characters)) {
  Object.keys(characters).forEach(function(charId) {
    columnValues[charId].charId = characters[charId];
  });
  const entryIdToName = characters;

  ReactDOM.render(<Table
      columnIds={columnIds}
      columnNames={columnNames}
      columnValues={columnValues}
      entryIdToName={entryIdToName} />,
      document.getElementById('stats-container'));
});
