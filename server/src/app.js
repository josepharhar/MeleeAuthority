var MyComponent = React.createClass({
  render: function() {
    return <blockquote>this is a quote lol</blockquote>;
  }
});

ReactDOM.render(<MyComponent />, document.getElementById('app'));
