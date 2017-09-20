<%@ page contentType="text/html; UTF-8" pageEncoding="UTF-8" language="java" isELIgnored="false" %>
<%@ include file="/include/page_init.jsp"%>
<!DOCTYPE html>
<html>
<head>
    <title>请稍后</title>
    <jsp:include page="/include/head_init.jsp"></jsp:include>
</head>
<body>
    <div>页面转向中,请稍后...</div>
</body>
<script type="text/javascript">
    var locationpath = location.href.split("/").slice(0,3).join('/');
    var redirect_uri = locationpath + "/winning/wework/getUserInfoAndRedirect.weworkService?p=${paramBase64Str}";
    redirect_uri = encodeURIComponent(redirect_uri);
    // 微信授权登录
    self.location.href = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=${appid}&redirect_uri=" +
        redirect_uri + "&response_type=code&scope=snsapi_base&agentid=${agentid}&state=${state}#wechat_redirect";
</script>
</html>
