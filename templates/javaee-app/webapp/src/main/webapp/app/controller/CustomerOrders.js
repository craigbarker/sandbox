Ext.define('AM.controller.CustomerOrders', {
    extend: 'Ext.app.Controller',

    stores: ['CustomerOrders'],
    models: ['CustomerOrder'],

    refs: [
        {
            ref: 'container',
            selector: '#topContainer'
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
            }
        });
    },

    editUser: function(grid, record) {
        var container = this.getContainer();
        container.removeAll();
        container.add(Ext.widget('customerorderedit'));
        container.doLayout();
    }
});