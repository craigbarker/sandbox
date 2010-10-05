Btk.View = Core.extend(Btk.Panel, {
    
    parent: null,

    $construct: function(properties) {
        Btk.Panel.call(this, properties);
        if (properties && properties.parent) {
            this.parent = properties.parent;
        }
        if (parent == null) {
            throw new Exception("'parent' property must be defined for a View");
        }
    }
});
