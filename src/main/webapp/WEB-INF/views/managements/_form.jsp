<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="constants.AttributeConst" %>

<c:if test="${errors != null}">
    <div id="flush_error">
        入力内容にエラーがあります。<br />
        <c:forEach var="error" items="${errors}">
            ・<c:out value="${error}" /><br />
        </c:forEach>

    </div>
</c:if>


<label for="${AttributeConst.MAN_PROGESS.getValue()}">進捗状況</label><br />
<select name="progess">
    <option value="未">未</option>
    <option value="進行中">進行中</option>
    <option value="完了">完了</option>
</select>
<br /><br />

<label for="name">担当者</label><br />
<select name="${AttributeConst.EMPLOYEE.getValue()}">
    <c:forEach var="employee" items="${employees}" varStatus="status">
        <option value= "${employee.id }" <c:if test="${employee.id == management.employee.id}">selected</c:if>>
            <c:out value="${employee.name}"/>
        </option>
    </c:forEach>
</select>
<br /><br />

<label for="${AttributeConst.MAN_PROJECTNUMBER.getValue()}">プロジェクト番号</label><br />
<input type="text" name="${AttributeConst.MAN_PROJECTNUMBER.getValue()}" value="${management.projectnumber}" />
<br /><br />

<label for="${AttributeConst.MAN_CLIENT.getValue()}">クライアント名</label><br />
<input type="text" name="${AttributeConst.MAN_CLIENT.getValue()}" value="${management.client}" />
<br /><br />

<label for="${AttributeConst.MAN_REQUESTER.getValue()}">依頼者</label><br />
<input type="text" name="${AttributeConst.MAN_REQUESTER.getValue()}" value="${management.requester}" />
<br /><br />

<label for="${AttributeConst.MAN_QUANTITY.getValue()}">分量</label><br />
<input type="text" name="${AttributeConst.MAN_QUANTITY.getValue()}" value="${management.quantity}" />
<br /><br />


<label for="${AttributeConst.MAN_CONTENT.getValue()}">内容</label><br />
<textarea name="${AttributeConst.MAN_CONTENT.getValue()}" rows="10" cols="50">${management.content}</textarea>
<br /><br />


<fmt:parseDate value="${management.deadline}" pattern="yyyy-MM-dd HH:mm:ss" var="deadline" type="date" />

 <c:set var="datePattern" value="yyyy-MM-dd'T'HH:mm" />
<label for="${AttributeConst.MAN_DEADLINE.getValue()}">納期</label><br />
<input type="datetime-local" name="${AttributeConst.MAN_DEADLINE.getValue()}" value="<fmt:formatDate value='${deadline}' pattern='${datePattern}' />" />
<br /><br />

<fmt:parseDate value="${management.predict}" pattern="yyyy-MM-dd HH:mm:ss" var="predict" type="date" />

<label for="${AttributeConst.MAN_PREDICT.getValue()}">予測完了時間</label><br />
<input type="datetime-local" name="${AttributeConst.MAN_PREDICT.getValue()}" value="<fmt:formatDate value='${predict}' pattern='${datePattern}' />" />
<br /><br />

<label for="${AttributeConst.MAN_HELP.getValue()}">ヘルプ</label><br />
<div>
      <input type="radio" id="need" name="help" value="要"
             checked>
      <label for="need">要</label>
    </div>

    <div>
      <input type="radio" id="not_need" name="help" value="不要">
      <label for="not_need">不要</label>
    </div>
<br /><br />

<input type="hidden" name="${AttributeConst.MAN_ID.getValue()}" value="${management.id}" />
<input type="hidden" name="${AttributeConst.TOKEN.getValue()}" value="${_token}" />
<button type="submit">投稿</button>