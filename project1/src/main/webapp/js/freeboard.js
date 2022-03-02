/*     insert     */
$(function() {
    $('#insert-btn').click(function() {
        let category = $('#category').val();
        if (category.trim() == "none") {
            $('#category').focus();
            return;
        }

        let title = $('#title').val();
        if (title.trim() == "") {
            $('#title').focus();
            return;
        }

        let content = $('#content').val();
        if (content.trim() == "") {
            $('#content').focus();
            return;
        }

        alert("게시물이 작성되었습니다");
        $('#insert-form').submit();
    })
})


/*     update     */
$(function() {
    $('#update-btn').click(function() {
        let category = $('#category').val();
        if (category.trim() == "none") {
            $('#category').focus();
            return;
        }

        let title = $('#title').val();
        if (title.trim() == "") {
            $('#title').focus();
            return;
        }

        let content = $('#content').val();
        if (content.trim() == "") {
            $('#content').focus();
            return;
        }

        var result = confirm('게시물을 수정하시겠습니까?');
        if (result) {
            alert("게시물이 수정되었습니다");
            $('#update-form').submit();
        } else {

        }
    })
})




/*    cancel    */
$(function() {
    $('#cancel-btn').click(function() {
        var result = confirm('취소하시겠습니까?');
        if (result) {
            history.back();
        } else {

        }
    })
})