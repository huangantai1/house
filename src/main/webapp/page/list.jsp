<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3c.org/TR/1999/REC-html401-19991224/loose.dtd">
<!-- saved from url=(0030)http://localhost:8080/House-2/ -->
<HTML xmlns="http://www.w3.org/1999/xhtml"><HEAD><TITLE>青鸟租房 - 首页</TITLE>
<META content="text/html; charset=utf-8" http-equiv=Content-Type>
  <script language="JavaScript" src="../js/jquery-1.8.3.js"></script>
  <script language="JavaScript">
      $(function(){  //加载事件
          //使用异步加载类型
          $.post("getType",null,function(data){
              for(var i=0;i<data.length;i++){
                  //创建option
                  var node=$("<option value="+data[i].id+">"+data[i].name+"</option>");
                  $("#typeid").append(node);
              }

              //设置选中项
              $("#typeid").val(${condition.typeid});
          },"json");


          //使用异步加载区域
          $.post("getDistrict",null,function(data){
              for(var i=0;i<data.length;i++){
                  //创建option
                  var node=$("<option value="+data[i].id+">"+data[i].name+"</option>");
                  $("#districtid").append(node);
              }

              //设置区域的选中项
              $("#districtid").val(${condition.districtid});


              //加载街道
              loadStreet( $("#districtid").val());
          },"json");






          //给区域列表添加改变事件
          $("#districtid").change(function(){
              loadStreet($(this).val());  //重新加载街道
          });
      });

      function loadStreet(did){
          //发送异步请求获取区域下的街道
          $.post("getStreetByDid",{"id":did},function (data) {
              //清空原有数据
              $("#streetid>option:gt(0)").remove();
              for(var i=0;i<data.length;i++){
                  //使用$()工厂函数创建标签
                  var node=$("<option value="+data[i].id+">"+data[i].name+"</option>");
                  //将创建的标签添加下拉列表
                  $("#streetid").append(node);
              }
              $("#streetid").val(${condition.districtid});  //设置街道的选中项
          },"json");
      }
      function gopage(pageNum) {
          $("#page").val(pageNum);
          $("#sform").submit();

      }
  </script>
<LINK rel=stylesheet type=text/css href="../css/style.css">
<META name=GENERATOR content="MSHTML 8.00.7601.17514"></HEAD>
<BODY>
<DIV id=header class=wrap>
<DIV id=logo><IMG src="../images/logo.gif"></DIV></DIV>
<DIV id=navbar class=wrap>
<DL class="search clearfix">
  <FORM id=sform method=post action=getBorswerHouse>
  <DT>
  <UL>
    <LI class=bold>房屋信息</LI>
    <LI>标题：<INPUT class=text type=text name=title id="title" value="${condition.title}" style="width: 40%"> <input id="page" name="page" value="1" type="hidden"><LABEL class=ui-blue></LABEL>
    </LI></UL></DT>
  <DD>
  <UL>
    <LI class=first>区域</LI>
    <LI><SELECT name=districtid id="districtid"> <OPTION selected value="">不限</OPTION>
     </SELECT> </LI></UL></DD>
  <DD>
  <UL>
    <LI class=first>街道</LI>
    <LI><SELECT id=streetid name=streetid> <OPTION selected
      value="">不限</OPTION></SELECT> </LI></UL></DD>
  <DD>
  <UL>
    <LI class=first>房型</LI>
    <LI><SELECT name=typeid id="typeid"> <OPTION selected value="">不限</OPTION> </SELECT>
  </LI></UL></DD>
  <DD>
  <UL> <LI class=first>价格 <LABEL class=ui-blue></LABEL>
  </LI><li><INPUT class=text type=text name=startPrice id="startPrice" value="${condition.startPrice}" style="width: 30%">到<INPUT class=text type=text name=endPrice id="endPrice" value="${condition.endPrice}"  style="width: 30%"><INPUT onclick=doSearch() value=搜索房屋 type=submit name=search></li></UL></DD></FORM></DL></DIV>
<DIV class="main wrap">
  <c:if test="${!empty pageInfo.list}">
<TABLE class=house-list>
  <TBODY>
  <c:forEach items="${pageInfo.list}" var="h">


  <TR>
    <TD class=house-thumb><span><A href="details.htm" target="_blank"><img src="http://localhost:80/${h.path}" width="100" height="75" alt=""></a></span></TD>
    <TD>
      <DL>
        <DT><A href="details.htm" target="_blank">${h.title}</A></DT>
        <DD>${h.dname}${h.sname},${h.floorage}平米<BR>联系方式：${h.contact} </DD></DL></TD>
    <TD class=house-type>${h.tname}</TD>
    <TD class=house-price><SPAN>${h.price}</SPAN>元/月</TD></TR>
  </TD></TR>
   </c:forEach></TBODY></TABLE>
<DIV class=pager>
<UL>
  <LI class=current><A href="javascript:gopage(1)">首页</A></LI>
  <LI><A href="javascript:gopage(${pageInfo.prePage==0?1:pageInfo.prePage})">上一页</A></LI>
  <LI><A href="javascript:gopage(${pageInfo.nextPage==0?pageInfo.pages:pageInfo.nextPage})">下一页</A></LI>
  <LI><A href="javascript:gopage(${pageInfo.pages})">末页</A></LI></UL><SPAN
class=total>${pageInfo.pageNum}/${pageInfo.pages}页</SPAN> </DIV></c:if>
<c:if test="${empty pageInfo.list}">
  <center style="color: red;font-size: 24px" >暂无出租房信息</center>
</c:if> </DIV>
<DIV id=footer class=wrap>
<DL>
  <DT>青鸟租房 © 2018 北大青鸟 京ICP证1000001号</DT>
  <DD>关于我们 · 联系方式 · 意见反馈 · 帮助中心</DD></DL></DIV></BODY></HTML>
