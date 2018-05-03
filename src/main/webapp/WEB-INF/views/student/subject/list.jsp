<%@ page contentType="text/html; charset=utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="lesson" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="lessonTag" uri="http://com.biz.lesson/tag/core" %>
<lesson:page title="user.title.list">
    <jsp:attribute name="css">
        <style type="text/css">
            #name-of-ban-user, #name-of-reset-user {
                font-weight: bold;
                color: red;
            }

            #password-not-match-msg {
                display: none;
                color: #a94442;
            }
        </style>
    </jsp:attribute>
    <jsp:attribute name="script">
        <script type="application/javascript">
            var active_class = 'active';
            $('#simple-table > thead > tr > th input[type=checkbox]').eq(0).on('click', function () {
                var th_checked = this.checked;//checkbox inside "TH" table header

                $(this).closest('table').find('tbody > tr').each(function () {
                    var row = this;
                    if (th_checked) $(row).addClass(active_class).find('input[type=checkbox]').eq(0).prop('checked', true);
                    else $(row).removeClass(active_class).find('input[type=checkbox]').eq(0).prop('checked', false);
                });
            });

            //select/deselect a row when the checkbox is checked/unchecked
            $('#simple-table').on('click', 'td input[type=checkbox]', function () {
                var $row = $(this).closest('tr');
                if ($row.is('.detail-row ')) return;
                if (this.checked) $row.addClass(active_class);
                else $row.removeClass(active_class);
            });


        </script>
    </jsp:attribute>
    <jsp:body>
        <div class="breadcrumbs ace-save-state" id="breadcrumbs">
            <ul class="breadcrumb">
                <li>
                    <i class="ace-icon fa fa-home home-icon"></i>
                    <a href="welcome.do">
                        <spring:message code="common.homepage"/>
                    </a>
                </li>
                <li class="active">
                    <spring:message code="user.title.list"/>
                </li>
            </ul><!-- /.breadcrumb -->
        </div>

        <div class="page-content">
            <input type="hidden" id="id-of-user">
            <div class="row">
                <div class="col-xs-12">
                    <!-- PAGE CONTENT BEGINS -->
                    <div class="row">
                        <div class="col-xs-12">
                            <h3 class="header smaller lighter blue">
                                <spring:message code="user.title.list"/>
                                <span class=" btn-group pull-right">
                                <sec:authorize ifAnyGranted="OPT_STUDENT_SUBJECT_ADD">
                                    <a href="student/subject/add.do" class="btn btn-sm btn-primary"><i
                                            class="ace-icon glyphicon glyphicon-plus"></i>
                                        <spring:message code="button.add"/>
                                    </a>
                                </sec:authorize>
                            	</span>
                            </h3>
                            <table id="simple-table" class="table  table-bordered table-hover">
                                <thead>
                                <tr>
                                    <%--<th class="center">
                                        <label class="pos-rel">
                                            <input type="checkbox" class="ace"/>
                                            <span class="lbl"></span>
                                        </label>
                                    </th>--%>
                                    <th>序号</th>
                                    <th>学科名</th>
                                    <th>选修人数</th>
                                    <th class="hidden-480">平均分</th>
                                    <th class="hidden-480">修改</th>
                                    <th class="hidden-480">删除</th>
                                </tr>
                                </thead>
                                <tbody>
                                <c:forEach items="${subjects}" var="subject" varStatus="vs">
                                    <tr id="tr-${subject.id}">
                                        <%--<td class="center">
                                            <label class="pos-rel">
                                                <input type="checkbox" class="ace"/>
                                                <span class="lbl"></span>
                                            </label>
                                        </td>--%>


                                        <td>
                                                ${vs.count}
                                        </td>
                                        <td>
                                                ${subject.name}
                                        </td>
                                        <td>
                                                ${subject.scoreList.size()}
                                        </td>
                                        <td>
                                               <fmt:formatNumber value="${subject.avgScore}" pattern="##.##"></fmt:formatNumber>
                                        </td>

                                        <td>
                                            <a title="编辑" href="student/subject/edit.do?id=${subject.id}"
                                               class="green">
                                                <i class="ace-icon fa fa-pencil-square-o bigger-120"></i>
                                            </a>
                                        </td>
                                        <td>
                                            <a title="删除" class="btn-delete-modal red" data-url="student/subject/delete.do"
                                               data-title="<spring:message code="button.disable"/>"
                                               data-id="${subject.id}">
                                                <i class="ace-icon fa fa-trash-o bigger-120"></i>
                                            </a>
                                        </td>
                                    </tr>
                                </c:forEach>
                                </tbody>
                            </table>
                        </div><!-- /.span -->
                    </div><!-- /.row -->
                    <!-- PAGE CONTENT ENDS -->
                </div><!-- /.col -->
            </div><!-- /.row -->
        </div>
    </jsp:body>
</lesson:page>
