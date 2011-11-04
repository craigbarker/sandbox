Ext.application({
    name: 'AM',

    appFolder: 'app',

    controllers: [
        'CustomerOrders'
    ],

    launch: function() {
        Ext.syncRequire("Ext.util.History");
        Ext.syncRequire("Ext.util.KeyMap");

        Ext.util.History.init();
        Ext.util.History.on({
            change: this.onHistoryChange,
            scope: this
        });

        var viewport = Ext.create('Ext.container.Viewport', {
            layout: 'fit',
            items: [
                {
                    xtype: 'customerorderlist'
                }
            ]
        });

        var map = new Ext.util.KeyMap(viewport.getEl(), [
            {
                key: Ext.EventObject.LEFT,
                alt: true,
                handler: this.navigateBack,
                scope: this
            }
        ]);

    },

    navigateBack: function(key, eventObject) {
        Ext.util.History.back();
    },

    onHistoryChange: function() {
        alert("History change");
    }
});