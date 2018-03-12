$(function () {
    $.post({
        url: 'http://localhost:8080/user/test1',
        data: "",
        dataType: "json",
        success: function(data) {
            function testsList(data){
                var testsList=data.testList;
                $.each(testsList,function (index,obj) {
                    var string= "<tr>"
                        +"<td width=\"10%\" align=\"center\">"+obj.question+"</td>"
                        +"<td width=\"10%\" align=\"center\">"+"<input type=radio name='test1'>"+"</td>"   //相同name只能取一个
                        +"<td width=\"10%\" align=\"center\">"+"<input type=radio name='test1'>"+"</td>"
                        +"<td width=\"10%\" align=\"center\">"+"<input type=radio name='test1'>"+"</td>"
                        +"<td width=\"10%\" align=\"center\">"+"<input type=radio name='test1'>"+"</td>"
                        +"<td width=\"10%\" align=\"center\">"+"<input type=radio name='test1'>"+"</td>"
                        +"</tr>"
                    $("#questions").append(string);
                });
            }

            testsList(data);

            alert(data.errorCode + "   " + data.errorMessage);
        },
        error:function (data) {
            alert(data.responseJSON.errorCode + "   " + data.responseJSON.errorMessage);
        }
    });
})