Ext.application({
    name: 'AM',

    appFolder: 'app',

    controllers: [
        'CustomerOrders'
    ],

    launch: function() {
        Ext.create('Ext.container.Viewport', {
            layout: 'fit',
            items: [
                {
                    xtype: 'customerorderlist'
                }
            ]
        });
    }
});