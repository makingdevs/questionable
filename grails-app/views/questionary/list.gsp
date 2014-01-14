<!DOCTYPE html>
<html>
<head>
  <title>List Questionary</title>
</head>
<body>
  <h1>Cuestionarios</h1>
  <table>
    <thead>
      <tr>
        <th>Titulo</th>
        <th>Descripción</th>
        <th>N. preguntas</th>
      </tr>
    </thead>
    <tbody>
      <g:each in="${questionary?}" var="questionarios" status="i">
      <tr>
        <td>${questionarios.title}</td>
        <td>${questionarios.description}</td>
        <td>${questionarios.questions.size()}</td>
      </tr>
    </g:each> 
    </tbody>
  </table>
</body>
</html>