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
            $('#search').datagrid({
                title: "已审核房屋信息",
                url: 'getHouse',  //服务器地址
                pagination: true,  //启用分页
                toolbar: "#ToolBar",  //工具栏
                rownumbers: true,  //显示行号
                //singleSelect:true,  //实现单行选择
                pageList: [3, 6, 9, 15, 20], //设置每页大小
                pageSize: 3, //每页三条
                columns: [[
                    {field: 'ck', checkbox: true, width: 100, align: 'left'},
                    {field: 'id', title: '编号', width: 100, align: 'left'},
                    {field: 'title', title: '标题', width: 100, align: 'left'},
                    {field: 'price', title: '价格', width: 100, align: 'left'},
                    {field: 'pubdate', title: '发布日期', width: 100, align: 'left'},
                    {field: 'contact', title: '联系方式', width: 100, align: 'left'},
                    {field: 'floorage', title: '面积', width: 100, align: 'left'},
                    {field: 'dname', title: '区域名称', width: 100, align: 'left'},
                    {field: 'sname', title: '街道名称', width: 100, align: 'left'},
                    {field: 'tname', title: '类型', width: 100, align: 'left'},
                    {
                        field: 'opt', title: '操作', width: 100, align: 'left',
                        formatter: function (value, row, index) {
                            //发送同步请求
                            // return "<a href=\"delDistrict?id="+row.id+"\" class=\"easyui-linkbutton\" iconCls=\"icon-ok\">删除</a>";
                            //发送异步请求Ajax
                            return "<a href=\"javascript:noPassHouse("+row.id+")\" class=\"easyui-linkbutton\" iconCls=\"icon-ok\">未审核</a>";

                        }
                    }
                ]]
            });
        })

    </script>
</head>
<body>

<div id="search"></div>
<div id="ToolBar">街道名字:<input id="sname" class="easyui-validatebox" />
    区域名字:<input id="dname" class="easyui-validatebox"><a
        class="easyui-linkbutton" onclick="search()"
        data-options="iconCls:'icon-search'" >搜索</a> <a
        href="javascript:Add()" plain="true"
        class="easyui-linkbutton" data-options="iconCls:'icon-add'">新增</a> <a
        href="javascript:ModifyBySelect()" onclick="add_bj()" class="easyui-linkbutton"
        data-options="iconCls:'icon-edit'">编辑</a> <a
        href="javascript:void(0);" onclick="add_del()" class="easyui-linkbutton"
        data-options="iconCls:'icon-remove'">批量删除</a></div>
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
<script type="text/javascript" src="./js/House.js"></script>
</html>
