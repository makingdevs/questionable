<%@ page import="com.makingdevs.Answer"%>
<!DOCTYPE html>
<html>
<head>
  <title>Create answer</title>
</head>
<body>
  <h1>Captura respuesta para ${question.description}</h1>
  <g:form name="answerForm" action="agregar">
    <label>Descripción:</label>
    <g:textField name="description"/>
    <label>¿Es la solución?:</label>
    <select name="solution">
      <option value="false">False</option>
      <option value="true">True</option>
    </select>
    <input type="hidden" value="${question.id}" name="question" />
    <input type="submit" value="Enviar" />
  </g:form>
</body>
</html>
