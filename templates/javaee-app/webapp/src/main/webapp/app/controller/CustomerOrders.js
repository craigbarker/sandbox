Ext.define('AM.controller.CustomerOrders', {
    extend: 'Ext.app.Controller',

    stores: ['CustomerOrders'],
    models: ['CustomerOrder'],

    views: [
        'customerorder.List'
    ],

    init: function() {
        this.control({
            'customerorderlist': {
                itemdblclick: this.editUser
            }
        });
    },

    editUser: function(grid, record) {
        alert("Edit order!")
    }
});