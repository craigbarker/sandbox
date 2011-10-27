Ext.define('AM.controller.CustomerOrders', {
    extend: 'Ext.app.Controller',

    stores: ['CustomerOrders'],
    models: ['CustomerOrder'],

    refs: [
        {
            ref: 'container',
            selector: '#topContainer'
        },
        {
            ref: 'viewport',
            selector: 'viewport'
        }
    ],

    views: [
        'customerorder.List',
        'customerorder.Edit'
    ],

    init: function() {
        this.control({
            'customerorderlist': {
                itemdblclick: this.editUser
            },
            'customerorderedit button[action=cancel]': {
                click: this.goToList
            }
        });
    },

    editUser: function(grid, record) {
        this.switchView(Ext.widget('customerorderedit'));
    },

    goToList: function() {
        this.switchView(Ext.widget('customerorderlist'));
    },

    switchView: function(widget) {
        var container = this.getViewport();
        container.removeAll();
        container.add(widget);
        container.doLayout();
    }
});