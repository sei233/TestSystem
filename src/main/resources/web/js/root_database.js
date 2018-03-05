$(function () {
    $.post({
        url: 'http://localhost:8080/user/database',
        data: "",
        dataType: "json",
        success: function(data) {
            function usersList(data){
            var usersList=data.usersList;
            $.each(usersList,function (index,obj) {
                var string= "<tr>/n"
                    +"<td width=\"10%\" align=\"center\">"+"<input type=checkbox name='test'>"+"</td>/n"
                    +"<td width=\"10%\" align=\"center\">"+obj.userId+"</td>/n"
                    +"<td width=\"20%\" align=\"center\" class='userName'>"+obj.userName+"</td>/n"
                    +"<td width=\"20%\" align=\"center\">"+obj.userPassword+"</td>/n"
                    +"<td width=\"20%\" align=\"center\">"+obj.userPhone+"</td>/n"
                    +"<td width=\"20%\" align=\"center\">"+obj.userRole+"</td>/n"
                +"</tr>/n"
                $("#table_id_example").append(string);
            });
            }
            function table() {
                $("#table_id_example").html("<tr>\n" +
                    "        <th width=\"10%\"></th>\n" +
                    "        <th width=\"10%\">用户ID</th>\n" +
                    "        <th width=\"20%\">用户名</th>\n" +
                    "        <th width=\"20%\">密码</th>\n" +
                    "        <th width=\"20%\">用户手机号</th>\n" +
                    "        <th width=\"20%\">权限</th>\n" +
                    "    </tr>");

            }
            function pageNum(data){
                $("#pageNum").html("<label>"+"当前页面:"+"</label>");
                $("#pageNum").append("<label>" + "[" + data.page + "/" + data.totalPage + "]" + "</label>");
            }

            usersList(data);

            // var page="当前页面:"+"["+data.page+"/"+data.totalPage+"]"
            //         +"<a id='pageUp'>"+"上一页"+"</a>"
            //         +"<a id='pageDown'>"+"下一页"+"</a>"
            // $("#table_id_example").append(page);
            $("#pageNum").append("<label>"+"["+data.page+"/"+data.totalPage+"]"+"</label>");

            $('#pageUp').click(function(){
                table();
                $.post({
                    url: 'http://localhost:8080/user/pageUp',
                    data: "",
                    dataType: "json",
                    success: function(data) {
                        usersList(data);
                        pageNum(data);
                        alert(data.errorCode + "   " + data.errorMessage);
                    },
                    error:function (data) {
                        alert(data.responseJSON.errorCode + "   " + data.responseJSON.errorMessage);
                    }
                })
            });

            // for(var i =1;i<data.totalPage;i++) {(function () {
            $('#pageDown').click(function () {
                    table();
                    $.post({
                        url: 'http://localhost:8080/user/pageDown',
                        data: "",
                        dataType: "json",
                        success: function (data) {
                            // data.page += 1;
                            usersList(data);
                            pageNum(data);
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

            var names;
            $('#delete').click(function () {
                table();
                $("input[name='test']:checkbox:checked").each(function(){   //遍历所有选中的checkbox
                    names=$(this).parent("td").parent("tr").children("td.userName").html();
                });
                $.post({
                    url: 'http://localhost:8080/user/delete',
                    data: names,
                    dataType: "json",
                    success: function (data) {
                        usersList(data);
                        pageNum(data);
                        alert(data.errorCode + "   " + data.errorMessage);
                    },
                    error: function (data) {
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