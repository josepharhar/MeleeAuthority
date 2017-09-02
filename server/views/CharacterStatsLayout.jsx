const React = require('react');
const Layout = require('./Layout');
const Spinner = require('./Spinner');

class CharacterStatsLayout extends React.Component {
  render() {
    const scripts = [
      '/client-build/table.js',
      '/client-build/stats.js'
    ];

    return (
      <Layout title={'Character Stats'} scripts={scripts}>
        <div className="container-head">
          <h1>Character Stats</h1>
        </div>
        <div className="container-body">
          <div id="stats-container">
            <Spinner />
          </div>
        </div>
      </Layout>
    );
  }
}

module.exports = CharacterStatsLayout;
