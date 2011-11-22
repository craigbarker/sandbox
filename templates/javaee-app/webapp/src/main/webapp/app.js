Ext.define("AM.AppCtx", {
    statics: {
        mode: 'prod',

        getStoreName: function(storeName) {
            var ret = storeName;
            if (this.mode == 'test') {
                ret += "Test";
            }
            return ret;
        }
    }
});

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

        var map = new Ext.util.KeyMap(viewport.getEl(), [
            {
                key: Ext.EventObject.LEFT,
                alt: true,
                handler: this.navigateBack,
                scope: this
            },
            {
                key: Ext.EventObject.RIGHT,
                alt: true,
                handler: this.navigateForward,
                scope: this
            }
        ]);

//        Ext.util.History.add('customerorderlist');

        Ext.syncRequire('AM.view.Welcome');
        this.switchView(Ext.widget('welcome'));

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
