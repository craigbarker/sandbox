require(["dojo/store/JsonRest",
    "dojo/store/Observable", "dojo/dom", "dojo/on",
    "dojo/dom-construct", "dojo/fx"], function(JsonRest, Observable, dom, on, domConstruct, fx) {
    var nextId = 1;

    poStore = new JsonRest({
        target: "services/purchase-orders/",
        idProperty: "id"
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
                class: "expander"
            }, row);

            var expanderAnchor = domConstruct.create("a", {
                href: '#',
                class: 'expander-anchor',
                innerHTML: "+"
            }, td);

            on(expanderAnchor, "click", function(){
                expandOrCollapseRow(item, i);
            });

            domConstruct.create("td", {
                innerHTML: item.orderNumber,
                class: "content"
            }, row);
            domConstruct.create("td", {
                innerHTML: item.customerReference,
                class: "content"
            }, row);


            td = domConstruct.create("td", {}, row);

            var removeLink = domConstruct.create("a", {
                href: '#',
                innerHTML: 'Remove'
            }, td);
            on(removeLink, "click", function(){
                alert("Remove");
            });
        }

        function removeRow(i) {
        }

        function expandOrCollapseRow(item, i) {
            var summaryRow = rows[item.id];
            var expanderTd = summaryRow.childNodes[0].childNodes[0];

            if (expanderTd.innerHTML == "+") {
                expandRow(item, i, expanderTd);
            } else {
                collapseRow(item, i, expanderTd);
            }
        }

        function expandRow(item, i, expanderTd) {
            expanderTd.innerHTML = "-";

            var detailRow = domConstruct.create("tr");

            var td = domConstruct.create("td", {
                class: "content",
                colSpan: 4
            }, detailRow);

            var div = domConstruct.create("div", {
                id: "expanded-div-" + item.id,
                class: "expanded-div",
                innerHTML: "Some content for id: " + item.id,
                style: {display: "none"},
                colSpan: 4
            }, td);

            var nextSummaryRow = rows[ids[i+1]];

            container.insertBefore(detailRow, nextSummaryRow);

            detailRows[item.id] = detailRow;

            fx.wipeIn({
                node: div
            }).play();
        }

        function collapseRow(item, i, expanderTd) {
            fx.wipeOut({
                node: "expanded-div-" + item.id,
                onEnd: function() {
                    domConstruct.destroy(container.removeChild(detailRows[item.id]));
                    delete detailRows[item.id];
                    expanderTd.innerHTML = "+";
                }
            }).play();
        }
    }
});
