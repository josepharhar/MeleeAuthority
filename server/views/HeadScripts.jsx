var React = require('react');

class HeadScripts extends React.Component {
  render() {
    //return <link rel="stylesheet" href="/static/bootstrap-cyborg.min.css" />;
    // TODO the div creates a space at the top of the page
    return (
        <div>
          <div>
            <link rel="stylesheet" href="/static/bootstrap-cyborg.min.css" />
            <link rel="stylesheet" href="/static/narrow-jumbotron.css" />
            <link rel="stylesheet" href="/static/melee-authority.css" />
          </div>
          <div id="navbar"></div>
        </div>
    );
  }
}

module.exports = HeadScripts;
