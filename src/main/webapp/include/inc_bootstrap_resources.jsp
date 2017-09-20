<%@ page contentType="text/html; UTF-8" pageEncoding="UTF-8" language="java" isELIgnored="false" %>
<%@ include file="page_init.jsp"%>
<script>
    function loadBootstrap() {
        document.write(unescape('%3Clink rel="stylesheet" href="js/common/bootstrap-3.3.7-dist/css/bootstrap.min.css"%3E'));
        document.write(unescape('%3Cscript src="js/common/bootstrap-3.3.7-dist/js/bootstrap.min.js"%3E%3C/script%3E'));
    }
</script>
<link rel="stylesheet" href="//cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script src="//cdn.bootcss.com/bootstrap/3.3.7/js/bootstrap.min.js" onerror="loadBootstrap();"></script>
