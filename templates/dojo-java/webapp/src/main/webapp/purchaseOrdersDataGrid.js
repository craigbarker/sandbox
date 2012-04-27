//require("dojox/grid/DataGrid");

require(["dijit/dijit", "dojo/store/JsonRest", "dojo/parser",
    "dojo/store/Observable", "dojo/dom", "dojo/on",
    "dojo/dom-construct", "dojo/fx", "dojo/date/stamp",
    "dojo/date/locale", "dojo/date", "dojox/grid/DataGrid",
    "dojo/data/ObjectStore", "dijit/form/Button", "dijit/layout/BorderContainer", "dijit/layout/ContentPane"],
    function(dijit, JsonRest, parser, Observable, dom, on, domConstruct, fx, stamp, locale, dojoDate, DataGrid,
        ObjectStore) {

//        poStore = new JsonRest({
//            "target": "services/purchase-orders/",
//            "idProperty": "id"
//        });
//
//        poStore.query();
//
//        var formatDate = function(raw) {
//            var forecastDate = stamp.fromISOString(raw);
//            return locale.format(forecastDate, {
//                "formatLength": "medium",
//                "selector": "date"
//            });
//        };
//
//        var layout = [[
//            {'name': 'Part', 'field': 'part', 'width': '10em'},
//            {'name': 'Description', 'field': 'description', 'width': '20em', 'sortable': false},
//            {'name': 'PO Number', 'field': 'poNumber'},
//            {'name': 'Committed Qty', 'field': 'committedQuantity'},
//            {'name': 'Committed Date', 'field': 'committedDate', 'width': '10em', 'formatter': formatDate},
//            {'name': 'Forecast Qty', 'field': 'forecastQuantity'},
//            {'name': 'Forecast Date', 'field': 'forecastDate', 'width': '10em', 'formatter': formatDate}
//        ]];
//
//        var grid = new DataGrid({
//            'id': 'purchaseOrderGrid',
//            'store': new ObjectStore({'objectStore': poStore}),
//            'structure': layout,
//            'rowSelector': '20px',
//            'loadingMessage': 'Loading'
//        }, document.createElement('div'));
//
//        var elem = dojo.byId('grid-container');
//        elem.appendChild(grid.domNode);
//
//        grid.startup();
    });
