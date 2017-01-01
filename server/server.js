var express = require('express');
var babel = require('babel-core');

var app = express();

app.use(express.static('static'));

app.listen(8080, function() {
  console.log('server started');
});
