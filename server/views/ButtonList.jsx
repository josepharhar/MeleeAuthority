var React = require('react');

var ButtonList = React.createClass({
  render: function() {
    var buttons = this.props.buttons.map(function(button) {
      if (button) return (
        <div>
          <a href={button.link} className="btn btn-default btn-primary">{button.name}</a>
        </div>
      );
    });

    return <div>{buttons}</div>;
  }
});

module.exports = ButtonList;
