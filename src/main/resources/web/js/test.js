$(function () {
    var names;
    $('#delete1').click(function () {
         $("input[name='test']:checkbox:checked").each(function () {   //遍历所有选中的checkbox
             names = $(this).parent("td").parent("tr").children("td.userName").html();
            $("#table_id_example").append(names);
            console.log(names);
        });
     });
})