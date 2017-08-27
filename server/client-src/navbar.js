class Navbar extends React.Component {
  render() {
    const navbarEntries = [
      {
        name: 'Home',
        link: '/',
      },
      {
        name: 'Characters',
        link: '/characters'
      },
      {
        name: 'Character Stats Table',
        link: '/character-stats'
      },
      {
        name: 'Moves Table',
        link: '/moves-table'
      }
    ];

    const currentLink = window.location.pathname;
    const navbarLinks = navbarEntries.map((entry) => {
      if (entry.link == currentLink) {
        return <li><a href={entry.link}>{entry.name}</a></li>;
      } else {
        return <li className="active" ><a href={entry.link}>{entry.name}</a></li>;
      }
    });
  
    return (
      <nav className="navbar navbar-default">
	<div className="container-fluid">
	  <div className="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
	    <div className="navbar-brand">Melee Authority</div>
	    <ul className="nav navbar-nav">
	      {navbarLinks}
	    </ul>
	    <form className="navbar-form navbar-left" role="search">
	      <div className="form-group">
	      </div>
	    </form>
	  </div>
	</div>
      </nav>
    );
  }
}

ReactDOM.render(<Navbar />, document.getElementById('navbar'));
