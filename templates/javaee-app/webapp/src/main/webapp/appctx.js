Ext.define("AM.AppCtx", {
    statics: {
        mode: 'local',

        getProxy: function(storeName) {
            if (this.mode == 'local') {
                return {
                    type: 'sessionstorage',
                    id: storeName
                };
            }
            else {
                return {
                    type: 'rest',
                    url: '/webapp/services/' + storeName,
                    reader: {
                        type: 'json',
                        root: storeName
                    }
                }
            }
        }
    }
});