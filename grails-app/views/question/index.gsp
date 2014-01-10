<%@ page import="com.makingdevs.QuestionType"%>
<!DOCTYPE html>
<html>
<head>
	<title>Create question</title>
</head>
<body>
	<h1>Captura pregunta</h1>
	<g:form name="questionForm" action="create">
		<label>Description</label>
		<g:textField name="description"/>
		<label>QuestionType</label>
		<g:select name="questionType" from="${QuestionType.values()}"/>
		<input type="submit" value="Enviar" />
	</g:form>
</body>
</html>
