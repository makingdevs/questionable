<!DOCTYPE html>
<html>
<head>
  <meta name="layout" content="bootstrap"/>
  <title>Evaluación</title>
</head>
<body>
  <div class="container">
    <h1>Tu evaluacion del cuestionario</h1>
    <div class="row-fluid">
      <g:each in="${listaDeEvaluaciones}" var="evaluacion" status="a">
        <label><strong>${evaluacion.pregunta.description}</strong></label>
        <label><strong>${evaluacion.rating}</strong></label>
      </g:each>
    </div>
  </div>  
</body>
</html>