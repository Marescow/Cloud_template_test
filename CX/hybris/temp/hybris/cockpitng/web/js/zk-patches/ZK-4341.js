/**
 * When typing a date into Datebox with Calendar open, pressing space causes the date to be replaced with current date.
 */
zk.afterLoad('zul.db', function () {
	var xDatebox = {};
	zk.override(zul.db.Datebox.prototype, xDatebox, {
		_doKeyDown: function (evt) {
			if (evt.keyCode !== 32) {
				xDatebox._doKeyDown.apply(this, arguments);
			}
		}
	});//zk.override
});//zk.afterLoad
