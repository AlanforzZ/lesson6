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
            jQuery(function($) {
                //datepicker plugin
                $('.input-daterange').datepicker(
                    {
                        autoclose:true,
                        format: 'yyyy-mm-dd',
                        todayHighlight: true
                    });
                //to translate the daterange picker, please copy the "examples/daterange-fr.js" contents here before initialization
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
                                <sec:authorize ifAnyGranted="OPT_STUDENT_STUDENT_ADD">
                                    <a href="student/student/add.do" class="btn btn-sm btn-primary"><i
                                            class="ace-icon glyphicon glyphicon-plus"></i>
                                        <spring:message code="button.add"/>
                                    </a>
                                </sec:authorize>
                            	</span>
                            </h3>
                            <div class="widget-box">
                                <div class="widget-header">
                                    <h4 class="widget-title">学生搜索</h4>
                                </div>
                                <div class="widget-body">
                                    <div class="widget-main">
                                        <form action="student/student/search.do" class="form-inline">
                                            <label class="inline" for="studentNumber">
                                                学生学号：
                                            </label>
                                            <input type="text" name="studentNumber" id="studentNumber" value="${vo.studentNumber}" autocomplete="off" class="input-sm" placeholder="学号" />
                                            <label class="inline" for="studentName">
                                                学生姓名：
                                            </label>
                                            <input type="text" name="name" id="studentName" value="${vo.name}"  class="input-sm" autocomplete="off" placeholder="姓名" />
                                            <label class="inline" for="studentName">
                                                出生日期范围：
                                            </label>
                                            <div class="inline">
                                                <div class="input-daterange input-group ">
                                                    <input type="text"   class="input-sm form-control" value="${vo.startDate}" name="startDate" />
                                                    <span class="input-group-addon">
																		<i class="fa fa-exchange"></i>
																	</span>
                                                    <input type="text" class="input-sm form-control" value="${vo.endDate}" name="endDate" />
                                                </div>
                                            </div>

                                            <button type="submit" class="btn btn-info btn-sm">
                                                <span class="ace-icon fa fa-search icon-on-right bigger-110"></span>
                                                Search
                                            </button>
                                        </form>
                                    </div>
                                </div>
                            </div>
                            <table id="simple-table" class="table  table-bordered table-hover">
                                <thead>
                                <tr>
                                    <th>序号</th>
                                    <th>学号</th>
                                    <th>姓名</th>
                                    <th>性别</th>
                                    <th class="hidden-480">出生日期</th>
                                    <th class="hidden-480">所在班级</th>
                                    <th class="hidden-480">选修科目数</th>
                                    <th class="hidden-480">平均分</th>
                                    <th class="hidden-480">操作</th>
                                </tr>
                                </thead>

                                <tbody>

                                <c:forEach items="${students}" var="student" varStatus="vs">
                                    <tr id="tr-${student.id}">

                                        <td><c:out value="${vs.count}"></c:out></td>
                                        <td><c:out value="${student.studentNumber}"></c:out></td>
                                        <td><c:out value="${student.name}"></c:out></td>
                                        <td>${student.gender==0?"男":"女"}</td>
                                        <td>${student.birthday}</td>
                                        <td><c:out value="${student.grade.name}"></c:out></td>
                                        <td>${student.scoreList.size()}</td>
                                        <td><fmt:formatNumber value="${student.avgScore}" pattern="##.##"></fmt:formatNumber></td>

                                        <td>
                                            <div class="hidden-sm hidden-xs btn-group action-buttons">

                                                <sec:authorize ifAnyGranted="OPT_STUDENT_STUDENT_EDIT">
                                                    <a title="录入分数" href="student/student/saveScore.do?id=${student.id}"
                                                       class="green">
                                                        <i class="ace-icon fa fa-sign-in bigger-120"></i>
                                                    </a>
                                                </sec:authorize>

                                                <sec:authorize ifAnyGranted="OPT_STUDENT_STUDENT_EDIT">
                                                    <a title="选课" href="student/student/select.do?id=${student.id}"
                                                       class="green">
                                                        <i class="ace-icon fa fa-tags bigger-120"></i>
                                                    </a>
                                                </sec:authorize>

                                                <sec:authorize ifAnyGranted="OPT_STUDENT_STUDENT_EDIT">
                                                    <a title="编辑" href="student/student/edit.do?id=${student.id}"
                                                       class="green">
                                                        <i class="ace-icon fa fa-pencil-square-o bigger-120"></i>
                                                    </a>
                                                </sec:authorize>
                                                <sec:authorize ifAnyGranted="OPT_STUDENT_STUDENT_DELETE">
                                                    <c:if test="${param.enabled != 'false'}">
                                                        <a title="删除" class="btn-delete-modal red" data-url="student/student/delete.do"
                                                           data-title="<spring:message code="button.disable"/>"
                                                           data-id="${student.id}">
                                                            <i class="ace-icon fa fa-trash-o bigger-120"></i>
                                                        </a>
                                                    </c:if>
                                                </sec:authorize>
                                            </div>
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
