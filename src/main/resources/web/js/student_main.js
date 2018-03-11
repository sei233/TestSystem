$(function () {
    $.post({
        url: 'http://localhost:8080/user/name',
        data: "",
        dataType: "json",
        success: function(data) {
            function usersList(data){
                var usersList=data.usersList;
                $.each(usersList,function (index,obj) {
                    $("#name").append("<label>" + obj.userName + "</label>");
                    $("#phone").append("<label>" + obj.userPhone + "</label>");
                    $("#role").append("<label>" + obj.userRole + "</label>");
                });
            }
            usersList(data);
        },
        error:function (data) {
            alert(data.responseJSON.errorCode + "   " + data.responseJSON.errorMessage);
        }
    });
});