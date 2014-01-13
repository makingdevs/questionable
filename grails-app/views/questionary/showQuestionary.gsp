<!DOCTYPE html>
<html>
<head>
  <title>Add Questions</title>
</head>
<body>
  <h1>Agrege preguntas para ${questionary.title}</h1>
<g:formRemote name="myForm" update="updateMe" url="[controller: 'questionary', action: 'buscar']">
  <ul>
    <g:each in="${questions?}" var="preguntas" status="i">
      <li>${preguntas.description}: <input name="id" value="${preguntas.id}" type="checkbox" /></li>
    </g:each> 
  </ul>
    <input type="hidden" value="${questionary.id}" name="questionary" />
    <input type="submit" value="Agregar">
</g:formRemote>
<div id="updateMe"></div>
<g:render template="listQuestions"/>
</body>
</html>