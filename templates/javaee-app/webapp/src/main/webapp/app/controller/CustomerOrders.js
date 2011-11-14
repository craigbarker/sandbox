Ext.define('AM.controller.CustomerOrders', {
    extend: 'Ext.app.Controller',

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
                click: this.goToList
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

    goToList: function() {
//        Ext.util.History.back();
        this.switchView(Ext.widget('customerorderlist'));
    },

    save: function(button) {
        var form = this.getForm().getForm();
        if (form.isValid()) {
            var store = this.getTheStore();
            var formRecord = form.getRecord();

            form.updateRecord(formRecord);

            var recordId = formRecord.get('id');
            if (recordId == '') {
                store.insert(0, formRecord);
            }

            store.sync();

            this.goToList();
        }
    },

    switchView: function(widget) {
        var container = this.getAppContainer();
        container.removeAll(true);
        container.add(widget);
        container.doLayout();
    },

    getTheStore: function() {
        return this.getStore(this.getStoreName());
    },

    getStoreName: function() {
        return AM.AppCtx.getStoreName('CustomerOrders');
    }
});