<%@ page contentType="text/html; UTF-8" pageEncoding="UTF-8" language="java" isELIgnored="false" %>
<%@ include file="/include/page_init.jsp"%>
<!DOCTYPE html>
<html>
<head>
    <title>微信辅助工具</title>
    <jsp:include page="/include/head_init.jsp"></jsp:include>

    <jsp:include page="/include/inc_resources.jsp"></jsp:include>
</head>
<body>

<div class="container">
    <div class="panel panel-primary">
        <div class="panel-heading">
            微信辅助工具
        </div>
        <div style="padding-top: 5px;">
            <ul class="nav nav-tabs" role="tablist" id="myTabs">
                <li role="presentation" class="active"><a href="#tools_content">生成跳转网页url</a></li>
            </ul>
        </div>
        <div class="tab-content">
            <div role="tabpanel" class="panel-body tab-pane active" id="tools_content">
                <form class="form-horizontal" id="input_form">
                    <div class="form-group">
                        <label for="serviceType" class="col-sm-2 control-label">服务入口URL：</label>
                        <div class="col-sm-10">
                            <div class="radio" id="serviceType">
                                <label><input type="radio" v-model="serviceType" value="1" >生产环境</label>
                                <label><input type="radio" v-model="serviceType" value="2" >测试环境</label>
                                <label><input type="radio" v-model="serviceType" value="9">自定义</label>
                            </div>
                            <div>
                                <input v-show="serviceType==9" type="text" class="form-control" v-model="serviceUrlInput"
                                       placeholder="示例：http://weberp.winning.com.cn:9083/xxx?xxx=xxx">
                                <span v-show="serviceType!=9">{{serviceUrl}}</span>
                            </div>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="appName" class="col-sm-2 control-label">微信应用名：</label>
                        <div class="col-sm-10">
                            <input type="text" class="form-control" id="appName" v-model="appName" value="pmis">
                            <div>*对应配置文件【\LiveBos\FormBuilder\WEB-INF\classes\config.json】中配置的应用名</div>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="appName" class="col-sm-2 control-label">动作类型：</label>
                        <div class="col-sm-10">
                            <div class="radio" id="actionType">
                                <label><input type="radio" v-model="action"
                                              value="url">跳转到网页</label>
                            </div>
                            <input type="hidden" class="form-control" id="action" value="{{action}}" disabled="disabled">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="redirect_url" class="col-sm-2 control-label">重定向url：</label>
                        <div class="col-sm-10">
                            <input type="text" class="form-control" id="redirect_url" v-model="redirect_url"
                                   placeholder="示例：/UIProcessor?Table=PM_GZRZAT&operate=PM_GZRZAT_M2"
                            >
                        </div>
                    </div>
                    <br>
                    <div class="form-group">
                        <label class="col-sm-2 control-label">微信菜单跳转网址：</label>
                        <div class="col-sm-10">
                            <div id="newUrl"  class="jumbotron" style="padding:20px 12px 20px 12px;background-color: #eee;min-height: 60px;"
                            >{{newUrl}}</div>
                        </div>
                    </div>

                    <div class="form-group">
                        <div class="col-sm-offset-2 col-sm-10">
                            <a class="btn btn-primary btn-lg" href="javascript:;" role="button" id="copyBtn"
                               data-clipboard-target="#newUrl" aria-label="This is the tooltip.">
                                复制到剪切板
                            </a>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>

</body>
<script src="js/wework/tools.js?v=1.1"></script>
</html>
