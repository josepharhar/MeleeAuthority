// TODO use some sort of namespace for this?

const getjson = function(url) {
  return new Promise(function(resolve, reject) {
    $.getJSON(url, resolve);
  });
}

const downloadJson = function(urls, func) {
  Promise.all(urls.map(getjson)).then(function(jsons) {
    func(jsons);
  });
}
