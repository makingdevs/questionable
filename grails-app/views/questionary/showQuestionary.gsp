<!DOCTYPE html>
<html>
<head>
  <meta name="layout" content="bootstrap"/>
  <r:require module="syntaxhighlighter"/>
  <script src='http://alexgorbatchev.com/pub/sh/current/scripts/shCore.js' type='text/javascript'></script>
  <title>Add Questions</title>
</head>
<body>
  <div class="container">
    <h1>Agrege preguntas para "${questionary.title}"</h1>
    <div class="row-fluid">
      <div class="span6">
        <g:form name="myForm" url="[controller:'questionary', action: 'buscar']">
          <ul>
            <g:each in="${questions?}" var="preguntas" status="i">
              <li>${preguntas.description}: <input name="id" value="${preguntas.id}" type="checkbox" /></li>
            </g:each> 
          </ul>
          <input type="hidden" value="${questionary.id}" name="questionary" />
          <input class="btn btn-primary" type="submit" value="Agregar">
        </g:form>
        <g:link class="btn btn-info" action="list">Todos los Cuestionarios</g:link>
        <g:link class="btn btn-info" action="create">Crear Cuestionario</g:link>
        <g:render template="listQuestions" model="[questionary:questionary]"/>
    </div>
  </div>
</body>
</html>