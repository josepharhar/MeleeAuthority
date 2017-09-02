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
        </head>
    );
  }
}

module.exports = Head;
