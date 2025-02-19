/**
 * Purpose: make a modal window (message box) not focusing on its child component by default.
 * Based on version: 8.6.3
 */
zk.afterLoad('zul.wnd', function() {
	var _modals = [], _lastfocus;
	function _markModal(wgt) {
		zk.currentModal = wgt;
		var wnd = _modals[0], fc = zk.currentFocus;
		if (wnd){
			wnd._lastfocus = fc;
		}
		else{ _lastfocus = fc;}
		_modals.unshift(wgt);
	}

	function _doOverlapped(wgt) {
		var pos = wgt._position,
			n = wgt.$n(),
			$n = zk(n);
		if (!pos && (!n.style.top || !n.style.left)) {
			var xy = $n.revisedOffset();
			//ZK-1391: use revisedOffset() only if style doesn't specify left/top value
			if (!n.style.left) {
				n.style.left = jq.px(xy[0]);
			}
			if (!n.style.top) {
				n.style.top = jq.px(xy[1]);
			}
		} else if (pos === 'parent'){
			_posByParent(wgt);
		}

		$n.makeVParent();
		// B70-ZK-2067: Should pass widget as parameter not DOM element.
		zWatch.fireDown('onVParent', wgt);

		wgt.zsync();
		_updDomPos(wgt);
		wgt.setTopmost();
		_makeFloat(wgt);
	}

	function _doModal(wgt) {
		var pos = wgt._position,
			n = wgt.$n(),
			$n = zk(n);
		if (pos == 'parent') _posByParent(wgt);

		$n.makeVParent();
		// B70-ZK-2067: Should pass widget as parameter not DOM element.
		zWatch.fireDown('onVParent', wgt);

		wgt.zsync();
		_updDomPos(wgt, true, false, true);

		//Note: modal must be visible
		var realVisible = wgt.isRealVisible();
		wgt.setTopmost();

		if (!wgt._mask) {
			var anchor = wgt._shadowWgt ? wgt._shadowWgt.getBottomElement() : null;
			wgt._mask = new zk.eff.FullMask({
				id: wgt.uuid + '-mask',
				anchor: anchor ? anchor : wgt.$n(),
				//bug 1510218: we have to make it as a sibling
				zIndex: wgt._zIndex,
				visible: realVisible
			});
			var tag = zk.ie < 11 || zk.gecko ? 'a' : 'button';
			jq('#' + wgt.uuid + '-mask').append('<' + tag + ' id="' + wgt.uuid + '-mask-a" style="top:0;left:0 onclick="return false;" href="javascript:;" class="z-focus-a"></' + tag + '>');
			wgt._anchor = jq('#' + wgt.uuid + '-mask-a')[0];
		}
		if (realVisible)
			_markModal(wgt);

		_makeFloat(wgt);
	}
	function _doModal(wgt) {
		var pos = wgt._position,
			n = wgt.$n(),
			$n = zk(n);
		if (pos === 'parent') {
			_posByParent(wgt);
		}

		$n.makeVParent();
		// B70-ZK-2067: Should pass widget as parameter not DOM element.
		zWatch.fireDown('onVParent', wgt);

		wgt.zsync();
		_updDomPos(wgt, true, false, true);

		//Note: modal must be visible
		var realVisible = wgt.isRealVisible();
		wgt.setTopmost();

		if (!wgt._mask) {
			var anchor = wgt._shadowWgt ? wgt._shadowWgt.getBottomElement() : null;
			wgt._mask = new zk.eff.FullMask({
				id: wgt.uuid + '-mask',
				anchor: anchor ? anchor : wgt.$n(),
				//bug 1510218: we have to make it as a sibling
				zIndex: wgt._zIndex,
				visible: realVisible
			});
			var tag = zk.ie < 11 || zk.gecko ? 'a' : 'button';
			jq(`#${wgt.uuid}-mask`).append(`<${tag} id="${wgt.uuid}-mask-a" style="top:0;left:0" onclick="return false;" href="javascript:;" class="z-focus-a"></${tag}>`);
			wgt._anchor = jq(`#${wgt.uuid}-mask-a`)[0];
		}
		if (realVisible){
			_markModal(wgt);
		}

		_makeFloat(wgt);
	}

	function _isModal(mode) {
		return mode === 'modal' || mode === 'highlighted';
	}

	//minTop - whether to at most 100px
	function _updDomPos(wgt, force, posParent, minTop) {
		if (!wgt.desktop || wgt._mode === 'embedded'){
			return;
		}

		var n = wgt.$n(), pos = wgt._position;
		if (pos === 'parent') {
			if (posParent){
				_posByParent(wgt);
			}
			return;
		}
		if (!pos && !force){
			return;
		}

		var n = wgt.$n(), pos = wgt._position;
		var st = wgt.$n().style;
		st.position = 'absolute'; //just in case
		var ol = st.left, ot = st.top;
		if (pos !== 'nocenter'){
			zk(n).center(pos);
		}
		var sdw = wgt._shadowWgt;
		if (pos && sdw) {
			var opts = sdw.opts, l = n.offsetLeft, t = n.offsetTop;
			if (pos.indexOf('left') >= 0 && opts.left < 0){
				st.left = jq.px(l - opts.left);
			} else if (pos.indexOf('right') >= 0 && opts.right > 0){
				st.left = jq.px(l - opts.right);
			}
			if (pos.indexOf('top') >= 0 && opts.top < 0){
				st.top = jq.px(t - opts.top);
			} else if (pos.indexOf('bottom') >= 0 && opts.bottom > 0){
				st.top = jq.px(t - opts.bottom);
			}
		}

		if (minTop && !pos) { //adjust y (to upper location)
			var top = zk.parseInt(n.style.top), y = jq.innerY();
			if (y) {
				var y1 = top - y;
				if (y1 > 100) {
					n.style.top = jq.px0(top - (y1 - 100));
				}
			} else if (top > 100){
				n.style.top = '100px';
			}
		}

		wgt.zsync();
		if (ol !== st.left || ot !== st.top){
			wgt._fireOnMove();
		}
	}

	function _makeSizer(wgt) {
		if (!wgt._sizer) {
			wgt.domListen_(wgt.$n(), 'onMouseMove');
			wgt.domListen_(wgt.$n(), 'onMouseOut');
			var Window = wgt.$class;
			wgt._sizer = new zk.Draggable(wgt, null, {
				stackup: true,
				overlay: true, // ZK-817
				draw: Window._drawsizing,
				snap: Window._snapsizing,
				initSensitivity: 0,
				starteffect: Window._startsizing,
				ghosting: Window._ghostsizing,
				endghosting: Window._endghostsizing,
				ignoredrag: Window._ignoresizing,
				endeffect: Window._aftersizing});
		}
	}

	function _makeFloat(wgt) {
		var handle = wgt.$n('cap');
		if (handle && !wgt._drag) {
			jq(handle).addClass(wgt.getZclass() + '-header-move');
			var Window = wgt.$class;
			wgt._drag = new zk.Draggable(wgt, null, {
				handle: handle, stackup: true,
				fireOnMove: false,
				starteffect: Window._startmove,
				ghosting: Window._ghostmove,
				endghosting: Window._endghostmove,
				ignoredrag: Window._ignoremove,
				endeffect: Window._aftermove,
				zIndex: 99999 //Bug 2929590
			});
		}
	}

	function _posByParent(wgt) {
		var n = wgt.$n(),
			ofs = zk(zk(n).vparentNode(true)).revisedOffset();
		wgt._offset = ofs;
		n.style.left = jq.px(ofs[0] + zk.parseInt(wgt._left));
		n.style.top = jq.px(ofs[1] + zk.parseInt(wgt._top));
	}

	var exWidget = {};
	zk.override(zul.wnd.Window.prototype, exWidget, {
		bind_: function (desktop, skipper, after) {
			this.$supers('bind_', arguments);

			var mode = this._mode;
			zWatch.listen({onSize: this});

			if (mode !== 'embedded') {
				zWatch.listen({onFloatUp: this, onHide: this, onShow: this});
				this.setFloating_(true);

				if (_isModal(mode)) {
					_doModal(this);
				}
				else {
					_doOverlapped(this);
				}
			}

			if (this._sizable){
				_makeSizer(this);
			}

			if (this._maximizable && this._maximized) {
				var self = this;
				after.push(function () {
					self._maximized = false;
					self.setMaximized(true, true);
				});
			}

			if (this._mode !== 'embedded' && (!zk.css3)) {
				jq.onzsync(this); //sync shadow if it is implemented with div
				zWatch.listen({onResponse: this});
			}
			zWatch.listen({onCommandReady: this});
		},
	});

});

if(zk.version !== '8.6.3'){
	console.warn('This override script version compatibility was tested for ZK 8.6.3. If you are running a different version, please check this script compatibility ');
}
