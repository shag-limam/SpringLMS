$(document).ready(function() {

    var users = [];
    function initUsers() {
        $.get('/rest/member/listuser', function(data) {
            if( data ) {
                users = data;
            }
        });
    }
    initUsers();

    function getUsersByType(userType) {
        var filteredUsers = [];
        for(var k=0 ; k<users.length ; k++) {
            if( users[k].role==userType ) {
                filteredUsers.push( users[k] );
            }
        }
        return filteredUsers;
    }

    function populateUsersList( usersList ) {
        $('#userSel').empty();
        $.each(usersList, function(k, v) {
             $('#userSel').append($("<option></option>")
                            .attr("value",v.id).text(v.firstName));
        });
    }

    $('#userTypeSel').on('change', function() {
        var value = $(this).val();
        if( value ) {
            var filteredUsers = getUsersByType( value );
            populateUsersList( filteredUsers );
        } else {
            $('#userSel').empty(); // Clear the user selection when no user type is selected
        }
    });


    function getBooksByCategory(value) {
        $.get('/rest/book/' + value + '/available', function(data) {
            if( data ) {
                populateBooksList( data );
            }
        });
    }

    function populateBooksList( booksList ) {
        $('#booksSel').empty().append('<option value="">-- Sélectionner un livre --</option>');
        $.each(booksList, function(k, v) {
             $('#booksSel').append($("<option></option>")
                            .attr("value",v.id).text(v.title)
                            .attr("data-authors", v.authors)
                            .attr("data-tag", v.tag)
                            .attr("data-publisher", v.publisher));
        });
    }

    $('#categorySel').on('change', function(){
        var value = $(this).val();
        if( value ) {
            getBooksByCategory( value );
        } else {
            $('#booksSel').empty(); // Clear the book selection when no category is selected
        }
    });


    $('#addBookBtn').on('click', function() {
        var id = $('#booksSel').val();
        var title = $("#booksSel option:selected").text();
        var tag = $("#booksSel option:selected").attr("data-tag");
        var authors = $("#booksSel option:selected").attr("data-authors");

        if( id && title && tag && authors ) {
            var book = { id: id, title: title, tag: tag, authors: authors };
            booksToIssue.push(book);
            initBooksInTable();
        }
    });

    $('#saveBtn').on('click', function(){
        var errors = validate();
        if( errors.length > 0 ) {
            $('.errors-modal').find('.modal-body').html( errors.join('<br />') );
            $('.errors-modal').modal('show');
        } else {
            var issue = {
                    user: $('#userSel').val(),
                    books: getIssuedBookIds().join()
            }
            $.post( "/rest/issue/save", issue).done(function (data){
                if( data=='success' ) {
                    window.location = '/issue/newUser';
                }
            });
        }
    });

    function getIssuedBookIds() {
        var ids = [];
        for(var k=0 ; k<booksToIssue.length ; k++) {
            ids.push( booksToIssue[k].id );
        }
        return ids;
    }

    function validate() {
        var errors = []
        var member = $('#userSel').val();
        if( !member ) {
            errors.push('- Sélectionner un utilisateur');
        }
        if( booksToIssue.length == 0 ) {
            errors.push('- Ajouter des livres à emprunter');
        }
        return errors;
    }

});

var booksToIssue = [];

function initBooksInTable() {

    var trs = '';
    for( var k=0 ; k<booksToIssue.length ; k++ ) {
        var rowNum = k+1;
        trs += '<tr>';
        trs += '<td>'+rowNum+'</td>';
        trs += '<td>'+booksToIssue[k].tag+'</td>';
        trs += '<td>'+booksToIssue[k].title+'</td>';
        trs += '<td>'+booksToIssue[k].authors+'</td>';
        trs += '<td><a href="javascript:void(0)"  onclick="removeFromTable('+rowNum+', '+booksToIssue[k].id+')"><i class="fa fa-remove"></i></a></td>';
        trs += '</tr>';
    }
    $("#issueBooksTable").find("tr:gt(0)").remove();
    $('#issueBooksTable').append( trs );
}

function removeFromTable(rowNum, id) {
    $('#issueBooksTable tr:eq('+(rowNum)+')').remove();
    removeFromBooksIssuedList(id);
    initBooksInTable();
}

function removeFromBooksIssuedList(id) {
    for( var k=0 ; k<booksToIssue.length ; k++ ) {
        if( booksToIssue[k].id == id ) {
            booksToIssue.splice(k, 1);
            break;
        }
    }
}
