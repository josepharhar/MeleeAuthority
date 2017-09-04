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
        <script type="text/javascript" src="https://cdn.datatables.net/v/bs/dt-1.10.15/fc-3.2.2/fh-3.1.2/rg-1.0.0/rr-1.2.0/datatables.min.js"></script>
        {extraScripts}
      </div>
    );
  }
}

module.exports = BodyScripts;
