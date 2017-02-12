var crawler = require('crawler');
var util = require('util');

var queuedLinks = {};

var c = new crawler({
  maxConnections: 1,
  //rateLimit: 2000,
  callback: function(error, res, done) {
    if (error) {
      console.error(error);
    } else {
      // TODO use res.request.uri.protocol instead of http:
      var address = 'http://' + res.request.uri.host;
      console.log(res.request.uri.href + ' -> ' + res.$('title').text());
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
