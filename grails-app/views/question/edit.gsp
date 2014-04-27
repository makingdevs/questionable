<!DOCTYPE html>
<html>
<head>
  <meta name="layout" content="bootstrap"/>
  <r:require modules="syntaxhighlighter"/>
  <title>Detail Question</title>
</head>
<body>
  <div class="container">
    <div  class="row">
      <div class="span6">
        <h4>Pregunta</h4>
        <g:form class="form" name="questionForm" action="update">
          <div class="control-grou">
            <g:textArea name="description" class="span6" value="${question.description}" rows="10" maxlength="1000" required=""></g:textArea>
            <input type="hidden" value="${question.id}" name="id" />
          </div>
          <div class="control">
            <button type="submit" class="btn btn-primary"><i class="icon-ok icon-white"></i> Aceptar</button>
            <g:link class="btn btn-info" action="detail" controller="question" id="${question.id}"><i class="icon-remove icon-white"></i> Cancelar</g:link>
          </div>
        </g:form>
      </div>
      <div class="span6 well">
        <dl>
          <dt>Tipo de Respuesta</dt>
          <dd>${question.questionType}</dd>
          <dt>Respuestas</dt>
          <dd>
            <g:if test="${question.answers.size()==0}">
             <p>Esta pregunta no tiene respuestas agregadas</p>
            </g:if>
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