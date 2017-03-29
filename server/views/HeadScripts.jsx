var React = require('react');

var HeadScripts = React.createClass({
  render: function() {
    //return <link rel="stylesheet" href="/static/bootstrap-cyborg.min.css" />;
    return (
      <div>
        <link rel="stylesheet" href="/static/bootstrap-cyborg.min.css" />
        <link rel="stylesheet" href="/static/narrow-jumbotron.css" />
        <link rel="stylesheet" href="/static/melee-authority.css" />
      </div>
    );
  }
});

module.exports = HeadScripts;
