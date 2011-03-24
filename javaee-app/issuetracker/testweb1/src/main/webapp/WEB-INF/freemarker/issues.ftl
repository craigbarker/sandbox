<#import "spring.ftl" as spring />
<html>
<head>
    <title>Issues list</title>
</head>
<body>
<#list issues as issue>
<p><a href="<@spring.url "/issue/${issue.issueNumber}"/>">${issue.issueNumber}</a></p>
</#list>
</body>
</html>