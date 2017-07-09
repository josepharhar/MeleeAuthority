"use strict";

// TODO use some sort of namespace for this?

var getjson = function getjson(url) {
  return new Promise(function (resolve, reject) {
    $.getJSON(url, resolve);
  });
};

var downloadJson = function downloadJson(urls, func) {
  Promise.all(urls.map(getjson)).then(function (jsons) {
    func(jsons);
  });
};