var React = require('react');

class BodyScripts extends React.Component {
  render() {
    var extraScripts = [];
    if (this.props.scripts) {
      extraScripts = this.props.scripts.map((script) => {
        return <script src={script}></script>
      });
    }

    return (
      <div>
        <script src="/static/jquery-3.2.1.min.js"></script>
        <script src="/static/bootstrap.min.js"></script>
        <script src="/static/react.js"></script>
        <script src="/static/react-dom.js"></script>
        <script src="/client-build/base.js"></script>
        <script src="/client-build/navbar.js"></script>
        <script async src="/client-build/analytics.js"></script>
        {extraScripts}
      </div>
    );
  }
}

module.exports = BodyScripts;
