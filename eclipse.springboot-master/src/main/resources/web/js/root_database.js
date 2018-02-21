$(function () {
    $.post({
        url: 'http://localhost:8080/user/database',
        data: "",
        dataType: "json",
        success: function(data) {
            var usersList=data.usersList;
            $.each(usersList,function (index,obj) {
                var string= "<tr>/n"
                    +"<td width=\"20%\">"+obj.userId+"</td>/n"
                    +"<td width=\"20%\">"+obj.userName+"</td>/n"
                    +"<td width=\"20%\">"+obj.userPassword+"</td>/n"
                    +"<td width=\"20%\">"+obj.userPhone+"</td>/n"
                    +"<td width=\"20%\">"+obj.userRole+"</td>/n"
                +"</tr>/n"
                $("#table_id_example").append(string);
            });
                var page= "<li>"+"当前页面:"+"["+data.page+"/"+data.totalPage+"]"+"</li>"
                    +"<a href=\"http://www.w3school.com.cn\">"+"上一页"+"</a>"
            $("#table_id_example").append(page);

            alert(data.errorCode + "   " + data.errorMessage);
        },
        error:function (data) {
            alert(data.responseJSON.errorCode + "   " + data.responseJSON.errorMessage);
        }
    })


})