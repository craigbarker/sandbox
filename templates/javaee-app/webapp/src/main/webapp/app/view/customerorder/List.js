Ext.define('AM.view.customerorder.List', {
    extend: 'Ext.grid.Panel',
    alias : 'widget.customerorderlist',

    title : 'All orders',
    store: 'CustomerOrders',

    initComponent: function() {
        this.columns = [
            {header: 'Customer Reference',  dataIndex: 'customerReference',  flex: 1},
            {header: 'Order Number', dataIndex: 'orderNumber', flex: 1},
            {header: 'Booking Date', dataIndex: 'bookingDate', flex: 1, xtype: 'datecolumn', format: 'd/m/y'}
        ];

        var newButton = new Ext.button.Button({
            text: 'New',
            action: 'new'
        });

        this.buttons = [
            newButton
        ];

        this.on(
            'render',
            function(){
                var map = new Ext.util.KeyMap(this.getEl(), [
                    {
                        key: Ext.EventObject.N,
                        alt: true,
                        handler: function() {
                            newButton.fireEvent('click');
                        },
                        scope: this
                    }
                ]);
                newButton.focus(false, true);
            },
            this
        );

        this.callParent(arguments);

        this.getStore().load();
    }
});