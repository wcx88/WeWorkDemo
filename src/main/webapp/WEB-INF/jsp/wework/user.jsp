<%@ page contentType="text/html; UTF-8" pageEncoding="UTF-8" language="java" isELIgnored="false" %>
<%@ include file="/include/page_init.jsp"%>
<!DOCTYPE html>
<html>
<head>
    <title>成员管理</title>
    <jsp:include page="/include/head_init.jsp"></jsp:include>

    <jsp:include page="/include/inc_resources.jsp"></jsp:include>


    <style type="text/css">
        .container .result-area {
            min-height: 60px;
            border-radius: 6px;
            padding: 20px 25px 20px 12px;
            background-color: #eee;
            word-break: break-all;
        }
        .container .btn-copy {
            position: absolute;
            top: 5px;
            right: 20px;
        }
    </style>
</head>
<body>

<div class="container">
    <div class="panel panel-primary">
        <div class="panel-heading">
            成员管理
        </div>
        <div style="padding-top: 5px;">
            <ul class="nav nav-tabs" role="tablist" id="myTabs">
                <li role="presentation" class="active"><a href="#getUser_content">查询成员</a></li>
                <li role="presentation"><a href="#createUser_content">成员新增</a></li>
            </ul>
        </div>
        <div class="tab-content">
            <div role="tabpanel" class="panel-body tab-pane active" id="getUser_content">
                <form class="form-horizontal" v-loading="loading" element-loading-text="正在查询中...">
                    <div class="form-group">
                        <label for="csvFile" class="col-sm-2 control-label">成员UserId：</label>
                        <div class="col-sm-2">
                            <div class="input-group">
                                <input type="text" class="form-control" v-model="userId" value="5328">
                                <span class="input-group-btn">
                                    <button type="button" @click="query" class="btn btn-primary">查询</button>
                                </span>
                            </div>
                        </div>
                    </div>
                    <div class="line mb10"></div>
                    <user-component :user-info="userInfo" :editabled="editabled"></user-component>
                    <div class="form-group hidden">
                        <label class="col-sm-2 control-label">返回的json串：</label>
                        <div class="col-sm-10">
                            <div class="result-area" v-text="retJsonStr"></div>
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="col-sm-12 tc">
                            <button type="button" @click="edit" class="btn btn-primary">编辑</button>
                            <button type="button" @click="submitEdit" class="btn btn-primary">确定</button>
                            <button type="button" @click="cancelEdit" class="btn btn-primary">取消</button>
                        </div>
                    </div>
                </form>
            </div>
            <div role="tabpanel" class="panel-body tab-pane" id="createUser_content">
                <form class="form-horizontal" >

                    <user-component :user-info="userInfo" editabled="true"></user-component>
                    <div class="form-group">
                        <div class="col-sm-offset-2 col-sm-10">
                            <button type="button" @click="submitEdit" class="btn btn-primary">确定</button>
                            <button type="button" @click="cancelEdit" class="btn btn-primary">取消</button>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>

</body>
<script src="${contextPath}/js/wework/user_component.js?v=1.111"></script>
<script src="${contextPath}/js/wework/user.js?v=1.3"></script>
</html>
