var React = require('react');
var ButtonList = require('./ButtonList');
var HeadScripts = require('./HeadScripts');
var BodyScripts = require('./BodyScripts');

var HomeLayout = React.createClass({
  render: function() {
    var buttons = [
      {
        name: 'Characters',
        link: '/characters'
      }
    ];
  
    return (
      <html>
        <head>
          <title>Melee Authority</title>
          <HeadScripts />
        </head>
        <body>
          <div className="container">
            <h1>Melee Authority</h1>
            <ButtonList buttons={buttons}/>
          </div>
          <BodyScripts />
        </body>
      </html>
    );
  }
});

module.exports = HomeLayout;
