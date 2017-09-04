const urls = [
  '/json/attributes.json',
  '/json/attributeKeys.json',
  '/json/charIdToName.json',
  '/json/attributeDefinitions.json'
];

downloadJson(urls, function(jsons) {
  const attributes = jsons[0];
  const attributeKeys = jsons[1];
  const charIdToName = jsons[2];
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
  Object.keys(charIdToName).forEach(function(charId) {
    columnValues[charId].charId = charIdToName[charId];
  });
  const entryIdToName = charIdToName;

  ReactDOM.render(<Table
      columnIds={columnIds}
      columnNames={columnNames}
      columnValues={columnValues}
      entryIdToName={entryIdToName} />,
      document.getElementById('stats-container'));

  $(document).ready(() => {
    $('#table').DataTable();
  });
});
