/**
 * tested on 8.6.0.1
 */
zk.afterLoad('zul.sel', function () {
	var globalDragListener = {
		onStartDrag: function (drag, event) {
			var draggedWgt = drag.origin.control;
			if (draggedWgt._isRodDndEnabled()) {
				draggedWgt.fire("onStartDrag", {}, {toServer: true});
			}
		}
	};
	zWatch.listen({
		onStartDrag: globalDragListener
	});

	var xWidget = {};
	zk.override(zk.Widget.prototype, xWidget, {
		bind_: function () {
			var result = xWidget.bind_.apply(this, arguments);
			if (this.getDroppable()) {
				this.listen({onDrop: this._adjustEventOnDrop});
			}
			return result;
		},

		_isRodDndEnabled: function () {
			for (var wgt = this; wgt; wgt = wgt.parent) {
				if (wgt._rodDndEnabled !== undefined) {
					return wgt._rodDndEnabled;
				}
			}
			return false;
		},

		_adjustEventOnDrop: function (event) {
			//replace the dragged element with an existing widget, to be ignored at server side
			if (this._isRodDndEnabled()) {
				event.data.dragged = event.target;
			}
		},

		unbind_: function () {
			this.unlisten({onDrop: this._adjustEventOnDrop});
			return xWidget.unbind_.apply(this, arguments);
		}
	});//zk.override
});//zk.afterLoad