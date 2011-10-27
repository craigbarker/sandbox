Ext.define('AM.view.customerorder.List', {
    extend: 'Ext.grid.Panel',
    alias : 'widget.customerorderlist',

    title : 'All orders',
    store: 'CustomerOrders',

    initComponent: function() {
        this.columns = [
            {header: 'Customer Reference',  dataIndex: 'customerReference',  flex: 1},
            {header: 'Order Number', dataIndex: 'orderNumber', flex: 1}
        ];

        this.callParent(arguments);
    }
});