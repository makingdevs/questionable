<!DOCTYPE html>
<html>
<head>
  <title>Add Questions</title>
</head>
<body>
  <h1>Agrege preguntas para ${questionary.title}</h1>
  <g:form name="addQuestionForm" action="addQuestion">
    <ul>
      <g:each in="${questions?}" var="preguntas" status="i">
        <li>${preguntas.description}</li>
      </g:each> 
    </ul>
    <input type="hidden" value="${questionary.id}" name="questionary" />
    <input type="submit" value="Enviar" />
  </g:form>
</body>
</html>