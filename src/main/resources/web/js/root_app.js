$(function () {
    $.post({
        url: 'http://localhost:8080/user/application',
        data: "",
        dataType: "json",
        success: function(data) {
            function usersList(data){
                var usersList=data.usersList;
                $.each(usersList,function (index,obj) {
                    var string= "<tr>/n"
                        +"<td width=\"10%\" align=\"center\">"+"<input type=checkbox name='test1'>"+"</td>/n"
                        +"<td width=\"10%\" align=\"center\">"+obj.userId+"</td>/n"
                        +"<td width=\"20%\" align=\"center\" class='userName1'>"+obj.userName+"</td>/n"
                        +"<td width=\"20%\" align=\"center\">"+obj.userPassword+"</td>/n"
                        +"<td width=\"20%\" align=\"center\">"+obj.userPhone+"</td>/n"
                        +"<td width=\"20%\" align=\"center\">"+obj.userRole+"</td>/n"
                        +"</tr>/n"
                    $("#table").append(string);
                });
            }
            function table() {
                $("#table").html("<tr>\n" +
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
            pageNum(data);
            // var page="当前页面:"+"["+data.page+"/"+data.totalPage+"]"
            //         +"<a id='pageUp'>"+"上一页"+"</a>"
            //         +"<a id='pageDown'>"+"下一页"+"</a>"
            // $("#table_id_example").append(page);

            $('#submit2').click(function(){
                var paramData = {
                    userName:$("#userName").val()
                };
                table();
                //js控制#id
                $.post({
                    url: 'http://localhost:8080/user/search',
                    data: paramData,
                    dataType: "json",
                    success: function(data) {
                        usersList(data);
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