Btk.Label = Core.extend({

    content: null,

    $construct: function(properties) {
        this.content = document.createElement("div");
        var text = "";
        if (properties && properties.text) {
            text = properties.text;
        }
        this.content.appendChild(document.createTextNode(text));
    }
});
