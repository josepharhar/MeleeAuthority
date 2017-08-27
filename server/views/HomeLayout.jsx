var React = require('react');
var ButtonList = require('./ButtonList');
var HeadScripts = require('./HeadScripts');
var BodyScripts = require('./BodyScripts');

class HomeLayout extends React.Component {
  render() {
    var buttons = [
      {
        name: 'Characters',
        link: '/characters'
      },
      {
        name: 'Character Stats',
        link: '/character-stats'
      },
      {
        name: 'Moves Table',
        link: '/moves-table'
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
}

module.exports = HomeLayout;
