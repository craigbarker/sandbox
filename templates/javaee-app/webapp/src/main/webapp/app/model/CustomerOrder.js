Ext.define('AM.model.CustomerOrder', {
    extend: 'Ext.data.Model',
    fields: [
        'id',
        {name: 'customerReference'},
        {name: 'orderNumber'},
        {name: 'bookingDate', type: Ext.data.Types.DATE, dateFormat: 'c'}
    ]
});