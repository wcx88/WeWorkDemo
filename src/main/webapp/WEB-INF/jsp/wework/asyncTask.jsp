<%@ page contentType="text/html; UTF-8" pageEncoding="UTF-8" language="java" isELIgnored="false" %>
<%@ include file="/include/page_init.jsp"%>
<!DOCTYPE html>
<html>
<head>
    <title>微信异步任务</title>
    <jsp:include page="/include/head_init.jsp"></jsp:include>

    <jsp:include page="/include/inc_jquery_resources.jsp"></jsp:include>
    <jsp:include page="/include/inc_bootstrap_resources.jsp"></jsp:include>
</head>
<body>

<div class="container">
    <div class="panel panel-primary">
        <div class="panel-heading">
            微信异步任务
        </div>
        <div style="padding-top: 5px;">
            <ul class="nav nav-tabs" role="tablist" id="myTabs">
                <li role="presentation" class="active"><a href="#replaceDepartment_content">全量覆盖部门</a></li>
                <li role="presentation"><a href="#replaceUser_content">全量覆盖成员</a></li>
                <li role="presentation"><a href="#syncUser_content">增量更新成员</a></li>
                <li role="presentation"><a href="#getResult_content">获取异步任务结果</a></li>
            </ul>
        </div>
        <div class="tab-content">
            <div role="tabpanel" class="panel-body tab-pane active" id="replaceDepartment_content">
                <form class="form-horizontal" action="/winning/wework/replaceDepartment.sdo" target="iframe_1" method="post" enctype="multipart/form-data">
                    <div class="form-group">
                        <label for="csvFile" class="col-sm-2 control-label">全量部门csv文件：</label>
                        <div class="col-sm-10">
                            <input type="file" class="form-control" id="csvFile" name="csvFile">
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="col-sm-offset-2 col-sm-10">
                            <button type="submit" class="btn btn-primary">提交</button>
                        </div>
                    </div>
                </form>
                <label class="col-sm-2 control-label">返回的json串：</label>
                <iframe name="iframe_1" width="100%"></iframe>
            </div>
            <div role="tabpanel" class="panel-body tab-pane" id="replaceUser_content">
                <form class="form-horizontal" action="/winning/wework/replaceUser.sdo" target="iframe_2" method="post" enctype="multipart/form-data">
                    <div class="form-group">
                        <label for="csvFile" class="col-sm-2 control-label">全量成员csv文件：</label>
                        <div class="col-sm-10">
                            <input type="file" class="form-control" id="csvFile" name="csvFile">
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="col-sm-offset-2 col-sm-10">
                            <button type="submit" class="btn btn-primary">提交</button>
                        </div>
                    </div>
                </form>
                <label class="col-sm-2 control-label">返回的json串：</label>
                <iframe name="iframe_2" width="100%"></iframe>
            </div>
            <div role="tabpanel" class="panel-body tab-pane" id="syncUser_content">
                <form class="form-horizontal" action="/winning/wework/syncUser.sdo" target="iframe_3" method="post" enctype="multipart/form-data">
                    <div class="form-group">
                        <label for="csvFile" class="col-sm-2 control-label">增量成员csv文件：</label>
                        <div class="col-sm-10">
                            <input type="file" class="form-control" id="csvFile" name="csvFile">
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="col-sm-offset-2 col-sm-10">
                            <button type="submit" class="btn btn-primary">提交</button>
                        </div>
                    </div>
                </form>
                <label class="col-sm-2 control-label">返回的json串：</label>
                <iframe name="iframe_3" width="100%"></iframe>
            </div>
            <div role="tabpanel" class="panel-body tab-pane" id="getResult_content">
                <form class="form-horizontal" action="/winning/wework/getAsyncJobResult.sdo" target="iframe_4" method="get">
                    <div class="form-group">
                        <label for="jobid" class="col-sm-2 control-label">异步任务id：</label>
                        <div class="col-sm-10">
                            <input type="text" class="form-control" id="jobId" name="jobId">
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="col-sm-offset-2 col-sm-10">
                            <button type="submit" class="btn btn-primary">查询</button>
                        </div>
                    </div>
                </form>
                <label class="col-sm-2 control-label">返回的json串：</label>
                <iframe name="iframe_4" width="100%"></iframe>
            </div>
        </div>
    </div>
</div>

</body>
<script src="/plug-in/wework/js/asyncTask.js"></script>
</html>
