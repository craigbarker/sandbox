Ext.define("AM.AppCtx", {
    statics: {
        mode: 'remote',

        getProxy: function(storeName) {
            if (this.mode == 'local') {
                return {
                    type: 'sessionstorage',
                    id: storeName
                };
            }
            else if (this.mode == 'remote') {
                return {
                    type: 'rest',
                    url: '/webapp/services/' + storeName,
                    reader: {
                        type: 'json',
                        root: storeName
                    }
                }
            }
            else throw new Error("Incorrect mode: " + mode)
        }
    }
});