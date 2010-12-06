<%@ Page Language="C#" Inherits="TestWeb.Default" %>
<%@ Register
Assembly="AjaxControlToolkit" 
Namespace="AjaxControlToolkit"
TagPrefix="ajaxToolkit" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html>
<head>
	<title>Default</title>
</head>
<body>
	<form id="form1" runat="server">
		<asp:Button id="button1" runat="server" Text="Click me!" OnClick="button1Clicked" />
		<asp:HiddenField id="hidden1" runat="server" Value="0"/>
		<asp:Calendar id="calendar1" runat="server"/>
		<asp:TextBox id="textBox1" runat="server"/>
	</form>
</body>
</html>