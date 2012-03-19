     define([
        "dojo/dom", "dojo/fx", "dojo/domReady!"
        ],
        function(dom, fx) {
          var oldText = {};
          return {
            setText: function(id, text) {
              var node = dom.byId(id);
              oldText[id] = node.innerHTML;
              node.innerHTML = text;
              fx.slideTo({
                top: 100,
                left: 200,
                node: node
              }).play();
            },
            restoreText: function(id) {
              var node = dom.byId(id);
              node.innerHTML = oldText[id];
              delete oldText[id];
            }
          }
        }
      );

