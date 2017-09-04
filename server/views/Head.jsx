var React = require('react');

class Head extends React.Component {
  render() {
    const title = this.props.title;
    return (
        <head>
          <title>{title}</title>
          <link rel="stylesheet" href="/static/bootstrap-cyborg.min.css" />
          <link rel="stylesheet" href="/static/narrow-jumbotron.css" />
          <link rel="stylesheet" href="/static/melee-authority.css" />
          <link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/v/bs/dt-1.10.15/fc-3.2.2/fh-3.1.2/rg-1.0.0/rr-1.2.0/datatables.min.css"/>
        </head>
    );
  }
}

module.exports = Head;
