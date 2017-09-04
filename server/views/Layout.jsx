const React = require('react');

class Layout extends React.Component {
  render() {
    const sheets = [
      'https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css',
      '/static/bootstrap-cyborg.min.css',
      '/static/narrow-jumbotron.css',
      '/static/melee-authority.css',
      'https://cdn.datatables.net/v/bs/jszip-3.1.3/pdfmake-0.1.27/dt-1.10.15/af-2.2.0/b-1.4.0/b-colvis-1.4.0/cr-1.3.3/fc-3.2.2/fh-3.1.2/kt-2.3.0/r-2.1.1/rg-1.0.0/rr-1.2.0/sc-1.4.2/se-1.2.2/datatables.min.css'
    ];
    if (this.props.sheets) {
      this.props.sheets.forEach((sheet) => {
        sheets.push(sheet);
      });
    }
    const sheetTags = sheets.map((sheet) => {
      return <link rel="stylesheet" type="text/css" href={sheet}/>
    });

    const scripts = [
        '/static/jquery-3.2.1.min.js',
        'https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js',
        //'/static/bootstrap.min.js',
        '/static/react.js',
        '/static/react-dom.js',
        '/client-build/base.js',
        '/client-build/navbar.js',
        '/client-build/analytics.js',
        'https://cdn.datatables.net/v/bs/jszip-3.1.3/pdfmake-0.1.27/dt-1.10.15/af-2.2.0/b-1.4.0/b-colvis-1.4.0/cr-1.3.3/fc-3.2.2/fh-3.1.2/kt-2.3.0/r-2.1.1/rg-1.0.0/rr-1.2.0/sc-1.4.2/se-1.2.2/datatables.min.js'
    ];
    if (this.props.scripts) {
      this.props.scripts.forEach((script) => {
        scripts.push(script);
      });
    }
    const scriptTags = scripts.map((script) => {
      return <script src={script}></script>
    });

    return (
      <html>
        <head>
          <title>{this.props.title}</title>
          {sheetTags}
        </head>
        <body>
          <div id="navbar"></div>
          {this.props.children}
          {scriptTags}
        </body>
      </html>
    );
  }
}

module.exports = Layout;
