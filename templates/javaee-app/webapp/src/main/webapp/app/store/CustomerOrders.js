Ext.define('AM.store.CustomerOrders', {
    extend: 'Ext.data.Store',
    model: 'AM.model.CustomerOrder',
    autoLoad: false,
    storeId: 'customerOrders',
    proxy: {
        type: 'rest',
        url: '/services/orders',
        reader: {
            type: 'json',
            root: 'orders'
        }
    }
});