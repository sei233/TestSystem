$(function () {
    $.post({
        url: 'http://localhost:8080/user/test1',
        data: "",
        dataType: "json",
        success: function(data) {
            function usersList(data){
                var usersList=data.usersList;
                $.each(usersList,function (index,obj) {
                    var string= "<tr>"
                        +"<td width=\"10%\" align=\"center\">"+"<input type=checkbox name='test1'>"+"</td>"
                        +"<td width=\"10%\" align=\"center\">"+obj.userId+"</td>"
                        +"<td width=\"20%\" align=\"center\" class='userName1'>"+obj.userName+"</td>"
                        +"<td width=\"20%\" align=\"center\">"+obj.userPassword+"</td>"
                        +"<td width=\"20%\" align=\"center\">"+obj.userPhone+"</td>"
                        +"<td width=\"20%\" align=\"center\">"+obj.userRole+"</td>"
                        +"</tr>"
                    $("#table_id_example").append(string);
                });
            }

            usersList(data);

            alert(data.errorCode + "   " + data.errorMessage);
        },
        error:function (data) {
            alert(data.responseJSON.errorCode + "   " + data.responseJSON.errorMessage);
        }
    });
})