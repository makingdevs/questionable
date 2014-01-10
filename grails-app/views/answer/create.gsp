
<!DOCTYPE html>
<html>
<head>
  <title>Create answer</title>
</head>
<body>
  <h1>Captura respuesta para ${question.description}</h1>
  <g:form name="answerForm" action="agregar">
    <label>Description</label>
    <g:textField name="description"/>
    <input type="hidden" value="${question.id}" name="question" />
    <input type="submit" value="Enviar" />
  </g:form>
</body>
</html>
