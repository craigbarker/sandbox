Ext.define('AM.controller.Viewport', {
    extend: 'Ext.app.Controller',

    views: [
        'customerorder.List',
        'trip.List'
    ],

    refs: [
        {
            ref: 'appContainer',
            selector: '#appContainer'
        }
    ],

    init: function() {
        this.control({
            '#ordersButton': {
                click: this.switchView(Ext.widget('customerorderlist'))
            },
            '#tripsButton': {
                click: this.switchView(Ext.widget('triplist'))
            }
        });
    },

    switchView: function(widget) {
        var container = this.getAppContainer();
        container.removeAll(true);
        container.add(widget);
        container.doLayout();
    }
});