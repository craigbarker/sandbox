Ext.define('AM.controller.CustomerOrders', {

    extend: 'AM.controller.AbstractController',

    stores: ['CustomerOrders'],
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
                click: this.handleSave
            },
            'customerorderedit button[action=delete]': {
                click: this.handleDelete
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

    handleSave: function(button) {
        var form = this.getForm().getForm();
        var record = form.getRecord();
        if (form.isValid()) {
            form.updateRecord(record);
            
            var me = this;
            
            var successFunction = function() {
                me.getTheStore().sync();
                me.goToList();
            }
            
            var failureFunction = function() {
                alert('There were validation errors');
            }
            
            var recordId = record.get('id');
            if (recordId == '') {
                record.save({
                    success: successFunction,
                    failure: failureFunction
                });
            } else {
                record.save({
                    success: successFunction,
                    failure: failureFunction
                });
            }
        } else {
            new Ext.window.MessageBox().alert('Errors', 'The form contains errors - please correct and re-submit');
        }
    },

    handleDelete: function(button) {
        this.getForm().getForm().getRecord().destroy();
        this.getTheStore().sync();
        this.goToList();
    },

    goToList: function() {
        this.switchViewByName('customerorderlist');
    },

    getTheStore: function() {
        return this.getStore(this.getStoreName());
    },

    getStoreName: function() {
        return 'CustomerOrders';
    }
});