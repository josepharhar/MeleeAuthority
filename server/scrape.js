var crawler = require('crawler');
var util = require('util');
var fs = require('fs');
var mkdirp = require('mkdirp');
var path = require('path');

var outputDirectory = __dirname + '/build';
var queuedLinks = {};

var c = new crawler({
  maxConnections: 1,
  //rateLimit: 2000,
  callback: function(error, res, done) {
    if (error) {
      console.error(error);
    } else {
      var filepath = outputDirectory + res.request.uri.path + '/index.html';
      console.log(res.request.uri.href + ' "' + res.$('title').text() + '" -> ' + filepath);
      mkdirp(path.dirname(filepath), function(err) {
        if (err) {
          console.error('mkdirp error: ' + err);
        } else {
          fs.writeFile(filepath, res.body, function(err) {
            if (err) {
              console.error('fs.writeFile error: ' + err);
            } else {
              console.log('file ' + filepath + ' was saved');
            }
          });
        }
      });

      // TODO use res.request.uri.protocol instead of http:
      var address = 'http://' + res.request.uri.host;

      res.$('a').each(function() {
        var link = address + this.attribs.href;
        console.log('target link: ' + link);
        if (queuedLinks[link] === true) {
          console.log('already queued link "' + link + '"');
        } else {
          c.queue({
            uri: link,
            limiter: 'key1'
          });
        }
      });
    }
    done();
  }
});

c.queue({
  uri: 'http://localhost:8080',
  limiter: 'key1',
});
