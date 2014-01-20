<!DOCTYPE html>
<html>
<head>
  <title></title>
</head>
<body>
  <h1>Pregunta Abierta</h1>
  <g:form name="questionForm" action="evaluate">
    <label>${question.first().description}</label>
    <g:textField name="description"/><br>
    <g:hiddenField name="id" value="${question.first().id}" />
    <input type="submit" value="Enviar" />
  </g:form>
</body>
</html>