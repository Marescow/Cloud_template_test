CKEDITOR.editorConfig = function(config) {

    config.baseFloatZIndex = 15000;
    config.entities = false;

    config.toolbar = [
        { name: 'styles', items: [ 'Format' ] },
        { name: 'basicstyles', items: [ 'Bold', 'Italic', 'Subscript', 'Superscript' ] },
        { name: 'links', items: [ 'Link' ] },
        { name: 'paragraph', items: [ 'BulletedList' ] },
        { name: 'document', items: [ 'Source' ] },
        { name: 'clipboard', items: [ 'Undo', 'Redo' ] },
        { name: 'specialcharacters', items: [ 'SpecialChar' ] }
    ];

    config.specialChars = characters;
    config.contentsCss = 'widgetClasspathResource/widgets/editors/wysiwygEditor/validationstyles.css';
};

CKEDITOR.on('instanceReady', function( ev ) {
    ev.editor.dataProcessor.writer.setRules( 'p', {
       breakAfterClose : false
    });

    resetWysiwygEditorStyles();

    ev.editor.on('mode', () => {
        resetWysiwygEditorStyles();
    });
});

function resetWysiwygEditorStyles() {
    var wysiwygIframes = document.getElementsByClassName("cke_wysiwyg_frame");
    if (wysiwygIframes.length > 0) {
        var textColor = getComputedStyle(document.documentElement).getPropertyValue('--bo-text-color');
        var fontFamily = getComputedStyle(document.documentElement).getPropertyValue('--bo-font-family');
        for (var iframe of wysiwygIframes) {
            var textBody = iframe.contentDocument.getElementsByClassName("cke_editable")[0];
            if (textBody) {
                textBody.style.color = textColor;
                textBody.style.fontFamily = fontFamily;
            }
        }
    }
}

let characters = [
    '!', '&quot;', '#', '$', '%', '&amp;', "'", '(', ')', '*', '+', '-', '.', '/', '{', '|', '}', '~',
    '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', ':', ';', '&le;', '&lt;','=', '&gt;', '&ge;', '?', '@',
    'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S',
    'T', 'U', 'V', 'W', 'X', 'Y', 'Z', '[', ']', '^', '_', '`',
    'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p',  'q', 'r', 's',
    't', 'u', 'v', 'w', 'x', 'y', 'z',
    '&deg;', '&sup3;', '&sup2;', '&plusmn;', '&Delta;', '&prod;', '&sum;','&Pi;', '&radic;', '&infin;','&int;', '&ohm;',
    '&ne;','&euro;', '&lsquo;', '&rsquo;', '&ldquo;', '&rdquo;', '&ndash;', '&mdash;', '&iexcl;', '&cent;', '&pound;',
    '&curren;', '&yen;', '&brvbar;', '&sect;', '&uml;', '&copy;', '&ordf;', '&laquo;', '&not;', '&reg;', '&macr;',
    '&deg;', '&sup2;', '&sup3;', '&acute;', '&micro;', '&para;', '&middot;', '&cedil;', '&sup1;', '&ordm;', '&raquo;',
    '&frac14;', '&frac12;', '&frac34;', '&iquest;', '&Agrave;', '&Aacute;', '&Acirc;', '&Atilde;', '&Auml;', '&Aring;',
    '&AElig;', '&Ccedil;', '&Egrave;', '&Eacute;', '&Ecirc;', '&Euml;', '&Igrave;', '&Iacute;', '&Icirc;', '&Iuml;',
    '&ETH;', '&Ntilde;', '&Ograve;', '&Oacute;', '&Ocirc;', '&Otilde;', '&Ouml;', '&times;', '&Oslash;', '&Ugrave;',
    '&Uacute;', '&Ucirc;', '&Uuml;', '&Yacute;', '&THORN;', '&szlig;', '&agrave;', '&aacute;', '&acirc;', '&atilde;',
    '&auml;', '&aring;', '&aelig;', '&ccedil;', '&egrave;', '&eacute;', '&ecirc;', '&euml;', '&igrave;', '&iacute;',
    '&icirc;', '&iuml;', '&eth;', '&ntilde;', '&ograve;', '&oacute;', '&ocirc;', '&otilde;', '&ouml;', '&divide;',
    '&oslash;', '&ugrave;', '&uacute;', '&ucirc;', '&uuml;', '&yacute;', '&thorn;', '&yuml;', '&OElig;', '&oelig;',
    '&#372;', '&#374', '&#373', '&#375;', '&sbquo;', '&#8219;', '&bdquo;', '&hellip;', '&trade;', '&#9658;', '&bull;',
    '&rarr;', '&rArr;', '&hArr;', '&diams;', '&asymp;',
];
