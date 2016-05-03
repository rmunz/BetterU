/*
 * Holds the dialog properties for all dialogs used.
 */
$(function () {
    // Disable focus
    $.ui.dialog.prototype._focusTabbable = function () {};

    // Dialog properties
    $("#delete-dialog").dialog({
        autoOpen: false,
        height: 225,
        width: 400,
        resizable: false,
        draggable: false,
        modal: true
    });
});

/*
 * Closes all dialogs and applies necessary styles on close.
 */ 
function closeDialogs() {
    // Close all dialogs
    $("#delete-dialog").dialog("close");
}