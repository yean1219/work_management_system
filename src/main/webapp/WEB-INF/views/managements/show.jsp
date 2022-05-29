<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="constants.ForwardConst" %>

<c:set var="actMan" value="${ForwardConst.ACT_MAN.getValue()}" />
<c:set var="commIdx" value="${ForwardConst.CMD_INDEX.getValue()}" />
<c:set var="commEdt" value="${ForwardConst.CMD_EDIT.getValue()}" />

<c:import url="/WEB-INF/views/layout/app.jsp">
    <c:param name="content">

        <h2>作業 詳細ページ</h2>

        <table>
            <tbody>
                <tr>
                    <th>進捗状況</th>
                    <td><c:out value="${management.progess}" /></td>
                </tr>
                <tr>
                    <th>担当者</th>
                    <td><c:out value="${management.employee.name}" /></td>
                </tr>
                <tr>
                    <th>プロジェクト番号</th>
                    <td><pre><c:out value="${management.projectnumber}" /></pre></td>
                </tr>
                <tr>
                    <th>クライアント名</th>
                    <td><pre><c:out value="${management.client}" /></pre></td>
                </tr>
                <tr>
                    <th>依頼者</th>
                    <td><pre><c:out value="${management.requester}" /></pre></td>
                </tr>
                <tr>
                    <th>分量</th>
                    <td><pre><c:out value="${management.quantity}" /></pre></td>
                </tr>
                <tr>
                    <th>内容</th>
                    <td><pre><c:out value="${management.content}" /></pre></td>
                </tr>
                <tr>
                    <th>納期</th>
                    <fmt:parseDate value="${management.deadline}" pattern="yyyy-MM-dd HH:mm:ss" var="deadline" type="date" />
                    <td><fmt:formatDate value="${deadline}" pattern="yyyy-MM-dd HH:mm" /></td>
                </tr>
                <tr>
                    <th>予測完了時間</th>
                    <fmt:parseDate value="${management.predict}" pattern="yyyy-MM-dd HH:mm:ss" var="predict" type="date" />
                    <td><fmt:formatDate value="${predict}" pattern="yyyy-MM-dd HH:mm" /></td>
                </tr>
                <tr>
                    <th>ヘルプ</th>
                    <td><pre><c:out value="${management.help}" /></pre></td>
                </tr>
            </tbody>
        </table>

            <p>
                <a href="<c:url value='?action=${actMan}&command=${commEdt}&id=${management.id}' />">この作業を編集する</a>
            </p>

        <p>
            <a href="<c:url value='?action=${actMan}&command=${commIdx}' />">一覧に戻る</a>
        </p>
    </c:param>
</c:import>