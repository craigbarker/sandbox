Ext.application({
    name: 'AM',

    appFolder: 'app',

    controllers: [
        'CustomerOrders',
        'Viewport'
    ],

    views: [
        'Welcome'
    ],

    refs: [
        {
            ref: 'appContainer',
            selector: '#appContainer'
        }
    ],

    launch: function() {

        Ext.QuickTips.init();

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
                    xtype: 'panel',
                    itemId: 'appContainer',
                    layout: 'fit',
                    tbar: {
                        items: [
                            {
                                text: 'Orders',
                                action: 'orders'
                            },
                            {
                                text: 'Trips',
                                action: 'trips'
                            }
                        ]
                    }
                }
            ]
        });

        Ext.syncRequire('AM.view.Welcome');
        this.switchView(Ext.widget('customerorderlist'));

    },

    navigateBack: function(key, eventObject) {
        Ext.util.History.back();
    },

    navigateForward: function(key, eventObject) {
        Ext.util.History.forward();
    },

    onHistoryChange: function(token, opts) {
        if (token.match(/^customerorderlist/)) {
            this.switchView(Ext.widget('customerorderlist'));
        }
        else if (token.match(/^customerorderedit/)) {
            this.getController('CustomerOrders').editUser(token);
        }
    },

    switchView: function(widget) {
        var container = this.getAppContainer();
        container.removeAll(true);
        container.add(widget);
        container.doLayout();
    }
});
