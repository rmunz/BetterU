
$(document).ready(function () {
    // Remove styles coming from primefaces on the profile save button
    $("#form-profile\\:edit-profile-save-btn").removeClass();
    $("#form-profile\\:edit-profile-save-btn").addClass("btn noupdate");
    $("#form-profile\\:edit-profile-save-btn").bind("mouseover mousedown mouseup focus active", function() {
        $("#form-profile\\:edit-profile-save-btn").removeClass();
        $("#form-profile\\:edit-profile-save-btn").addClass("btn noupdate");
    });
    
    // Remove styles coming from primefaces on the advanced save button
    $("#form-advanced\\:edit-advanced-save-btn").removeClass();
    $("#form-advanced\\:edit-advanced-save-btn").addClass("btn noupdate");
    $("#form-advanced\\:edit-advanced-save-btn").bind("mouseover mousedown mouseup focus active", function() {
        $("#form-advanced\\:edit-advanced-save-btn").removeClass();
        $("#form-advanced\\:edit-advanced-save-btn").addClass("btn noupdate");
    });
    
    // Hide password and confirm password
    $("#advanced-table tr:last td").hide();
    $("#advanced-table tr:last").prev().find("td").hide();
    
    // Hide profile save and cancel buttons
    $("#form-profile\\:edit-profile-save-btn").hide();
    $("#form-profile\\:edit-profile-cancel-btn").hide();
    $("#form-advanced\\:edit-advanced-save-btn").hide();
    $("#form-advanced\\:edit-advanced-cancel-btn").hide();
    // Disable profile fields
    changeProfileFields(true);
    changeAdvancedFields(true);
    
    // Edit profile button click
    $("#form-profile\\:edit-profile-btn").bind("click", function(e) {
        // Enable fields
        changeProfileFields(false);
        // Show/hide appropriate buttons
        $("#form-profile\\:edit-profile-btn").hide();
        $("#form-profile\\:edit-profile-save-btn").show();
        $("#form-profile\\:edit-profile-cancel-btn").show();
    });
    
    // Edit advanced button click
    $("#form-advanced\\:edit-advanced-btn").bind("click", function(e) {
        // Show password and confirm password fields
        $("#advanced-table tr:last td").show();
        $("#advanced-table tr:last").prev().find("td").show();
        // Enable fields
        changeAdvancedFields(false);
        // Show/hide appropriate buttons
        $("#form-advanced\\:edit-advanced-btn").hide();
        $("#form-advanced\\:edit-advanced-save-btn").show();
        $("#form-advanced\\:edit-advanced-cancel-btn").show();
    });
});

function changeProfileFields(enabled) {
    $("#form-profile\\:first-name").prop("disabled", enabled);
    $("#form-profile\\:last-name").prop("disabled", enabled);
    $("#form-profile\\:age").prop("disabled", enabled);
    $("#form-profile\\:height").prop("disabled", enabled);
    $("#form-profile\\:weight").prop("disabled", enabled);
    $("#form-profile\\:gender").prop("disabled", enabled);
}

function changeAdvancedFields(enabled) {
    $("#form-advanced\\:email").prop("disabled", enabled);
    $("#form-advanced\\:goal-weight").prop("disabled", enabled);
    $("#form-advanced\\:activity-level").prop("disabled", enabled);
    $("#form-advanced\\:activity-goal").prop("disabled", enabled);
}