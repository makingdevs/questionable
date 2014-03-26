<!DOCTYPE html>
<html>
<head>
  <meta name="layout" content="bootstrap"/>
  <r:require modules="syntaxhighlighter"/>
  <title>Detail Question</title>
</head>
<body>
  <div class="container">
    <div  class="row-fluid">
      <div class="span12">
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