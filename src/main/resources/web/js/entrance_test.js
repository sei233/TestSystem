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
                        +"<td width=\"10%\" align=\"center\">"+obj.question+"</td>"
                        +"</tr>"
                    $("#questions").append(string);
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