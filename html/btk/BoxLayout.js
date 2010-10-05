Btk.BoxLayout = Core.extend({

    $static: {
        VERTICAL: 1,
        HORIZONTAL: 2
    },

    _orientation: null,

    $construct: function(orientation) {
        if (orientation == null) {
            throw new Error("Orientation must be specified");
        }
        this._orientation = orientation;
    },

    layout: function(container) {
        var table = document.createElement("table");
        table.style.border = "1px dotted";
        container.content = table;

        // add the table we will use for layout
        container.parent.appendChild(container.content);

        switch(this._orientation) {
            case Btk.BoxLayout.VERTICAL:
                this._layoutVertical(container, table);
            case Btk.BoxLayout.HORIZONTAL:
                this._layoutHorizontal(container, table);
            default:
                throw new Error("Unknown orientation");
        }
    },

    _layoutVertical: function(container, table) {
        for (var i = 0; i < container.children.length; i++) {
            var child = container.children[i];
            var tr = document.createElement("tr");
            table.appendChild(tr);
            var td = document.createElement("td");
            tr.appendChild(td);
            td.appendChild(child.content);
        } 
    },

    _layoutHorizontal: function(container, table) {
    }
});

Btk.VBoxLayout = Core.extend(Btk.BoxLayout, {
    $construct: function() {
        Btk.BoxLayout.call(this, Btk.BoxLayout.VERTICAL);
    }
});

Btk.HBoxLayout = Core.extend(Btk.BoxLayout, {
    $construct: function() {
        Btk.BoxLayout.call(this, Btk.BoxLayout.HORIZONTAL);
    }
});
