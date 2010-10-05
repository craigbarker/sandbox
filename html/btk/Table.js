Btk.Table = {};

Btk.Table.Column = Core.extend({
    width: null,
    title: null,

    $construct: function(properties){
        if (properties) {
            this.width = properties.width;
            this.title = properties.title;
        }
    }
});

Btk.Table.ColumnModel = Core.extend({
    columns: null,

    $construct: function(properties) {
        if (properties && properties.columns) {
            this.columns = properties.columns;
        }
    }
});

Btk.Table.TableModel = Core.extend({
    data: null,

    $construct: function(properties) {
        if (properties && properties.data) {
            this.data = properties.data;
        }
    }
});

Btk.Table.Table = Core.extend({

    _columnModel: null,
    _parent: null,
    _tableModel: null,
    _width: null,

    $construct: function(properties) {
        if (properties) {
            this._parent = properties.parent;
            this._columnModel = properties.columnModel;
            this._tableModel = properties.tableModel;
            this._width = properties.width;
        }
        this._createHeader();
        this._createBody();
    },

    _createBody: function() {
        var bodyDiv = document.createElement("div");
        this._parent.appendChild(bodyDiv);
        bodyDiv.style.width = this._width + "px";
        bodyDiv.style.height = "100px";
        bodyDiv.style.border = "1px dotted";
        bodyDiv.style.overflow = "auto";

        for (var i = 0, j = this._tableModel.data.length; i < j; i++) {
            var rowTable = document.createElement("table");
            bodyDiv.appendChild(rowTable);

            var rowTr = document.createElement("tr");
            rowTable.appendChild(rowTr);

            var row = this._tableModel.data[i];

            for (var k = 0, l = row.length; k < l; k++) {
                var cellTd = document.createElement("td");
                rowTr.appendChild(cellTd);
                var column = this._columnModel.columns[k];
                cellTd.style.width = column.width + "px";
                cellTd.appendChild(document.createTextNode(row[k]));
            }
        }
    },

    _createHeader: function() {
        var headerDiv = document.createElement("div");
        this._parent.appendChild(headerDiv);
        headerDiv.style.width = this._width + "px";
        headerDiv.style.border = "1px dotted";
        headerDiv.style.overflow = "hidden";

        var headerTable = document.createElement("table");
        headerDiv.appendChild(headerTable);

        var headerTr = document.createElement("tr");
        headerTable.appendChild(headerTr);

        for (var i = 0, j = this._columnModel.columns.length; i < j; i++) {
            var column = this._columnModel.columns[i];
            var td = document.createElement("td");
            td.style.width = column.width + "px";
            headerTr.appendChild(td);
            td.appendChild(document.createTextNode(column.title));
        }
    }
});
