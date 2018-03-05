$(function () {
    var names="shiqian";
    $('#delete1').click(function () {
         $("input[name='test']:checkbox:checked").each(function () {   //遍历所有选中的checkbox
             names = $(this).parent("td");
            $("#table_id_example").append(names);
        });
     });
})