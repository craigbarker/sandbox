require(["dojo/store/JsonRest",
    "dojo/store/Observable", "dojo/dom", "dojo/on",
    "dojo/dom-construct", "dojo/fx", "dojo/date/stamp",
    "dojo/date/locale", "dojo/date"],
    function(JsonRest, Observable, dom, on, domConstruct, fx, stamp, locale, dojoDate) {

    var nextId = 1;

    /*
     * This object loads the orders over the network using
     * a xhr request.  In practise, it makes more sense to
     * define the order data on the server side as the
     * purchaseOrders.jsp page is rendered.  Doing it this
     * way involves a second round trip to the server.
     */
    poStore = new JsonRest({
        "target": "services/purchase-orders/",
        "idProperty": "id"
    });

    var results = poStore.query();
    viewResults(results);

    function viewResults(results) {
        var container = dom.byId("container");
        var rows = {};
        var ids = [];
        var detailRows = {};

        results.forEach(insertRow);

        function insertRow(item, i) {
            var row = domConstruct.create("tr");
            rows[item.id] = container.appendChild(row);
            ids.push(item.id);

            var td = domConstruct.create("td", {
                "class": "expander"
            }, row);

            var expanderDiv = domConstruct.create("div", {
                "class": "expander"
            }, td);

            var expanderAnchor = domConstruct.create("a", {
                "href": '#',
                "class": 'expander-anchor',
                "innerHTML": "+"
            }, expanderDiv);

            on(expanderAnchor, "click", function(){
                expandOrCollapseRow(item, i, expanderAnchor);
            });

            domConstruct.create("td", {
                "innerHTML": item.part,
                "class": "content"
            }, row);
            domConstruct.create("td", {
                "innerHTML": item.description,
                "class": "content"
            }, row);
            domConstruct.create("td", {
                "innerHTML": item.poNumber,
                "class": "content"
            }, row);
            domConstruct.create("td", {
                "innerHTML": item.committedQuantity,
                "class": "content"
            }, row);

            /*
             * Dojo has powerful date parsing and formatting
             * functions. If you return dates in ISO format it's
             * this easy, but it's not that hard no matter what
             * format you return them in.
             */
            var committedDate = stamp.fromISOString(item.committedDate);
            domConstruct.create("td", {
                "innerHTML": locale.format(committedDate, {
                    "formatLength": "medium",
                    "selector": "date"
                })
            }, row);
            domConstruct.create("td", {
                "innerHTML": item.forecastQuantity,
                "class": "content"
            }, row);

            var forecastDate = stamp.fromISOString(item.forecastDate);
            var cssClass = "";
            if (dojoDate.compare(forecastDate, committedDate) > 0) {
                cssClass="late";
            }
            domConstruct.create("td", {
                "class": cssClass,
                "innerHTML": locale.format(forecastDate, {
                    "formatLength": "medium",
                    "selector": "date"
                })
            }, row);


        }

        function removeRow(i) {
        }

        function expandOrCollapseRow(item, i, expanderAnchor) {
            var summaryRow = rows[item.id];

            if (expanderAnchor.innerHTML == "+") {
                expandRow(item, i, expanderAnchor);
            } else {
                collapseRow(item, i, expanderAnchor);
            }
        }

        function expandRow(item, i, expanderAnchor) {
            expanderAnchor.innerHTML = "-";

            var detailRow = domConstruct.create("tr");

            var td = domConstruct.create("td", {
                "class": "content",
                "colSpan": 4
            }, detailRow);

            var div = domConstruct.create("div", {
                "id": "expanded-div-" + item.id,
                "class": "expanded-div",
                "innerHTML": "Some content for id: " + item.id,
                "style": {display: "none"},
                "colSpan": 4
            }, td);

            var nextSummaryRow = rows[ids[i+1]];

            container.insertBefore(detailRow, nextSummaryRow);

            detailRows[item.id] = detailRow;

            fx.wipeIn({
                "node": div
            }).play();
        }

        function collapseRow(item, i, expanderAnchor) {
            fx.wipeOut({
                "node": "expanded-div-" + item.id,
                "onEnd": function() {
                    domConstruct.destroy(container.removeChild(detailRows[item.id]));
                    delete detailRows[item.id];
                    expanderAnchor.innerHTML = "+";
                }
            }).play();
        }
    }
});
