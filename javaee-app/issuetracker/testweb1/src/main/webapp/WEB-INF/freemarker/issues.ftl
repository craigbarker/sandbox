<#assign c=JspTaglibs["http://java.sun.com/jsp/jstl/core"]>
<html>
<head>
    <title>Issues list</title>
</head>
<body>
<#list issues as issue>
<p><a href="${contextPath}/issue/${issue.issueNumber}">${issue.issueNumber}</a></p>
</#list>
</body>
</html>