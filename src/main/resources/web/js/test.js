$(function () {
    var names;
    $('#delete1').click(function () {
        var names=$("input[type=checkbox]:checked").attr("id").toString();//发挥2个值赋予1个值
            $("#table_id_example").append(names);
            console.log(names);

     });
})