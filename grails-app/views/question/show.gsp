<%@ page import="com.makingdevs.QuestionType"%>
<!DOCTYPE html>
<html>
<head>
  <title>Show Question </title>
</head>
<body>
  <h1>${question.description}</h1>
  <h2>${question.questionType}</h2>
  <g:if test="${question.questionType == QuestionType.MULTIPLE_CHOICE || question.questionType == QuestionType.MULTIPLE_RESPONSE}">
    <g:link controller="answer" action="create">Agregar respuestas</g:link>
  </g:if>
</body>
</html>
