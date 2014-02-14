<!DOCTYPE html>
<html>
<head>
  <meta name="layout" content="bootstrap"/>
  <title>List Questionary</title>
</head>
<body>
  <div class="container">
    <h1>Cuestionarios</h1>
    <div class="row-fluid">
      <div class="span6">
        <table class="table table-striped">
          <thead>
            <tr>
              <th>Titulo</th>
              <th>Descripción</th>
              <th>N. preguntas</th>
              <th>CodeName</th>
            </tr>
          </thead>
          <tbody>
            <g:each in="${questionary?}" var="questionarios" status="i">
            <tr>
              <td>
                <g:link controller="questionary" action="showQuestionary" id="${questionarios.id}">${questionarios.title}</g:link>
              </td>
              <td>${questionarios.description}</td>
              <td>${questionarios.questions.size()}</td>
              <td>${questionarios.codeName}</td>
            </tr>
          </g:each> 
          </tbody>
        </table>
      </div>
    </div>
  </div>
</body>
</html>