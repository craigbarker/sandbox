//require("dojox/grid/DataGrid");

require(["dijit/dijit", "dojo/store/JsonRest", "dojo/parser",
    "dojo/store/Observable", "dojo/dom", "dojo/on",
    "dojo/dom-construct", "dojo/fx", "dojo/date/stamp",
    "dojo/date/locale", "dojo/date", "dojox/grid/DataGrid",
    "dojo/data/ObjectStore", "dijit/form/Button", "dijit/layout/BorderContainer", "dijit/layout/ContentPane"],
    function(dijit, JsonRest, parser, Observable, dom, on, domConstruct, fx, stamp, locale, dojoDate, DataGrid,
        ObjectStore) {

        formatDate = function(raw) {
            var forecastDate = stamp.fromISOString(raw);
            return locale.format(forecastDate, {
                "formatLength": "medium",
                "selector": "date"
            });
        };

        poStore = new JsonRest({
            "target": "services/purchase-orders/",
            "idProperty": "id"
        });

        objectStore = new ObjectStore({'objectStore': poStore});

        parser.parse();

    });