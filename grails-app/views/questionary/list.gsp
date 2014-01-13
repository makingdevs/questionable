<!DOCTYPE html>
<html>
<head>
  <title>List Questionary</title>
</head>
<body>
  <h1>Cuestionarios</h1>
  <ul>
    <g:each in="${questionary?}" var="questionarios" status="i">
      <li>${questionarios}</li>
    </g:each> 
  </ul>
</body>
</html>