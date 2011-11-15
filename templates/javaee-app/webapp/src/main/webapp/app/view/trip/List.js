Ext.define('AM.view.trip.List', {
    extend: 'Ext.grid.Panel',
    alias : 'widget.triplist',

    title : 'All trips',
    store: AM.AppCtx.getStoreName('CustomerOrders'),

    initComponent: function() {
        this.columns = [
            {header: 'Customer Reference',  dataIndex: 'customerReference',  flex: 1},
            {header: 'Order Number', dataIndex: 'orderNumber', flex: 1},
            {header: 'Booking Date', dataIndex: 'bookingDate', flex: 1, xtype: 'datecolumn', format: 'd/m/y'}
        ];

        this.buttons = [
            {
                text: 'New',
                action: 'new'
            }
        ];

        this.callParent(arguments);

        this.getStore().load();
    }
});