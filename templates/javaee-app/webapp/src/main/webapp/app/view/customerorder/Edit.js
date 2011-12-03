Ext.define('AM.view.customerorder.Edit', {
    extend: 'Ext.form.Panel',
    alias : 'widget.customerorderedit',

    title : 'Edit Order',

    fieldDefaults: {
        msgTarget: 'side'
    },

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
                itemId: 'customerReferenceField',
                form: this,
                validator: this.validateField
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
    },

    validateField: function(value) {
        var model = this.form.getRecord();
        if (!model) {
            return;
        }
        model.beginEdit();
        model.set(this.name, value);
        var modelErrors = model.validate();
        var errors = modelErrors.getByField(this.name);
        if (errors && errors.length > 0) {
            var msgs = [];
            Ext.Array.each(
                errors,
                function(msg) {
                    msgs.push(msg.message);
                }
            );
            this.markInvalid(msgs);
        } else {
            this.clearInvalid();
        }
        model.cancelEdit();
    }
});