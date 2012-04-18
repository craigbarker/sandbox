//require("dojox/grid/DataGrid");

require(["dojo/store/JsonRest",
    "dojo/store/Observable", "dojo/dom", "dojo/on",
    "dojo/dom-construct", "dojo/fx", "dojo/date/stamp",
    "dojo/date/locale", "dojo/date", "dojox/grid/DataGrid",
    "dojo/data/ObjectStore"],
    function(JsonRest, Observable, dom, on, domConstruct, fx, stamp, locale, dojoDate, DataGrid,
        ObjectStore) {

        poStore = new JsonRest({
            "target": "services/purchase-orders/",
            "idProperty": "id"
        });

        poStore.query();

        var formatDate = function(raw) {
            var forecastDate = stamp.fromISOString(raw);
            return locale.format(forecastDate, {
                "formatLength": "medium",
                "selector": "date"
            });
        };

        var layout = [[
            {'name': 'Part', 'field': 'part', 'width': '10em'},
            {'name': 'Description', 'field': 'description', 'width': '20em', 'sortable': false},
            {'name': 'PO Number', 'field': 'poNumber'},
            {'name': 'Committed Qty', 'field': 'committedQuantity'},
            {'name': 'Committed Date', 'field': 'committedDate', 'formatter': formatDate},
            {'name': 'Forecast Qty', 'field': 'forecastQuantity'},
            {'name': 'Forecast Date', 'field': 'forecastDate', 'formatter': formatDate}
        ]];

        var grid = new DataGrid({
            'id': 'purchaseOrderGrid',
            'store': new ObjectStore({'objectStore': poStore}),
            'structure': layout,
            'rowSelector': '20px',
            'autoHeight': 20,
            'loadingMessage': 'Loading'
        }, document.createElement('div'));

        dom.byId('grid-div').appendChild(grid.domNode);

        grid.startup();

});
