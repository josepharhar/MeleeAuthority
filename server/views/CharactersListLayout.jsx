var React = require('react');
var ButtonList = require('./ButtonList');
var HeadScripts = require('./HeadScripts');
var BodyScripts = require('./BodyScripts');

class CharactersListLayout extends React.Component {
  render() {
    var characters = this.props.characters;
    var numCharacters = Object.keys(characters).length;

    var buttons = Object.keys(characters).map(function(charId) {
      return {
        name: characters[charId],
        link: '/characters/' + charId
      };
    }).sort(function(one, two) {
      return one.name.localeCompare(two.name);
    });

    var one_third = Math.ceil(numCharacters / 3);
    var two_thirds = Math.ceil(2 * numCharacters / 3);

    var buttons_one = buttons.slice(0, one_third);
    var buttons_two = buttons.slice(one_third, two_thirds);
    var buttons_three = buttons.slice(two_thirds);

    return (
      <html>
        <head>
          <title>Characters List</title>
          <HeadScripts />
        </head>
        <body>
          <div className="container">
            <h1>Characters List</h1>
            <div className="flex-container">
              <div className="flex-item">
                <ButtonList buttons={buttons_one}/>
              </div>
              <div className="flex-item">
                <ButtonList buttons={buttons_two}/>
              </div>
              <div className="flex-item">
                <ButtonList buttons={buttons_three}/>
              </div>
            </div>
          </div>
          <BodyScripts />
        </body>
      </html>
    );
  }
}

module.exports = CharactersListLayout;
