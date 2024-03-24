var userId, userName;

function removeUserDialog(el) {
    userId = $(el).attr('data-user-id');
    userName = $(el).attr('data-user-name');
    $('.remove-user-modal').find('#user-name').text(userName);
}

function removeUser() {
    $('.remove-user-modal').modal('hide');
    window.location = "/user/remove/" + userId;
}
