$(function () {
    $.post({
        url: 'http://localhost:8080/user/correct',
        data: "",
        dataType: "json",
        success: function(data) {
            function usersList(data){
                var usersList=data.usersList;
                $.each(usersList,function (index,obj) {
                    var string= "<tr>"
                        +"<td width=\"10%\" align=\"center\">"+"<input type=checkbox name='test2'>"+"</td>"
                        +"<td width=\"10%\" align=\"center\">"+obj.userId+"</td>"
                        +"<td width=\"20%\" align=\"center\" class='userName2'>"+obj.userName+"</td>"
                        +"<td width=\"20%\" align=\"center\">"+obj.userPhone+"</td>"
                        +"</tr>"
                    $("#table").append(string);
                });
            }
            function table() {
                $("#table").html("<tr>" +
                    "        <th width=\"10%\"></th>" +
                    "        <th width=\"10%\">用户ID</th>" +
                    "        <th width=\"20%\">用户名</th>" +
                    "        <th width=\"20%\">用户手机号</th>" +
                    "    </tr>");

            }
            function pageNum(data){
                $("#pageNum").html("<label>"+"当前页面:"+"</label>");
                $("#pageNum").append("<label>" + "[" + data.page + "/" + data.totalPage + "]" + "</label>");
            }

            usersList(data);
            pageNum(data);


            alert(data.errorCode + "   " + data.errorMessage);
        },
        error:function (data) {
            alert(data.responseJSON.errorCode + "   " + data.responseJSON.errorMessage);
        }
    });
})