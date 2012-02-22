Ext.define('AM.controller.AbstractController', {
    extend: 'Ext.app.Controller',

    refs: [
        {
            ref: 'appContainer',
            selector: '#appContainer'
        }
    ],

    switchViewByName: function(widgetName) {
        var widget = Ext.widget(widgetName);
        this.switchView(widget);
    },

    switchView: function(widget) {
        var container = this.getAppContainer();
        container.removeAll(true);
        container.add(widget);
        container.doLayout();
    }
});