const React = require('react');
const Layout = require('./Layout');
const Spinner = require('./Spinner');

class CharacterStatsLayout extends React.Component {
  render() {
    const scripts = [
      '/client-build/stats.js'
    ];

    return (
      <Layout title={'Character Stats'} scripts={scripts}>
        <div className="container-head">
          <h1>Character Stats</h1>
        </div>
        <div className="container-body">
          <table id='table' className='table table-hover table-bordered'></table>
          <Spinner />
        </div>
      </Layout>
    );
  }
}

module.exports = CharacterStatsLayout;
