<!DOCTYPE html>
<html>
<head>
  <meta name="layout" content="bootstrap"/>
  <title>Detail Question</title>
</head>
<body>
  <div class="container">
    <h1>${question.description}</h1>
    <div  class="row-fluid">
      <div class="span6">
        <dl>
          <dt>Pregunta</dt>
          <dd>${question.description}</dd>
          <dt>Tipo de Respuesta</dt>
          <dd>${question.questionType}</dd>
          <dt>Respuestas</dt>
          <dd>
            <ul>
            <g:each in="${question?.answers}" var="respuestas" status="i">
              <li>${respuestas.description} -- ${respuestas.solution}</li>
            </g:each> 
            </ul>
          </dd>
        </dl>
      </div>
    </div>
  </div>
</body>
</html>