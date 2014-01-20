<!DOCTYPE html>
<html>
<head>
  <title></title>
</head>
<body>
  <h1>Pregunta Multiple opcion</h1>
  <g:form name="questionForm" action="evaluate">
    <label>${question.first().description}</label><br>
    <g:each in="${question.first().answers}" var="respuesta" status="a"> 
      <br>
      <label>${respuesta.description}</label>
      <g:radio name="description" value="${respuesta.id}"/>
    </g:each>
    <g:hiddenField name="id" value="${question.first().id}" />
    <br>
    <input type="submit" value="Enviar" />
  </g:form>
</body>
</html>