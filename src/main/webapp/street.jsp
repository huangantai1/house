<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
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
                title: "街道信息",
                url: 'getStreet',  //服务器地址
                pagination: true,  //启用分页
                pageList: [3, 6, 9, 15, 20], //设置每页大小
                pageSize: 3,
                toolbar:$("#search"),
                columns: [[
                    {field:"ck",checkbox:"true"},
                    {field: 'id', title: '编号', width: 100, align: 'left'},
                    {field: 'name', title: '街道名字', width: 100, align: 'left'},
                    {field: 'operation', title: '删除', width: 100,
                        formatter: function (value, rec, index) {
                            var del = '<a href="#" class="easyui-linkbutton" data-options="plain:true,iconCls:\'icon-remove\'" onclick="delete1(' + value + ')">删除</a>';
                            return del;
                        }
                    }



                ]]


            });
        })
    </script>
</head>
<body>

<div id="search">
街道名字:<input id="name" class="easyui-validatebox" /> <a
        class="easyui-linkbutton" onclick="search()"
        data-options="iconCls:'icon-search'" >搜索</a> <a
        href="javascript:Add()" plain="true"
        class="easyui-linkbutton" data-options="iconCls:'icon-add'">新增</a> <a
        href="javascript:ModifyBySelect()" onclick="add_bj()" class="easyui-linkbutton"
        data-options="iconCls:'icon-edit'">编辑</a> <a
        href="javascript:void(0);" onclick="add_del()" class="easyui-linkbutton"
        data-options="iconCls:'icon-remove'">批量删除</a>
</div>
<div id="div"></div>
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
                <td>街道名字:</td>
                <td><input type="text" name="name" id="name1" /></td>
            </tr>
            <tr>
                <td>区域名字：</td><td><select>
                    <option value="1004">海淀</option>
                    <option value="1006">朝阳</option>
                    </select></td>
            </tr>
        </table>
    </form>
</div>
</body>
<script type="text/javascript" src="./js/EasyUi.js"></script>
</html>
