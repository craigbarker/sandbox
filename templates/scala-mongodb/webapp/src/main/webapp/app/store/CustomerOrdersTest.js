var customerOrders = [
        {
            id: 1,
            customerReference: 'CREF1',
            orderNumber: 'ORD1',
            bookingDate: '2011-11-07T13:23:12Z'
        },
        {
            id: 2,
            customerReference: 'CREF2',
            orderNumber: 'ORD2',
            bookingDate: '2011-11-08T13:23:12Z'
        },
        {
            id: 3,
            customerReference: 'CREF3',
            orderNumber: 'ORD3',
            bookingDate: '2011-11-09T13:23:12Z'
        }
    ];

Ext.define('AM.store.CustomerOrdersTest', {
    extend: 'Ext.data.Store',
    model: 'AM.model.CustomerOrder',
    autoLoad: false,
    proxy: {
        type: 'sessionstorage',
        id: 'customerorders'
    },
    constructor: function(config) {
        config = config || {};
        this.callParent([config]);
        if (this.count() == 0) {
            var model = Ext.create('AM.model.CustomerOrder', customerOrders[0]);
            this.add(model);
            this.sync();
        }
    }
});