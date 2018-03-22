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

            $('#student_entrance').click(function (){
                $.post({
                    url: 'http://localhost:8080/user/entrance',
                    data: "",
                    dataType: "json",
                    success: function (data) {
                        if(data.entrance==0) window.location.assign("../entrance/entrance_test.html")
                        if(data.entrance==2) window.location.assign("../entrance/entrance_repo.html")
                        alert(data.errorCode + "   " + data.errorMessage);
                    },
                    error: function (data) {
                        alert(data.responseJSON.errorCode + "   " + data.responseJSON.errorMessage);
                    }
                })
            });

            $('#student_study').click(function (){
                $.post({
                    url: 'http://localhost:8080/user/study',
                    data: "",
                    dataType: "json",
                    success: function (data) {
                        if(data.study==0) window.location.assign("../study/study_test.html")
                        if(data.study==2) window.location.assign("../study/study_repo.html")
                        alert(data.errorCode + "   " + data.errorMessage);
                    },
                    error: function (data) {
                        alert(data.responseJSON.errorCode + "   " + data.responseJSON.errorMessage);
                    }
                })
            });

            $('#student_graduation').click(function (){
                $.post({
                    url: 'http://localhost:8080/user/graduation',
                    data: "",
                    dataType: "json",
                    success: function (data) {
                        if(data.graduation==0) window.location.assign("../graduation/gradu_test.html")
                        if(data.graduation==2) window.location.assign("../graduation/gradu_repo.html")
                        alert(data.errorCode + "   " + data.errorMessage);
                    },
                    error: function (data) {
                        alert(data.responseJSON.errorCode + "   " + data.responseJSON.errorMessage);
                    }
                })
            });

        },
        error:function (data) {
            alert(data.responseJSON.errorCode + "   " + data.responseJSON.errorMessage);
        }
    });
});