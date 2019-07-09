function noPassHouse(id){
    //发送异步请求到服务器
    $.post("houseNo",{"id":id},function(data){
        if(data.result>0)
        {
            $("#search").datagrid("reload"); //刷新
        }else{
            $.messager.alert('提示框','审核失败！^_^','info');
        }
    },"json");
}
function search(){
    var sname1=$("#sname").val();
    var sname=Trim(sname1);
    var dname=$("#dname").val().trim("");
    $("#search").datagrid("load",{"sname":sname,"dname":dname});

}
function Trim(str)
{
    return str.replace(/(^\s*)|(\s*$)/g, "");
}
