function isFormValid() {
    var firstName = $('#firstName').val();
    var displayName = $('#displayName').val();
    var username = $('#username').val();
    var password = $('#password').val();

    // Check if any of the fields are empty
    if (firstName === '' || displayName === '' || username === '' || password === '') {
        return false;
    }

    // If all fields are filled, return true
    return true;
}

$(document).ready(function() {
    $('#saveBtn').on('click', function() {
        if (isFormValid()) {
            $('#user-form').submit();
        } else {
            $('#formErr').text('Veuillez remplir tous les champs obligatoires.');
        }
    });

    $('#gotoListBtn').on('click', function() {
        window.location = "/user/list";
    });
});
