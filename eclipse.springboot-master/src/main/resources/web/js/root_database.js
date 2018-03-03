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


            // var page="当前页面:"+"["+data.page+"/"+data.totalPage+"]"
            //         +"<a id='pageUp'>"+"上一页"+"</a>"
            //         +"<a id='pageDown'>"+"下一页"+"</a>"
            // $("#table_id_example").append(page);
            $("#pageNum").append("<label>"+"["+data.page+"/"+data.totalPage+"]"+"</label>");

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

            // for(var i =1;i<data.totalPage;i++) {(function () {
            $('#pageDown').click(function () {
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
                        success: function (data) {
                            // data.page += 1;
                            var usersList = data.usersList;
                            $.each(usersList, function (index, obj) {
                                var string = "<tr>/n"
                                    + "<td width=\"20%\">" + obj.userId + "</td>/n"
                                    + "<td width=\"20%\">" + obj.userName + "</td>/n"
                                    + "<td width=\"20%\">" + obj.userPassword + "</td>/n"
                                    + "<td width=\"20%\">" + obj.userPhone + "</td>/n"
                                    + "<td width=\"20%\">" + obj.userRole + "</td>/n"
                                    + "</tr>/n"
                                $("#table_id_example").append(string);                                //一条输出一次
                            });

                            $("#pageNum").html("<label>"+"当前页面:"+"</label>");
                            $("#pageNum").append("<label>" + "[" + data.page + "/" + data.totalPage + "]" + "</label>");
                            // var nextpage = "当前页面:" + "[" + data.page + "/" + data.totalPage + "]" //两个page的data不一样
                            //     + "<a id='pageUp'>" + "上一页" + "</a>"
                            //     + "<a id='pageDown'>" + "下一页" + "</a>"
                            // $("#table_id_example").append(nextpage);                                //总共输出一次
                            alert(data.errorCode + "   " + data.errorMessage);
                        },
                        error: function (data) {
                            alert(data.responseJSON.errorCode + "   " + data.responseJSON.errorMessage);
                        }
                    })
                });      //pageDown里再写个pageDown？那10页怎么办？
            // })(i);
            // }
            alert(data.errorCode + "   " + data.errorMessage);
        },
        error:function (data) {
            alert(data.responseJSON.errorCode + "   " + data.responseJSON.errorMessage);
        }
    });
})