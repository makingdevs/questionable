<%@ page import="com.makingdevs.QuestionType"%>
<!DOCTYPE html>
<html>
<head>
  <title>Show Question </title>
</head>
<body>
  <h1>${question.description}</h1>
  <h2>${question.questionType}</h2>
  <g:if test="${question.answers.size() > 0}">
  <g:each in="${question.answers}" var="answers" status="i">
    <p>${answers.description}</p>
    </g:each>
  </g:if>
  <g:if test="${question.questionType == QuestionType.MULTIPLE_CHOICE || question.questionType == QuestionType.MULTIPLE_RESPONSE}">
    <g:link controller="answer" action="create" id="${question.id}">Agregar respuestas</g:link>
  </g:if>
  <g:link action="index">Crear otra Pregunta</g:link>
</body>
</html>
