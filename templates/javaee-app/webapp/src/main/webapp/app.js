Ext.define("AM.AppCtx", {
    statics: {
        mode: 'test',

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
        'CustomerOrders'
    ],

    refs: [
        {
            ref: 'viewport',
            selector: 'viewport'
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
            layout: 'fit'
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
        var container = this.getViewport();
        container.removeAll(true);
        container.add(widget);
        container.doLayout();
    }
});
