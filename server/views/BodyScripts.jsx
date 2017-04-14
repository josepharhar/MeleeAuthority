var React = require('react');

var BodyScripts = React.createClass({
  render: function() {
    return (<div>
      <script src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
      <script src="/static/bootstrap.min.js"></script>
    </div>);
  }
});

module.exports = BodyScripts;
