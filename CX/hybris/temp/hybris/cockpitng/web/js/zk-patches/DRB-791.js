/**
 * tested on 8.6.3
 */
zk.afterLoad( function() {
    var _xzjq = {};
    zk.override(zjq.prototype, _xzjq, {
         undoVParent: function() {
              if(!this.hasVParent()){
                   return;
              }
              var result = _xzjq.undoVParent.apply(this, arguments);
              return result;
         }
    });
});