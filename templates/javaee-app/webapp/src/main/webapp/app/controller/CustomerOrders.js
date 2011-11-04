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
        },
        {
            ref: 'form',
            selector: 'customerorderedit'
        }
    ],

    views: [
        'customerorder.List',
        'customerorder.Edit'
    ],

    init: function() {
        this.control({
            'customerorderlist': {
                itemdblclick: this.editUser,
                render: this.listRendered
            },
            'customerorderedit': {
                render: this.editRendered
            },
            'customerorderedit button[action=cancel]': {
                click: this.goToList
            },
            'customerorderedit button[action=save]': {
                click: this.save
            }
        });
    },

    listRendered: function() {
        Ext.util.History.add('customerorderlist');
    },

    editRendered: function() {
        Ext.util.History.add('customerorderedit');
    },

    editUser: function(grid, record) {
        var view = Ext.widget('customerorderedit');
        view.loadRecord(record);
        this.switchView(view);
    },

    goToList: function() {
        this.switchView(Ext.widget('customerorderlist'));
    },

    save: function(button) {
        var form = this.getForm().getForm();
        if (form.isValid()) {
            var model = form.getRecord();
            model.set(form.getValues());
            model.save({
                scope: this,
                success: function() {
                    this.goToList();
                },
                failure: function() {
                    Ext.Msg.alert("Failure!");
                }
            });
        }
    },

    switchView: function(widget) {
        var container = this.getViewport();
        container.removeAll(true);
        container.add(widget);
        container.doLayout();
    }
});