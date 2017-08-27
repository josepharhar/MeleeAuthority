class Navbar extends React.Component {
  render() {
    const navbarEntries = [
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
      if (currentLink.startsWith(entry.link)) {
        return <li key={entry.link}><a href={entry.link}>{entry.name}</a></li>;
      } else {
        return <li key={entry.link} className="active"><a href={entry.link}>{entry.name}</a></li>;
      }
    });
  
    return (
      <nav className="navbar navbar-default">
	<div className="container-fluid">
	  <div className="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
	    <a className="navbar-brand" href="/">Melee Authority</a>
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
