'use strict';

var _createClass = function () { function defineProperties(target, props) { for (var i = 0; i < props.length; i++) { var descriptor = props[i]; descriptor.enumerable = descriptor.enumerable || false; descriptor.configurable = true; if ("value" in descriptor) descriptor.writable = true; Object.defineProperty(target, descriptor.key, descriptor); } } return function (Constructor, protoProps, staticProps) { if (protoProps) defineProperties(Constructor.prototype, protoProps); if (staticProps) defineProperties(Constructor, staticProps); return Constructor; }; }();

function _classCallCheck(instance, Constructor) { if (!(instance instanceof Constructor)) { throw new TypeError("Cannot call a class as a function"); } }

function _possibleConstructorReturn(self, call) { if (!self) { throw new ReferenceError("this hasn't been initialised - super() hasn't been called"); } return call && (typeof call === "object" || typeof call === "function") ? call : self; }

function _inherits(subClass, superClass) { if (typeof superClass !== "function" && superClass !== null) { throw new TypeError("Super expression must either be null or a function, not " + typeof superClass); } subClass.prototype = Object.create(superClass && superClass.prototype, { constructor: { value: subClass, enumerable: false, writable: true, configurable: true } }); if (superClass) Object.setPrototypeOf ? Object.setPrototypeOf(subClass, superClass) : subClass.__proto__ = superClass; }

var StatsTable = function (_React$Component) {
  _inherits(StatsTable, _React$Component);

  function StatsTable() {
    _classCallCheck(this, StatsTable);

    return _possibleConstructorReturn(this, (StatsTable.__proto__ || Object.getPrototypeOf(StatsTable)).apply(this, arguments));
  }

  _createClass(StatsTable, [{
    key: 'render',
    value: function render() {
      var attributes = this.props.attributes;
      var characters = this.props.characters;
      var attributeDefinitions = this.props.attributeDefinitions;

      var attributeKeys = this.props.attributeKeys.filter(function (key) {
        return attributeDefinitions[key].viewCategory == 'BASIC';
      });

      var headerRow = attributeKeys.map(function (key) {
        return React.createElement(
          'th',
          null,
          key
        );
      });

      var characterRows = Object.keys(attributes).map(function (charId) {
        var row = attributeKeys.map(function (key) {
          return React.createElement(
            'td',
            null,
            attributes[charId][key]
          );
        });

        return React.createElement(
          'tr',
          null,
          React.createElement(
            'td',
            null,
            characters[charId]
          ),
          row
        );
      });

      // TODO use class attribute-table?
      return React.createElement(
        'table',
        { className: 'table table-hover table-bordered' },
        React.createElement(
          'thead',
          null,
          React.createElement(
            'tr',
            null,
            React.createElement(
              'th',
              null,
              'Character'
            ),
            headerRow
          )
        ),
        React.createElement(
          'tbody',
          null,
          characterRows
        )
      );
    }
  }]);

  return StatsTable;
}(React.Component);

var urls = ['/json/attributes.json', '/json/attributeKeys.json', '/json/characters.json', '/json/attributeDefinitions.json'];

downloadJson(urls, function (jsons) {
  ReactDOM.render(React.createElement(StatsTable, {
    attributes: jsons[0],
    attributeKeys: jsons[1],
    characters: jsons[2],
    attributeDefinitions: jsons[3] }), document.getElementById('stats-container'));
});