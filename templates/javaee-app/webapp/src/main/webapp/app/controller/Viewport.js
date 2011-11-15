Ext.define('AM.controller.Viewport', {
    extend: 'AM.controller.AbstractController',

    views: [
        'customerorder.List',
        'trip.List'
    ],

    init: function() {
        this.control({
            '#appContainer button[action=orders]': {
                click: function() {this.switchViewByName('customerorderlist')}
            },
            '#appContainer button[action=trips]': {
                click: function() {this.switchViewByName('triplist')}
            }
        });
    }

});