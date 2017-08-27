var React = require('react');

class BodyScripts extends React.Component {
  render() {
    return (<div>
      <script src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
      <script src="/static/bootstrap.min.js"></script>
      <script src="/static/react.js"></script>
      <script src="/static/react-dom.js"></script>
      <script src="/client-build/base.js"></script>
      <script src="/client-build/navbar.js"></script>
      <script src="/client-build/analytics.js"></script>
    </div>);
  }
}

module.exports = BodyScripts;
