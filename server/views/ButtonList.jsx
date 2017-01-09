var React = require('react');

var ButtonList = React.createClass({
  render: function() {
    var buttons = this.props.buttons.map(function(button) {
      return (
        <div>
          <a href={button.link}>
            <button>{button.name}</button>
          </a>
        </div>
      );
    });

    return <div>{buttons}</div>;
  }
});

module.exports = ButtonList;
