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

    const buttonClasses = 'link-button btn btn-info';
    const activeButtonClasses = buttonClasses + buttonClasses + ' active';

    const currentLink = window.location.pathname;
    const navbarLinks = navbarEntries.map((entry) => {
      if (currentLink.startsWith(entry.link)) {
        return <a className={activeButtonClasses} href={entry.link}>{entry.name}</a>;
      } else {
        return <a className={buttonClasses} href={entry.link}>{entry.name}</a>;
      }
    });

    var homeButtonClasses = buttonClasses /*+ ' navbar-brand'*/;
    if (currentLink == '/') {
      homeButtonClasses = activeButtonClasses /*+ ' navbar-brand'*/;
    }
  
    return (
      <nav className="navbar navbar-default">
        <div className="container-fluid">
          <div className="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
            <ol className="nav navbar-nav">
              <a className={homeButtonClasses} href="/">Melee Authority</a>
              {navbarLinks}
            </ol>
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
