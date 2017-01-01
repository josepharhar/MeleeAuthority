'use strict';

var MyComponent = React.createClass({
  displayName: 'MyComponent',

  render: function render() {
    return React.createElement(
      'blockquote',
      null,
      'this is a quote lol'
    );
  }
});

ReactDOM.render(React.createElement(MyComponent, null), document.getElementById('app'));