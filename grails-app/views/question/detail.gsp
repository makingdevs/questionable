<!DOCTYPE html>
<html>
<head>
  <title>Detail Question</title>
</head>
<body>
  <h1>${question.description}</h1>
  <dl>
  <dt>Pregunta</dt>
  <dd>${question.description}</dd>
  <dt>Tipo de Respuesta</dt>
  <dd>${question.questionType}</dd>
  <dt>Respuestas</dt>
  <dd>
    <ul>
    <g:each in="${question?.answers}" var="respuestas" status="i">
      <li>${respuestas.description}</li>
    </g:each> 
    </ul>
  </dd>
  </dl>
</body>
</html>