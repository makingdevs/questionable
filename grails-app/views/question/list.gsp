<!DOCTYPE html>
<html>
<head>
  <title>Show Question </title>
</head>
<body>
  <h1>Lista de preguntas</h1>
  <g:if test="${listQuestion.size() > 0}">
    <ul>
    <g:each in="${listQuestion}" var="listaDePreguntas" status="i">
      <li>${listaDePreguntas.description}</li>
    </g:each> 
    </ul>
  </g:if>
  <g:else>
    <p>No hay preguntas</p>
  </g:else>
</body>
</html>
