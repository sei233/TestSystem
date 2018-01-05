/**
 * Created by ChenXiang on 2018/01/05.
 */
$(function () {
    $('#submit').click(function() {
        var paramData = {
            userName:$("#userName").val(),
            userPhone:$("#userPhone").val()
        };

        $.post({
            url: 'http://127.0.0.1:8080/user/regist',
            data: paramData,
            dataType: "json",
            success: function(data) {
                alert(data.errorCode + "   " + data.errorMessage);
            },
            error:function (data) {
                alert(data.responseJSON.errorCode + "   " + data.responseJSON.errorMessage);
            }
        })
    })

    function transArrToObj(arr) {
        var obj = {};
        for(var key in arr) {
            obj[arr[key]['name']] = arr[key]['value'];
        }

        return obj;
    }
})