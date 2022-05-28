<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="constants.ForwardConst" %>

<c:set var="actMan" value="${ForwardConst.ACT_MAN.getValue()}" />
<c:set var="commIdx" value="${ForwardConst.CMD_INDEX.getValue()}" />
<c:set var="commShow" value="${ForwardConst.CMD_SHOW.getValue()}" />
<c:set var="commNew" value="${ForwardConst.CMD_NEW.getValue()}" />

<c:import url="/WEB-INF/views/layout/app.jsp">
    <c:param name="content">
        <c:if test="${flush != null}">
            <div id="flush_success">
                <c:out value="${flush}"></c:out>
            </div>
        </c:if>
        <h2>作業　一覧</h2>
        <table id="management_list">
            <tbody>
                <tr>
                    <th class="management_progess">進捗状況</th>
                    <th class="management_manager">担当者</th>
                    <th class="management_projectnumber">プロジェクト番号</th>
                    <th class="management_client">クライアント名</th>
                    <th class="management_requester">依頼者</th>
                    <th class="management_quantity">分量</th>
                    <th class="management_content">内容</th>
                    <th class="management_deadline">納期</th>
                    <th class="management_predict">予測完了時間</th>
                    <th class="management_help">ヘルプ</th>
                    <th class="management_action">操作</th>
                </tr>
                <c:forEach var="management" items="${managements}" varStatus="status">
                    <fmt:parseDate value="${management.deadline}" pattern="yyyy-MM-dd" var="deadline" type="date" />
                    <fmt:parseDate value="${management.predict}" pattern="yyyy-MM-dd" var="predict" type="date" />

                    <tr class="row${status.count % 2}">
                        <td class="management_progess"><c:out value="${management.progess}" /></td>
                        <td class="management_manager"><c:out value="${management.manager}" /></td>
                        <td class="management_projectnumber">${management.projectnumber}</td>
                        <td class="management_client">${management.client}</td>
                        <td class="management_requester">${management.requester}</td>
                        <td class="management_quantity">${management.quantity}</td>
                        <td class="management_content">${management.content}</td>
                        <td class="management_deadline"><fmt:formatDate value='${deadline}' pattern='yyyy-MM-dd' /></td>
                        <td class="management_predict"><fmt:formatDate value='${predict}' pattern='yyyy-MM-dd' /></td>
                        <td class="management_help">${management.help}</td>
                        <td class="report_action"><a href="<c:url value='?action=${actMan}&command=${commShow}&id=${management.id}' />">詳細を見る</a></td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>

        <div id="pagination">
            （全 ${managements_count} 件）<br />
            <c:forEach var="i" begin="1" end="${((managements_count - 1) / maxRow) + 1}" step="1">
                <c:choose>
                    <c:when test="${i == page}">
                        <c:out value="${i}" />&nbsp;
                    </c:when>
                    <c:otherwise>
                        <a href="<c:url value='?action=${actMan}&command=${commIdx}&page=${i}' />"><c:out value="${i}" /></a>&nbsp;
                    </c:otherwise>
                </c:choose>
            </c:forEach>
        </div>
        <p><a href="<c:url value='?action=${actMan}&command=${commNew}' />">新規作業の登録</a></p>

    </c:param>
</c:import>