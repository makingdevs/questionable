<%@ page import="com.makingdevs.QuestionType"%>
<!DOCTYPE html>
<html>
<head>
  <title>Create answer</title>
</head>
<body>
  <h1>Captura respuesta para ${question.description}</h1>
  <g:if test="${question.questionType == QuestionType.TRUE_FALSE}">
    <g:form name="answer1Form" action="agregar">
      <label>¿Cual es la respuesta?:</label>
      <select name="description">
        <option value="false">False</option>
        <option value="true">True</option>
      </select>
      <input type="hidden" value="true" name="solution" />
      <input type="hidden" value="${question.id}" name="question" />
      <input type="submit" value="Enviar" />
    </g:form>
  </g:if>
  <g:else>
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
  </g:else>
</body>
</html>
