<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<HEAD><TITLE>青鸟租房 - 用户注册</TITLE>
</HEAD>
<BODY>
<DIV id=header class=wrap>
<DIV id=logo><IMG src="../images/logo.gif"></DIV></DIV>
<DIV id=regLogin class=wrap>
<DIV class=dialog>
<DL class=clearfix>
  <DT>新用户注册</DT>
  <DD class=past>填写个人信息</DD></DL>
<DIV class=box>
<FORM action=reg method="post" name="d">
<DIV class=infos>
<TABLE class=field>
  <TBODY>
  <TR>
    <TD class=field>用 户 名：</TD>
    <TD><INPUT class=text type=text name=name id="uname"> <span id="unotice"></span><input id="btn1" type="button" value="检查用户名是否存在"></TD></TR>
  <TR>
    <TD class=field>密　　码：</TD>
    <TD><INPUT class=text type=password name=password></TD></TR>
  <TR>
    <TD class=field>确认密码：</TD>
    <TD><INPUT class=text type=password name=repassword> </TD></TR>
  <TR>
    <TD class=field>电　　话：</TD>
    <TD><INPUT class=text type=text name=telephone> </TD></TR>
  <TR>
    <TD class=field>年龄：</TD>
    <TD><INPUT class=text type=text name=age> </TD></TR>

</TABLE>
<DIV class=buttons>
<INPUT  value=立即注册 type=submit>
</DIV></DIV></FORM></DIV></DIV></DIV>
<DIV id=footer class=wrap>
<DL>
  <DT>青鸟租房 © 2018 北大青鸟 京ICP证1000001号</DT>
  <DD>关于我们 · 联系方式 · 意见反馈 · 帮助中心</DD></DL></DIV></BODY>
<script language="JavaScript" src="../js/jquery-1.8.3.js">
</script>
<script language="JavaScript">
    $(function(){  //加载事件
        $("#btn1").click(function(){  //给按钮添加点事件
            //发送异步请求
            var username=$("#uname").val();
            $.post("checkUname",{"name":username},function (data){
                if(data.result==1){
                    $("#unotice").html("用户名已存在").css("color","red");
                }else{
                    $("#unotice").html("用户名可用").css("color","green");
                }
            },"json");


        });
    });
</script>


</HTML>
