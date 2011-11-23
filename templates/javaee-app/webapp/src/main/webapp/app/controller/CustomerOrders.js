Ext.define('AM.controller.CustomerOrders', {

    extend: 'AM.controller.AbstractController',

    stores: [AM.AppCtx.getStoreName('CustomerOrders')],
    models: ['CustomerOrder'],

    refs: [
        {
            ref: 'appContainer',
            selector: '#appContainer'
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
                itemdblclick: this.editUserFromRecord
            },
            'customerorderlist button[action=new]': {
                click: this.handleNew
            },
            'customerorderedit button[action=cancel]': {
                click: function(){this.switchViewByName('customerorderlist')}
            },
            'customerorderedit button[action=save]': {
                click: this.save
            }
        });
    },

    handleNew: function() {
        var record = Ext.create('AM.model.CustomerOrder');
        var view = Ext.widget('customerorderedit');
        view.loadRecord(record);
        this.switchView(view);
    },

    fireEditUser: function(grid, record) {
        var token = 'customerorderedit:id=' + record.internalId;
        Ext.util.History.add(token);
    },

    editUserFromRecord: function(grid, record) {
        var view = Ext.widget('customerorderedit');
        view.loadRecord(record);
        this.switchView(view);
    },

    editUser: function(historytoken) {
        var view = Ext.widget('customerorderedit');
        var store = this.getStore(AM.AppCtx.getStoreName('CustomerOrders'));

        var re = /customerorderedit:id=(\d+)/;
        var matches = re.exec(historytoken);
        var id = matches[1];

        var record = store.findRecord('id', id);

        view.loadRecord(record);
        this.switchView(view);
    },

    save: function(button) {
        var form = this.getForm().getForm();
        if (form.isValid()) {
            var store = this.getTheStore();
            var formRecord = form.getRecord();

            form.updateRecord(formRecord);

            var recordId = formRecord.get('id');
            if (recordId == '') {
                formRecord.save({
                    success: function() {
                        store.sync();
                    }
                });
            } else {
                store.sync();
            }

            this.switchViewByName('customerorderlist');
        }
    },

    getTheStore: function() {
        return this.getStore(this.getStoreName());
    },

    getStoreName: function() {
        return AM.AppCtx.getStoreName('CustomerOrders');
    }
});