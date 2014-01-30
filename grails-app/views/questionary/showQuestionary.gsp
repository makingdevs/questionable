<!DOCTYPE html>
<html>
<head>
  <meta name="layout" content="bootstrap"/>
  <title>Add Questions</title>
</head>
<body>
  <div class="container">
    <h1>Agrege preguntas para "${questionary.title}"</h1>
    <div class="row-fluid">
      <div class="span6">
        <g:formRemote name="myForm" update="updateMe" url="[controller:'questionary', action: 'buscar']">
          <ul>
            <g:each in="${questions?}" var="preguntas" status="i">
              <li>${preguntas.description}: <input name="id" value="${preguntas.id}" type="checkbox" /></li>
            </g:each> 
          </ul>
          <input type="hidden" value="${questionary.id}" name="questionary" />
          <input class="btn btn-primary" type="submit" value="Agregar">
        </g:formRemote>
        <div id="updateMe"></div>
          <g:render template="listQuestions"/>
        </div>
    </div>
  </div>
</body>
</html>