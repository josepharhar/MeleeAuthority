const React = require('react');
const HeadScripts = require('./HeadScripts');
const BodyScripts = require('./BodyScripts');

class MovesTableLayout extends React.Component {
  render() {
    return (
      <html>
        <head>
          <title>Moves Table</title>
          <HeadScripts />
        </head>
        <body>
          <div className="container-head">
            <h1>Moves Table</h1>
          </div>
          <div className="container-body">
            <div id="stats-container">
              <div className="loader"></div>
            </div>
          </div>
          <BodyScripts />
          <script src="/client-build/table.js"></script>
          <script src="/client-build/moves-table.js"></script>
        </body>
      </html>
    );
  }
}

module.exports = MovesTableLayout;
