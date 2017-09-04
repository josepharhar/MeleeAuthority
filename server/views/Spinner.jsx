const React = require('react');

class Spinner extends React.Component {
  render() {
    return (
      <div id="spinner" className="spinner-container">
        <div className="spinner"></div>
      </div>
    );
  }
}

module.exports = Spinner;
