const React = require('react');
const Layout = require('./Layout');
const ButtonList = require('./ButtonList');

class CharactersListLayout extends React.Component {
  render() {
    const charIdToName = this.props.charIdToName;
    var numCharacters = Object.keys(charIdToName).length;

    var buttons = Object.keys(charIdToName).map(function(charId) {
      return {
        name: charIdToName[charId],
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
      <Layout title={'Characters List'}>
        <div className="container-head">
          <h1>Characters List</h1>
        </div>
        <div className="container-body">
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
      </Layout>
    );
  }
}

module.exports = CharactersListLayout;
