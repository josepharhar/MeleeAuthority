const React = require('react');
const Layout = require('./Layout');
const Spinner = require('./Spinner');

class MovesTableLayout extends React.Component {
  render() {
    const scripts = [
      '/client-build/table.js',
      '/client-build/moves-table.js'
    ];

    return (
      <Layout title={'Moves Table'} scripts={scripts}>
        <div className="container-head">
          <h1>Moves Table</h1>
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

module.exports = MovesTableLayout;
