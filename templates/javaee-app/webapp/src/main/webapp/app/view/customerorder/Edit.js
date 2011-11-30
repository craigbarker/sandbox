Ext.define('AM.view.customerorder.Edit', {
    extend: 'Ext.form.Panel',
    alias : 'widget.customerorderedit',

    title : 'Edit Order',

    initComponent: function() {

        this.initialConfig = {
            url: '/services/order'
        };

        this.items = [
            {
                xtype: 'hidden',
                name: 'id'
            },
            {
                xtype: 'textfield',
                name : 'customerReference',
                fieldLabel: 'Customer reference',
                itemId: 'customerReferenceField'
            },
            {
                xtype: 'textfield',
                name : 'orderNumber',
                fieldLabel: 'Order number'
            },
            {
                xtype: 'datefield',
                name: 'bookingDate',
                fieldLabel: 'Booking date',
                format: 'd/m/y',
                submitFormat: 'c'
            }
        ];

        var saveButton = new Ext.button.Button({
            text: 'Save',
            action: 'save'
        });

        var cancelButton = new Ext.button.Button({
            text: 'Cancel',
            action: 'cancel'
        });

        var deleteButton = new Ext.button.Button({
            text: 'Delete',
            action: 'delete'
        });

        this.buttons = [
            cancelButton,
            saveButton,
            deleteButton
        ];

        this.on(
            'render',
            function(){
                var map = new Ext.util.KeyMap(this.getEl(), [
                    {
                        key: Ext.EventObject.ESC,
                        handler: function() {
                            cancelButton.fireEvent('click');
                        },
                        scope: this
                    },
                    {
                        key: Ext.EventObject.ENTER,
                        handler: function() {
                            saveButton.fireEvent('click');
                        },
                        scope: this
                    }
                ]);
                this.getComponent('customerReferenceField').focus(true, true);
            },
            this
        );


        this.callParent(arguments);
    }
});