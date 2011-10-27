Ext.define('AM.view.customerorder.Edit', {
    extend: 'Ext.form.Panel',
    alias : 'widget.customerorderedit',

    title : 'Edit Order',
    layout: 'fit',

    initComponent: function() {
        this.items = [
            {
                xtype: 'textfield',
                name : 'customerReference',
                fieldLabel: 'Customer reference'
            },
            {
                xtype: 'textfield',
                name : 'orderNumber',
                fieldLabel: 'Order number'
            }
        ];

        this.buttons = [
            {
                text: 'Save',
                action: 'save'
            },
            {
                text: 'Cancel',
                action: 'cancel'
            }
        ];

        this.callParent(arguments);
    }
});