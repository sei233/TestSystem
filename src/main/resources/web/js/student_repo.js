$(function () {

    $.post({
        url: 'http://localhost:8080/user/entrance_repo',
        data: "",
        dataType: "json",
        success: function(data) {
            console.log(data);
            function usersList(data){
                var usersList=data.entrList;
                $.each(usersList,function (index,obj) {
                    $("#Name").append("<label>" + obj.student + "</label>");
                    $("#Dominance").append("<label>" + obj.dominance + "</label>");
                    $("#Extroversion").append("<label>" + obj.extroversion + "</label>");
                    $("#Patience").append("<label>" + obj.patience + "</label>");
                    $("#Conformity").append("<label>" + obj.conformity + "</label>");
                    $("#Sigma").append("<label>" + obj.sigma + "</label>");
                });
            }
            usersList(data);

        },
        error:function (data) {
            alert(data.responseJSON.errorCode + "   " + data.responseJSON.errorMessage);
        }
    });
});