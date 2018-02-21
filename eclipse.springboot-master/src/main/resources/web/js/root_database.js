$(function () {
    $.post({
        url: 'http://localhost:8080/user/database',
        data: "",
        dataType: "json",
        success: function(data) {
            var usersList=data.usersList;
            $.each(usersList,function (index,obj) {
                var string="<tr>/n"
                    +"<td width=\"20%\">"+obj.userId+"</td>/n"
                    +"<td width=\"20%\">"+obj.userName+"</td>/n"
                    +"<td width=\"20%\">"+obj.userPassword+"</td>/n"
                    +"<td width=\"20%\">"+obj.userPhone+"</td>/n"
                    +"<td width=\"20%\">"+obj.userRole+"</td>/n"
                +"</tr>"
                $("#table_id_example").append(string);
            });
            alert(data.errorCode + "   " + data.errorMessage);
        },
        error:function (data) {
            alert(data.responseJSON.errorCode + "   " + data.responseJSON.errorMessage);
        }
    })
})