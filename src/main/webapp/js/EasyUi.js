function noPassHouse(id){
    //发送异步请求到服务器
    $.post("houseNo",{"id":id},function(data){
        if(data.result>0)
        {
            $("#dg").datagrid("reload"); //刷新
        }else{
            $.messager.alert('提示框','审核失败！^_^','info');
        }
    },"json");
}