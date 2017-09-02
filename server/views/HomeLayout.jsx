const React = require('react');
const Layout = require('./Layout');
const ButtonList = require('./ButtonList');

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
      <Layout title={'Melee Authority'}>
        <div className="container">
          <h1>Melee Authority</h1>
          <ButtonList buttons={buttons} />
        </div>
      </Layout>
    );
  }
}

module.exports = HomeLayout;
