'use strict';

var _createClass = function () { function defineProperties(target, props) { for (var i = 0; i < props.length; i++) { var descriptor = props[i]; descriptor.enumerable = descriptor.enumerable || false; descriptor.configurable = true; if ("value" in descriptor) descriptor.writable = true; Object.defineProperty(target, descriptor.key, descriptor); } } return function (Constructor, protoProps, staticProps) { if (protoProps) defineProperties(Constructor.prototype, protoProps); if (staticProps) defineProperties(Constructor, staticProps); return Constructor; }; }();

function _classCallCheck(instance, Constructor) { if (!(instance instanceof Constructor)) { throw new TypeError("Cannot call a class as a function"); } }

function _possibleConstructorReturn(self, call) { if (!self) { throw new ReferenceError("this hasn't been initialised - super() hasn't been called"); } return call && (typeof call === "object" || typeof call === "function") ? call : self; }

function _inherits(subClass, superClass) { if (typeof superClass !== "function" && superClass !== null) { throw new TypeError("Super expression must either be null or a function, not " + typeof superClass); } subClass.prototype = Object.create(superClass && superClass.prototype, { constructor: { value: subClass, enumerable: false, writable: true, configurable: true } }); if (superClass) Object.setPrototypeOf ? Object.setPrototypeOf(subClass, superClass) : subClass.__proto__ = superClass; }

var StatsTable = function (_React$Component) {
  _inherits(StatsTable, _React$Component);

  _createClass(StatsTable, [{
    key: 'getDefaultCharIds',
    value: function getDefaultCharIds() {
      return Object.keys(this.props.attributes);
    }
  }]);

  function StatsTable(props) {
    _classCallCheck(this, StatsTable);

    var _this = _possibleConstructorReturn(this, (StatsTable.__proto__ || Object.getPrototypeOf(StatsTable)).call(this, props));

    _this.state = { charIds: _this.getDefaultCharIds() };
    _this.handleSortClick = _this.handleSortClick.bind(_this);
    return _this;
  }

  _createClass(StatsTable, [{
    key: 'createCharacterRows',
    value: function createCharacterRows(charIds) {
      var attributeDefinitions = this.props.attributeDefinitions;
      var attributes = this.props.attributes;
      var characters = this.props.characters;
      var attributeKeys = this.filteredAttributeKeys();

      return charIds.map(function (charId) {
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
    }
  }, {
    key: 'handleSortClick',
    value: function handleSortClick(e) {
      var attribute = e.target.innerText;
      // need to create a mapping between charId and this category,
      // then sort charIds based on values of this category
      var defaultCharIds = this.getDefaultCharIds();
      var attributes = this.props.attributes;

      var compareCharAndValue = function compareCharAndValue(a, b) {
        return a.value - b.value;
      };

      var sortedCharIds = defaultCharIds.map(function (charId) {
        return {
          charId: charId,
          value: attributes[charId][attribute]
        };
      }).sort(compareCharAndValue).map(function (charAndValue) {
        return charAndValue.charId;
      });

      this.setState({
        charIds: sortedCharIds
      });
    }
  }, {
    key: 'filteredAttributeKeys',
    value: function filteredAttributeKeys() {
      var attributeDefinitions = this.props.attributeDefinitions;
      return this.props.attributeKeys.filter(function (key) {
        return attributeDefinitions[key].viewCategory == 'BASIC';
      });
    }
  }, {
    key: 'render',
    value: function render() {
      var handleClick = this.handleSortClick;
      var headerRow = this.filteredAttributeKeys().map(function (key) {
        return React.createElement(
          'th',
          { onClick: handleClick },
          key
        );
      });

      var characterRows = this.createCharacterRows(this.state.charIds);

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
              { onClick: handleClick },
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