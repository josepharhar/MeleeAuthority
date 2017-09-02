const React = require('react');
const HeadScripts = require('./HeadScripts');
const BodyScripts = require('./BodyScripts');

class CharacterStatsLayout extends React.Component {
  render() {
    return (
      <html>
        <head>
          <title>Character Stats</title>
          <HeadScripts />
        </head>
        <body>
          <div className="container-head">
            <h1>Character Stats</h1>
          </div>
          <div className="container-body">
            <div id="stats-container">
              <div className="loader"></div>
            </div>
          </div>
          <BodyScripts />
          <script src="/client-build/table.js"></script>
          <script src="/client-build/stats.js"></script>
        </body>
      </html>
    );
  }
}

module.exports = CharacterStatsLayout;
