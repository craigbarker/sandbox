<#import "spring.ftl" as spring />
<html>
<head>
    <title>Issue ${issue.issueNumber}</title>
</head>
<body>
<table border="1">
<tr><td>Description</td><td>${issue.summary}</td></tr>
</table>
<a href="<@spring.url "/editissue/${issue.issueNumber}"/>">Edit this issue</a>
</body>
</html>