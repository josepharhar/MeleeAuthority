const React = require('react');
const Head = require('./Head');
const BodyScripts = require('./BodyScripts');

class Layout extends React.Component {
  render() {
    return (
      <html>
        <Head title={this.props.title} />
        <body>
          <div id="navbar"></div>
          {this.props.children}
          <BodyScripts scripts={this.props.scripts} />
        </body>
      </html>
    );
  }
}

module.exports = Layout;
