<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
<%--<%@ page import="java.util.*"  %> 
<%@ page import="com.model2.mvc.common.Search" %>
<%@page import="com.model2.mvc.common.Page"%>
<%@page import="com.model2.mvc.common.util.CommonUtil"%>
<%@ page import="com.model2.mvc.service.domain.*" %>
    
<%
List<Purchase> list= (List<Purchase>)request.getAttribute("list");
List<User> list02= (List<User>)request.getAttribute("list02");
Page resultPage=(Page)request.getAttribute("resultPage");
//System.out.println("jsp"+list02);
Search searchVO = (Search)request.getAttribute("search");
//==> null �� ""(nullString)���� ����
String searchCondition = CommonUtil.null2str(searchVO.getSearchCondition());
String searchKeyword = CommonUtil.null2str(searchVO.getSearchKeyword());
//User user = (User)session.getAttribute("userVO");
//Product productVO = (Product)request.getAttribute("productVO");
//User user = (User)session.getAttribute("user");


%>--%> 
	
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>���� �����ȸ</title>

<link rel="stylesheet" href="/css/admin.css" type="text/css">

<script type="text/javascript">

function fncGetList(currentPage) {
	document.getElementById("currentPage").value = currentPage;
   	document.detailForm.submit();		
}
</script>
</head>

<body bgcolor="#ffffff" text="#000000">

<div style="width: 98%; margin-left: 10px;">

<form name="detailForm" action="/listPurchase.do" method="post">

<table width="100%" height="37" border="0" cellpadding="0"	cellspacing="0">
	<tr>
		<td width="15" height="37"><img src="/images/ct_ttl_img01.gif"width="15" height="37"></td>
		<td background="/images/ct_ttl_img02.gif" width="100%" style="padding-left: 10px;">
			<table width="100%" border="0" cellspacing="0" cellpadding="0">
				<tr>
					<td width="93%" class="ct_ttl01">���� �����ȸ</td>
				</tr>
			</table>
		</td>
		<td width="12" height="37"><img src="/images/ct_ttl_img03.gif"	width="12" height="37"></td>
	</tr>
</table>

<table width="100%" border="0" cellspacing="0" cellpadding="0"	style="margin-top: 10px;">
	<tr>
		<td colspan="11">��ü ${resultPage.totalCount } �Ǽ�, ���� ${resultPage.currentPage}  ������</td>
	</tr>
	<tr>
		<td class="ct_list_b" width="100">No</td>
		<td class="ct_line02"></td>
		<td class="ct_list_b" width="150">ȸ��ID</td>
		<td class="ct_line02"></td>
		<td class="ct_list_b" width="150">ȸ����</td>
		<td class="ct_line02"></td>
		<td class="ct_list_b">��ȭ��ȣ</td>
		<td class="ct_line02"></td>
		<td class="ct_list_b">�����Ȳ</td>
		<td class="ct_line02"></td>
		<td class="ct_list_b">��������</td>
	</tr>
	<tr>
		<td colspan="11" bgcolor="808285" height="1"></td>
	</tr>

	
<%--<%
	for(int i=0; i<list.size(); i++) {
		Purchase purchase = (Purchase)list.get(i);
		User user = (User)list02.get(i);
		
	%>	--%>
	
	<c:set var="i" value="0" />
	<c:forEach var="purchase" items="${list}">	
		<c:set var="i" value="${ i+1 }" />
	<tr class="ct_list_pop">
		<td align="center">
			<a href="/getPurchase.do?tranNo=${purchase.tranNo}">${ i }</a>
		</td>
		<td></td>
		<td align="left">
			<a href="/getUser.do?userId=${purchase.buyer.userId }">${purchase.buyer.userId }</a>
		</td>
		<td></td>
		<td align="left">${purchase.receiverName}</td>
		<td></td>
		<td align="left">${purchase.receiverPhone}</td>
		<td></td>
		<td align="left"><c:if test = "${purchase.tranCode==null}">
				�Ǹ���
			</c:if>
			<c:if test = "${purchase.tranCode=='0  '}">
				���ſϷ�
				<a href="/updatePurchaseView.do?tranNo=${purchase.tranNo}">�����������</a>
				<a href="/deletePurchase.do?tranNo=${purchase.tranNo}">ȯ���ϱ�</a>
			</c:if>
			<c:if test = "${purchase.tranCode=='1  '}">
				�����
			</c:if>
			<c:if test = "${purchase.tranCode=='2  '}">
				��ۿϷ�  (����� : ${purchase.divyAddr})
			</c:if></td>
		<td></td>
		<td align="left">
		<c:if test = "${purchase.tranCode=='1  '}">
			����� (��������,ȯ�ҺҰ�)
		</c:if>
		<c:if test = "${purchase.tranCode=='2  '}">
			��ۿϷ� (��������,ȯ�ҺҰ�)
		</c:if>
		<c:if test = "${purchase.tranCode=='1  '}">
		<a href="/updateTranCode.do?tranNo=${purchase.tranNo}&tranCode=${purchase.tranCode}">���ǵ���</a>		
		</c:if>
		</td>
	</tr>
	<tr>
		<td colspan="11" bgcolor="D6D7D6" height="1"></td>
	</tr>
	</c:forEach>
	<%--<%} %>--%>
</table>

<table width="100%" border="0" cellspacing="0" cellpadding="0" style="margin-top: 10px;">
	<tr>
		<td align="center">
		 
			<input type="hidden" id="currentPage" name="currentPage" value=""/>
			<%--<% if( resultPage.getCurrentPage() <= resultPage.getPageUnit() ){ %>
					�� ����
			<% }else{ %>
					<a href="javascript:fncGetPurchaseList('<%=resultPage.getCurrentPage()-1%>')">�� ����</a>
			<% } %>

			<%	for(int i=resultPage.getBeginUnitPage();i<= resultPage.getEndUnitPage() ;i++){	%>
					<a href="javascript:fncGetPurchaseList('<%=i %>');"><%=i %></a>
			<% 	}  %>
	
			<% if( resultPage.getEndUnitPage() >= resultPage.getMaxPage() ){ %>
					���� ��
			<% }else{ %>
					<a href="javascript:fncGetPurchaseList('<%=resultPage.getEndUnitPage()+1%>')">���� ��</a>
			<%} %>--%>
		<jsp:include page="../common/pageNavigator.jsp"/>	
		</td>
	</tr>
</table>

<!--  ������ Navigator �� -->
</form>

</div>

</body>
</html>