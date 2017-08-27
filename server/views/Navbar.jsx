var React = require('react');
var ButtonList = require('./ButtonList');

class Navbar extends React.Component {
  render() {
    var buttons = [
      {
        name: 'Home',
        link: '/'
      }
    ];
  
    return (
      <nav className="navbar navbar-toggleable-md navbar-light bg-faded">
        <div className="collapse navbar-collapse">
          <ButtonList buttons={buttons} />
          <ul className="navbar-nav mr-auto">
            <li className="nav-item">
              <a className="nav-link" href="/">Home</a>
            </li>
          </ul>
        </div>
      </nav>
    );
  }
}

module.exports = Navbar;
