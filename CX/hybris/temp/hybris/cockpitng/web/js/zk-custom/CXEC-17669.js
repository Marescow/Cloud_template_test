/*
 * Copyright (c) 2022 SAP SE or an SAP affiliate company. All rights reserved
 */

/**
 * Customized for the zul.inp.Textbox to hidden content when the input is supposed to a password input without autofill
 */
 zk.afterLoad('zul.inp', function () {

    const _xTextbox = {};
    const TYPE = "type";
    const CLASS = "class";
    const YE_PASSWORD = "ye-password";

    const isPasswordInputWithTextType = (component) => {
        let isPasswordInput = false;
        let isTextType = false;
        try {
            const input = component.getInputNode();
            isPasswordInput = input && input.getAttribute(CLASS) && input.getAttribute(CLASS).indexOf(YE_PASSWORD) !== -1;
            isTextType = input && input.getAttribute(TYPE) && input.getAttribute(TYPE) === "text";
        }
        catch (e) {
            console.error(e);
        }
        return isPasswordInput && isTextType;
    };

    const replaceActualValue = (component) => {
        try {
            const input = component.getInputNode();
            const newChangedValue = input.value.replaceAll("•", "");
            const start = input.selectionStart;
            const startNumber = start - newChangedValue.length;
            const endNumber = input.value.length - startNumber - newChangedValue.length;
            const actualValue = component._actualValue || "";

            component._actualValue = actualValue.substring(0, startNumber) + newChangedValue + actualValue.substring(actualValue.length - endNumber);

            // Replace input actual value with "•"
            input.value = "•".repeat(component._actualValue.length);

            // Move caret back to the palce of changed, since every set value for input ZK will automatically set caret to the end
            input.selectionStart = start;
            input.selectionEnd = start;
        }
        catch (e) {
            console.error(e);
        }
    };

    zk.override(zul.inp.Textbox.prototype, _xTextbox, {
        _updateValue: function () {
            if (isPasswordInputWithTextType(this)) {
                replaceActualValue(this);
            }
            _xTextbox._updateValue.apply(this, arguments);
        },
        //Determining whether an onchange event is fired
        _equalValue: function (newValue, oldValue) {
            if (this._actualValue && isPasswordInputWithTextType(this)) {
                return this._actualValue === oldValue;
            }
            return newValue === oldValue || this.marshall_(newValue) === this.marshall_(oldValue);
        },
        //Transform the UI value into a server value
        marshall_: function (val) {
            if (this._actualValue && isPasswordInputWithTextType(this)) {
                return this._actualValue;
            }
            return val;
        },
        //Transforms a server value into the UI value
        unmarshall_: function (val) {
            if (isPasswordInputWithTextType(this)) {
                this._actualValue = val;
                return "•".repeat(val.length);
            }
            return val;
        }
    });
});
