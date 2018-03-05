<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div>
	<table>
	      <c:forEach var="vo" items="${list2 }">
	         <tr>
	            <td>&nbsp;${vo.info }</td>
	         </tr>
      </c:forEach>
   </table>	
   <h2>footer</h2>
</div>