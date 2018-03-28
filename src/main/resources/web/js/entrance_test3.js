$(function () {
    $.post({
        url: 'http://localhost:8080/user/test3',
        data: "",
        dataType: "json",
        success: function(data) {
            var name_map=new Array();
            var id_map = new Array();

            function testsList(data){
                var testsList=data.test3List;
                $.each(testsList,function (index,obj) {
                        var datas=data.page-1;
                        var string= "<div class=\"lbl\">"+ "<label for='test"+datas+index.toString()+"' style=\"color: #B0BED9\">"+obj.question+"</label>"+"</div>"
                    +"<div class=\"ctrl\">"+
                        "<textarea style=\"background-color: #EbEbEb\" id='test"+datas+index.toString()+"' name='test"+datas+index.toString()+"' data-validation=text data-msg=\"Invalid editType\" value=\" \" >"
                          +"</textarea>"
                        +"</div>"
                        $("#question").append(string);
                });
            }
            function pageNum(data){
                $("#pageNum").html("<label style=\"color: #B0BED9\">"+"当前页面:"+"</label>");
                $("#pageNum").append("<label style=\"color: #B0BED9\">" + "[" + data.page + "/" + data.totalPage + "]" + "</label>");
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
                    url: 'http://localhost:8080/user/stu_ans2',
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


            $("#pageNum").append("<label style=\"color: #B0BED9\">"+"["+data.page+"/"+data.totalPage+"]"+"</label>");


            alert(data.errorCode + "   " + data.errorMessage);
        },
        error:function (data) {
            alert(data.responseJSON.errorCode + "   " + data.responseJSON.errorMessage);
        }
    });
})