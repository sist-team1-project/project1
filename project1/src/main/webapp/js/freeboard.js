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