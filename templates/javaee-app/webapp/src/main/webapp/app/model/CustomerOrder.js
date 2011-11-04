Ext.define('AM.model.CustomerOrder', {
    extend: 'Ext.data.Model',
    fields: ['id', 'customerReference', 'orderNumber', 'bookingDate'],
    proxy: {
        type: 'rest',
        url: '/services/orders'
    }
});