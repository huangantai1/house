<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" type="text/css" href="easyUI/themes/default/easyui.css">
    <link rel="stylesheet" type="text/css" href="easyUI/themes/icon.css">
    <link rel="stylesheet" type="text/css" href="easyUI/css/demo.css">
    <script src="js/jquery-1.8.3.js"></script>
    <script src="js/jquery.easyui.min.js"></script>
    <script language="JavaScript">
         $(function () {
             $('#div').datagrid({
                 title: "区域信息",
                 url: 'getDistrict',  //服务器地址
                 pagination: true,  //启用分页
                 pageList: [3, 6, 9, 15, 20], //设置每页大小
                 pageSize: 3,
                 mode:'remote',//每页三条
                 toolbar:$("#search"),
                 sortName:"id",
                 sortOrder:"desc",
                 remoteSort:true,
                 columns: [[
                     {field:"ck",checkbox:"true"},
                     {field: 'id', title: '编号', width: 100, align: 'left'},
                     {field: 'name', title: '区域名字', width: 100, align: 'left'},
                     {
                         field: 'operation', title: '删除', width: 100,
                         formatter: function (value, row, index) {
                             return "<a href=\"javascript:delete1("+row.id+")\" class=\"easyui-linkbutton\" iconCls=\"icon-ok\">删除</a> <a href=\"javascript:openShowSteetDialog("+row.id+");\">查看街道</a>";
                         }
                     }



                         ]]


             });
         })

         function delete1(id) {
             $.messager.confirm("系统提示", "你确认要删除吗？", function (xo) {
                 var selectRows = $('#div').datagrid("getSelections");
                 var row = selectRows[0];
                 if (xo) {
                     $.post("deleteDistrict", {"id": row.id}, function (data) {
                         date = $.parseJSON(data);
                         if (data.result == 1) {
                             $.messager.alert("删除失败");
                         } else {
                             $('#div').datagrid('reload');
                             $.messager.alert("删除成功");

                         }

                     })
                 }

             })


         }
    function deleteList() {
        var SelectRows = $("#div").datagrid('getSelections');
        if( 0== SelectRows.length ){
            $.messager.alert("系统提示", "请选择一行");
            return;
        }
        $.messager.confirm("系统提示", "你确认要删除吗？", function (xo) {
        if(xo){
            var value="";
            for(var i=0;i<SelectRows.length;i++){
                value=value+SelectRows[i].id+",";


            }
            value=value.substring(0,value.length-1);
            $.post("deleteDistricts",{"id":value},function (data) {
                if (data.result == 1) {
                    $.messager.alert("删除失败");
                } else {
                    $('#div').datagrid('reload');
                    $.messager.alert("删除成功");

                }

            },"json")
        }
    })
    }
            

    </script>
</head>
<body>
<div id="search">
        区域名字:<input id="name" class="easyui-validatebox" /> <a
         class="easyui-linkbutton" onclick="search()"
         data-options="iconCls:'icon-search'" >搜索</a> <a
        href="javascript:Add()" plain="true"
        class="easyui-linkbutton" data-options="iconCls:'icon-add'">新增</a> <a
        href="javascript:ModifyBySelect()" onclick="add_bj()" class="easyui-linkbutton"
        data-options="iconCls:'icon-edit'">编辑</a> <a
        href="javascript:deleteList();" onclick="add_del()" class="easyui-linkbutton"
        data-options="iconCls:'icon-remove'">批量删除</a>

</div>
<div id="AddDialog" class="easyui-dialog" buttons="#AddDialogButtons"
     style="width: 280px; height: 250px; padding: 10px 20px;" closed="true">
    <form id="ModiyDialogForm" method="post">
        <table>
            <tr>
                <td>编号:</td>
                <td><input type="text" class="easyui-validatebox" required
                           name="id" id="id" /></td>
            </tr>
            <tr>
                <td>区域名字:</td>
                <td><input type="text" name="name" id="name1" /></td>
            </tr>
        </table>
    </form>
</div>
<div id="upDialog" class="easyui-dialog" buttons="#upDialogButtons"
     style="width: 280px; height: 250px; padding: 10px 20px;" closed="true">
    <form id="upDialogForm" method="post">
        <table>
            <tr>
                <td>街道名字:</td>
                <td><input type="text" name="name" id="name2" /></td>
            </tr>
        </table>
    </form>
</div>
<div id="showStreetDialog" class="easyui-dialog" buttons="#AddDialogButtons"
     style="width: 280px; height: 250px; padding: 10px 20px;" closed="true" >
    <form id="DialogForm" method="post">
        <table id="div1">
            <tr>
                <td>街道名字:</td>
                <td><input type="text" name="name" id="name3" /></td>
            </tr>
        </table>
    </form>
</div>
<div id="AddDialogButtons">
    <a href="javascript:SaveDialog()" class="easyui-linkbutton"
       iconCls="icon-ok">保存</a> <a href="javascript:CloseDialog()"
                                   class="easyui-linkbutton" iconCls="icon-cancel">取消</a>
</div>
<div id="upDialogButtons">
    <a href="javascript:upDaveDialog()" class="easyui-linkbutton"
       iconCls="icon-ok">保存</a> <a href="javascript:CloseDialog()"
                                   class="easyui-linkbutton" iconCls="icon-cancel">取消</a>
</div>
<div id="div"></div>


</body>
<script type="text/javascript" src="js/MyEasyUICRUD.js"></script>
</html>
