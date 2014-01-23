<!DOCTYPE html>
<html>
<head>
  <meta name="layout" content="bootstrap"/>
  <title>Evaluación</title>
</head>
<body>
  <div class="container">
    <h1>Tu evaluacion del cuestionario por pregunta</h1>
    <h3>A las siguientes preguntas obtuviste</h3>
    <div class="row-fluid">
      <div class="span8">
        <g:each in="${listaDeEvaluaciones}" var="evaluacion" status="a">
        <g:if test="${evaluacion.rating==1.0}">
          <label class="text-success"><strong>${evaluacion.pregunta.description}</strong></label>
          <label><strong>${evaluacion.rating} puntos</strong></label>
        </g:if>
        <g:elseif test="${evaluacion.rating==0.0}">
          <label class="text-error"><strong>${evaluacion.pregunta.description}</strong></label>
          <label><strong>${evaluacion.rating} puntos</strong></label>
        </g:elseif>
        <g:else>
          <label class="text-warning"><strong>${evaluacion.pregunta.description}</strong></label>
          <label><strong>${evaluacion.rating} puntos</strong></label>
        </g:else>
        </g:each>
        <h3>Puntuacion Global: ${listaDeEvaluaciones.evaluacion.rating.sum()}</h3>
      </div>
    </div>
  </div>  
</body>
</html>