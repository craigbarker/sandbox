Btk.Panel = Core.extend({

    parent: null,
    content: null,
    _layout: null,
    children: null,
    layoutContainer: null,

    $construct: function(properties) {
        if (properties) {
            this.parent = properties.parent;
            this._layout = properties.layout;
            if (properties.children) {
                this.children = properties.children;
            }
        }
        if (this._layout == null) {
            throw new Exception("Layout manager must be specified on a panel");
        }

        if (this.children == null) {
            this.children = new Array();
        }

        this.content = document.createElement("div");
    },

    addChild: function(child) {
        this.children.add(child);
    },

    doLayout: function() {
        if (this._layout != null) {
            this._layout.layout(this);
        }
    }
});
