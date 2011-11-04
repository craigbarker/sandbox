Ext.define('AM.store.CustomerOrders', {
    extend: 'Ext.data.Store',
    model: 'AM.model.CustomerOrder',
    autoLoad: false,
    proxy: {
        type: 'ajax',
        url: '/services/orders',
        reader: {
            type: 'json',
            root: 'orders'
        }
    }
});