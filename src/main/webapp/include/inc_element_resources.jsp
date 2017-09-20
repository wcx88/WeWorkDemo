<%@ page contentType="text/html; UTF-8" pageEncoding="UTF-8" language="java" isELIgnored="false" %>
<%@ include file="page_init.jsp"%>
<script>
    function loadElementUI() {
        document.write(unescape('%3Clink rel="stylesheet" href="js/common/element-ui-1.4.4/lib/theme-default/index.css"%3E'));
        document.write(unescape('%3Cscript src="js/common/element-ui-1.4.4/lib/index.js"%3E%3C/script%3E'));
    }
</script>
<link href="https://cdn.bootcss.com/element-ui/1.4.3/theme-default/index.css" rel="stylesheet">
<script src="https://cdn.bootcss.com/element-ui/1.4.3/index.js" onerror="loadElementUI();"></script>
<%--<link rel="stylesheet" href="https://unpkg.com/element-ui/lib/theme-default/index.css">--%>
<%--<script src="https://unpkg.com/element-ui/lib/index.js" onerror="loadElementUI();"></script>--%>