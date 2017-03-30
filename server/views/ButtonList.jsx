var React = require('react');

var ButtonList = React.createClass({
  render: function() {
    var buttons = this.props.buttons.map(function(button) {
      if (button) return (
        <a className="link-button btn btn-default btn-primary" href={button.link}>{button.name}</a>
      );
    });

    return <div className="button-list">{buttons}</div>;
  }
});

module.exports = ButtonList;
