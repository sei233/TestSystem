/**
 * Created by ChenXiang on 2018/01/05.
 */
$(function () {
    $('#re_submit').click(function() {
        var paramData = {
            userName:$("#re_userName").val(),
            userPassword:$("#re_userPassword").val(),
            userPhone:$("#re_userPhone").val(),
            userRole:$("#re_userRole").val()
        };
        //js控制id
        $.post({
            url: 'http://localhost:8080/user/regist',
            data: paramData,
            dataType: "json",
            success: function(data) {
                alert(data.errorCode + "   " + data.errorMessage);
                window.location.assign("login.html")
            },
            error:function (data) {
                alert(data.responseJSON.errorCode + "   " + data.responseJSON.errorMessage);
            }
        })
    });

    $('#lo_submit').click(function() {
        var paramData = {
            userName:$("#lo_userName").val(),
            userPassword:$("#lo_userPassword").val(),
            userRole:$("#lo_userRole").val()
        };
        //js控制id,通过submit来进行区分
        $.post({
            url: 'http://localhost:8080/user/login',
            data: paramData,
            dataType: "json",
            success: function(data) {
                alert(data.errorCode + "   " + data.errorMessage);
                if(data.role==1) window.location.assign("student_main.html")
                if(data.role==6) window.location.assign("../html/root/root_main.html")
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