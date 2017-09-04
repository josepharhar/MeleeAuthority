const urls = [
  '/json/charIdToName.json',
  '/json/attributeIdToInfo.json',
  '/json/charIdToAttributeIdToValue.json'
];

downloadJson(urls, (jsons) => {
  const charIdToName = jsons[0];
  const attributeIdToInfo = jsons[1];
  const charIdToAttributeIdToValues = jsons[2];

  const attributeIds = Object.keys(attributeIdToInfo)
    .filter((attributeId) => {
      return attributeIdToInfo[attributeId]['viewCategory'] == 'BASIC';
    });
  const attributeColumns = attributeIds
    .map((attributeId) => {
      return attributeIdToInfo[attributeId]['fullName'];
    });
  const columns = ['Character'].concat(attributeColumns)
    .map((column) => {
      return { title: column };
    });

  const data = [];
  Object.keys(charIdToAttributeIdToValues).forEach((charId) => {
    const attributeValues = [];
    attributeValues.push(charIdToName[charId]);
    attributeIds.forEach((attributeId) => {
      attributeValues.push(charIdToAttributeIdToValues[charId][attributeId]);
    });
    data.push(attributeValues);
  });

  $(document).ready(() => {
    $('table').DataTable({
      columns: columns,
      data: data
    });
    $('.spinner-container').remove();
  });
});
