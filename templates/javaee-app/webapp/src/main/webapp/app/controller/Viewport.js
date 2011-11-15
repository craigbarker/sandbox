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
            '#appContainer button[action=orders]': {
                click: this.goToOrders
            },
            '#appContainer button[action=trips]': {
                click: this.goToTrips
            }
        });
    },

    goToOrders: function() {
        this.switchView(Ext.widget('customerorderlist'));
    },

    goToTrips: function() {
        this.switchView(Ext.widget('triplist'));
    },

    switchView: function(widget) {
        var container = this.getAppContainer();
        container.removeAll(true);
        container.add(widget);
        container.doLayout();
    }
});