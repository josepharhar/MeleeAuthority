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
					/*<div className="navbar-header">
						<button type="button" className="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
							<span className="sr-only">Toggle navigation</span>
							<span className="icon-bar"></span>
							<span className="icon-bar"></span>
							<span className="icon-bar"></span>
						</button>
						<a className="navbar-brand" href="/">Melee Authority</a>
					</div>*/
							/*<li className="dropdown">
								<a href="#" className="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false">Dropdown <span className="caret"></span></a>
								<ul className="dropdown-menu" role="menu">
									<li><a href="#">Action</a></li>
									<li><a href="#">Another action</a></li>
									<li><a href="#">Something else here</a></li>
									<li className="divider"></li>
									<li><a href="#">Separated link</a></li>
									<li className="divider"></li>
									<li><a href="#">One more separated link</a></li>
								</ul>
							</li>*/
}

ReactDOM.render(<Navbar />, document.getElementById('navbar'));
