<%@ page import="com.makingdevs.QuestionType"%>
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
          <g:if test="${question.questionType==QuestionType.MULTIPLE_CHOICE || question.questionType==QuestionType.MULTIPLE_RESPONSE}">
            <dd>
              <ul>
              <g:each in="${question?.answers}" var="respuestas" status="i">
                <li>
                  <g:link action="edit" controller="answer" id="${respuestas.id}" params="[question:question.id]">${respuestas.description} -- ${respuestas.solution}</g:link>
                </li>
              </g:each> 
              </ul>
            </dd>
          </g:if>
          <g:else>
            <dd>
              <ul>
              <g:each in="${question?.answers}" var="respuestas" status="i">
                <li>${respuestas.description} -- ${respuestas.solution}</li>
              </g:each> 
              </ul>
            </dd>
          </g:else>
        </dl>
        <g:link class="btn btn-info" action="list" controller="question"><i class="icon-arrow-left icon-white"></i> Regresar</g:link>
        <g:link class="btn btn-info" action="edit" controller="question" id="${question.id}"><i class=" icon-edit icon-white"></i> Editar</g:link>
        <g:link class="btn btn-info" action="show" controller="question" id="${question.id}"><i class=" icon-plus-sign icon-white"></i> Agregar respuestas</g:link>
      </div>
    </div>
  </div>
</body>
</html>