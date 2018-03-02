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


                var page="当前页面:"+"["+data.page+"/"+data.totalPage+"]"
                    +"<a id='pageUp'>"+"上一页"+"</a>"
                    +"<a id='pageDown'>"+"下一页"+"</a>"
            $("#table_id_example").append(page);


            $('#pageUp').click(function(){
                $("#table_id_example").html("<tr>\n" +
                    "        <th width=\"20%\">用户ID</th>\n" +
                    "        <th width=\"20%\">用户名</th>\n" +
                    "        <th width=\"20%\">密码</th>\n" +
                    "        <th width=\"20%\">用户手机号</th>\n" +
                    "        <th width=\"20%\">权限</th>\n" +
                    "    </tr>");
                $.post({
                    url: 'http://localhost:8080/user/pageUp',
                    data: "",
                    dataType: "json",
                    success: function(data) {
                        alert(data.errorCode + "   " + data.errorMessage);
                    },
                    error:function (data) {
                        alert(data.responseJSON.errorCode + "   " + data.responseJSON.errorMessage);
                    }
                })
            });


            $('#pageDown').click(function(){
                data.page++;
                $("#table_id_example").html("<tr>\n" +
                    "        <th width=\"20%\">用户ID</th>\n" +
                    "        <th width=\"20%\">用户名</th>\n" +
                    "        <th width=\"20%\">密码</th>\n" +
                    "        <th width=\"20%\">用户手机号</th>\n" +
                    "        <th width=\"20%\">权限</th>\n" +
                    "    </tr>");
                $.post({
                    url: 'http://localhost:8080/user/pageDown',
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


                        var page="当前页面:"+"["+data.page+"/"+data.totalPage+"]"
                            +"<a id='pageUp'>"+"上一页"+"</a>"
                            +"<a id='pageDown'>"+"下一页"+"</a>"
                        $("#table_id_example").append(page);
                        alert(data.errorCode + "   " + data.errorMessage);
                    },
                    error:function (data) {
                        alert(data.responseJSON.errorCode + "   " + data.responseJSON.errorMessage);
                    }
                })
            });

            alert(data.errorCode + "   " + data.errorMessage);
        },
        error:function (data) {
            alert(data.responseJSON.errorCode + "   " + data.responseJSON.errorMessage);
        }
    });
})