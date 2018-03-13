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
                            +"<td width=\"10%\" align=\"center\">"+"<input type=radio name='test"+index.toString()+"' id='5'>"+"</td>"   //相同name只能取一个
                            +"<td width=\"10%\" align=\"center\">"+"<input type=radio name='test"+index.toString()+"' id='4'>"+"</td>"
                            +"<td width=\"10%\" align=\"center\">"+"<input type=radio name='test"+index.toString()+"' id='3'>"+"</td>"
                            +"<td width=\"10%\" align=\"center\">"+"<input type=radio name='test"+index.toString()+"' id='2'>"+"</td>"
                            +"<td width=\"10%\" align=\"center\">"+"<input type=radio name='test"+index.toString()+"' id='1'>"+"</td>"
                            +"</tr>"
                        $("#questions").append(string);
                });
            }
            function pageNum(data){
                $("#pageNum").html("<label>"+"当前页面:"+"</label>");
                $("#pageNum").append("<label>" + "[" + data.page + "/" + data.totalPage + "]" + "</label>");
            }
            function table() {
                $("#questions").html("<tr>" +
                    "        <th width=\"10%\"></th>" +
                    "        <th width=\"10%\">非常同意</th>" +
                    "        <th width=\"20%\">比较同意</th>" +
                    "        <th width=\"20%\">差不多</th>" +
                    "        <th width=\"20%\">一点同意</th>" +
                    "        <th width=\"20%\">不同意</th>" +
                    "    </tr>");
            }

            var list_map = new Array();
            $('#submit').click(function () {
                $("input:radio:checked").each(function(){   //遍历所有选中的radio
                    var names=parseInt($(this).attr("id").toString());
                    list_map.push(names);
                    //console.log(list_map)
                });

                var test1=list_map[4]+list_map[9]+list_map[13]+list_map[17]+list_map[23]+list_map[29];  //5、10、14、18、24、30
                console.log("老虎:"+test1);
                var test2=list_map[2]+list_map[5]+list_map[12]+list_map[19]+list_map[21]+list_map[28];  //3、6、13、20、22、29
                console.log("孔雀:"+test2);
                var test3=list_map[1]+list_map[7]+list_map[14]+list_map[16]+list_map[24]+list_map[27];  //2、8、15、17、25、28
                console.log("无尾熊:"+test3);
                var test4=list_map[0]+list_map[6]+list_map[10]+list_map[15]+list_map[20]+list_map[25];  //1、7、11、16、21、26
                console.log("猫头鹰:"+test4);
                var test5=list_map[3]+list_map[8]+list_map[11]+list_map[18]+list_map[22]+list_map[26];  //4、9、12、19、23、27
                console.log("变色龙:"+test5);
            });

            testsList(data);
            $("#pageNum").append("<label>"+"["+data.page+"/"+data.totalPage+"]"+"</label>");

            $('#pageDown').click(function () {
                table();
                $.post({
                    url: 'http://localhost:8080/user/pageDown_test1',
                    data: "",
                    dataType: "json",
                    success: function (data) {
                        testsList(data);
                        pageNum(data);
                        alert(data.errorCode + "   " + data.errorMessage);
                    },
                    error: function (data) {
                        alert(data.responseJSON.errorCode + "   " + data.responseJSON.errorMessage);
                    }
                })
            });                             //获得自动提交

            $('#pageUp').click(function () {
                table();
                $.post({
                    url: 'http://localhost:8080/user/pageUp_test1',
                    data: "",
                    dataType: "json",
                    success: function (data) {
                        testsList(data);
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