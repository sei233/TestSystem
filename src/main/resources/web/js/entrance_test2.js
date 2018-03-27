$(function () {
    $.post({
        url: 'http://localhost:8080/user/test2',
        data: "",
        dataType: "json",
        success: function(data) {
            var name_map=new Array();
            var id_map = new Array();

            function testsList(data){
                var testsList=data.test2List;
                $.each(testsList,function (index,obj) {
                        var datas=data.page-1;
                        var string= "<tr>"
                            +"<td width=\"10%\" align=\"center\" id='"+index.toString()+"'>"+obj.question+"</td>"
                            +"<td width=\"10%\" align=\"center\">"+"<input type=radio name='test"+datas+index.toString()+"' id='5'>"+"</td>"   //相同name只能取一个
                            +"<td width=\"10%\" align=\"center\">"+"<input type=radio name='test"+datas+index.toString()+"' id='4'>"+"</td>"
                            +"<td width=\"10%\" align=\"center\">"+"<input type=radio name='test"+datas+index.toString()+"' id='3'>"+"</td>"
                            +"<td width=\"10%\" align=\"center\">"+"<input type=radio name='test"+datas+index.toString()+"' id='2'>"+"</td>"
                            +"<td width=\"10%\" align=\"center\">"+"<input type=radio name='test"+datas+index.toString()+"' id='1'>"+"</td>"
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
            function getNum(arr, val) {
                for(var i=0; i<arr.length; i++) {
                    if(arr[i] == val) {
                        return i;
                    }
                }
            }          //判断值是第几个
            // function removeByValue(arr, val) {
            //     for(var i=0; i<arr.length; i++) {
            //         if(arr[i] == val) {
            //             arr.splice(i, 1);
            //             break;
            //         }
            //     }
            // }   //根据值来移除数组中的元素
            function radio() {
                    $("input:radio:checked").each(function(){                               //遍历当前页面，默认排序,所有选中的radio获得他们的id和name
                        var ids=parseInt($(this).attr("id").toString());                    // 不停上下页，可能会导致死机
                        var names=$(this).attr("name").toString();
                        if($.inArray(names,name_map)>= 0) {                                  //找到匹配元素
                            var i=getNum(name_map,names);
                         //   removeByValue(name_map,names);
                            name_map[i]=names;                                                 //这就是替换
                                                                                               //id 和 name 一一对应
                         //   id_map.splice(i,1);
                            id_map[i]=ids;
                        }
                        else{ name_map.push(names);
                              id_map.push(ids);}
                        console.log(id_map);
                        console.log(name_map);
                        //$.cookie('name',name_map);
                    });
            }
            function read(){
                for(var i=0;i<=id_map.length;i++) {
                    if(id_map[i]==null)break;
                    $("input[id='"+id_map[i]+"'][name='"+name_map[i]+"']:radio").attr('checked',true);
                }
            }    //如果用户数量多，临时数据巨大，需要改成缓存

            testsList(data);



            $('#submit').click(function () {
                radio();
                var paramData={
                    testAns:id_map,
                    testNum:name_map
                };
                $.post({
                    url: 'http://localhost:8080/user/stu_ans',
                    data: JSON.stringify(paramData),
                    dataType: "json",
                    contentType:"application/json",
                    success: function (data) {
                        alert(data.errorCode + "   " + data.errorMessage);
                        window.location.assign("../student/student_main.html")
                        //跳转下一题
                        //test
                    },
                    error: function (data) {
                        alert(data.responseJSON.errorCode + "   " + data.responseJSON.errorMessage);
                    }
                })
            });

                // var test1=list_map[4]+list_map[9]+list_map[13]+list_map[17]+list_map[23]+list_map[29];  //5、10、14、18、24、30
                // console.log("老虎:"+test1);
                // var test2=list_map[2]+list_map[5]+list_map[12]+list_map[19]+list_map[21]+list_map[28];  //3、6、13、20、22、29
                // console.log("孔雀:"+test2);
                // var test3=list_map[1]+list_map[7]+list_map[14]+list_map[16]+list_map[24]+list_map[27];  //2、8、15、17、25、28
                // console.log("无尾熊:"+test3);
                // var test4=list_map[0]+list_map[6]+list_map[10]+list_map[15]+list_map[20]+list_map[25];  //1、7、11、16、21、26
                // console.log("猫头鹰:"+test4);
                // var test5=list_map[3]+list_map[8]+list_map[11]+list_map[18]+list_map[22]+list_map[26];  //4、9、12、19、23、27
                // console.log("变色龙:"+test5);

            $("#pageNum").append("<label>"+"["+data.page+"/"+data.totalPage+"]"+"</label>");

            $('#pageDown').click(function () {
                radio();
                table();
                if($.cookie('name')!=null){
                    console.log($.cookie('name'));
                    console.log($.cookie('id'));
                }   //cookie将表以文本形式存储？
                $.post({
                    url: 'http://localhost:8080/user/pageDown_test1',
                    data: "",
                    dataType: "json",
                    success: function (data) {
                        testsList(data);
                        read();
                        pageNum(data);
                        alert(data.errorCode + "   " + data.errorMessage);
                    },
                    error: function (data) {
                        alert(data.responseJSON.errorCode + "   " + data.responseJSON.errorMessage);
                    }
                })
            });                             //获得自动提交

            $('#pageUp').click(function () {
                radio();
                table();
                $.post({
                    url: 'http://localhost:8080/user/pageUp_test1',
                    data: "",
                    dataType: "json",
                    success: function (data) {
                        testsList(data);
                        read();
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