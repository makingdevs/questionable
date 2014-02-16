<%@ page import="com.makingdevs.QuestionType"%>
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
      <div class="span12">
        <ol>
          <g:each in="${listaDeEvaluaciones}" var="evaluacion" status="a">
            <g:if test="${evaluacion.rating==1.0}">
              <li><label class="text-success"><strong>${evaluacion.pregunta.description}</strong></label></li>
              <label class="text-success"><strong>${evaluacion.rating} puntos</strong></label>
            </g:if>
            <g:elseif test="${evaluacion.rating==0.0}">
              <li><label class="text-error"><strong>${evaluacion.pregunta.description}</strong></label></li>
              <label class="text-error"><strong>${evaluacion.rating} puntos</strong></label>
            </g:elseif>
            <g:else>
              <li><label class="text-warning"><strong>${evaluacion.pregunta.description}</strong></label></li>
              <label class="text-warning"><strong>${evaluacion.rating} puntos</strong></label>
            </g:else>
            <strong>Tu respuesta</strong>
            <g:if test="${evaluacion.answerUser.question.questionType==QuestionType.OPEN}">
              ${evaluacion.answerUser.openAnswerPerUsers.first().userAnswer}
            </g:if>
            <g:else>
              <g:each in="${evaluacion.answerUser.answerPerUsers.answer}" var="respuestas" status="b">
                ${respuestas.description}
              </g:each>
            </g:else>
          </g:each>
        </ol>
        <h3>Puntuacion Global: ${ratingTotal} puntos de un total ${listaDeEvaluaciones.size()}.00 puntos</h3>
      </div>
      <div class="offset9 span3">
        <a class="btn btn-success" href="${url}"><i class="icon-arrow-left icon-white"></i> Regresar</a>
      </div>
    </div>
  </div>  
</body>
</html>