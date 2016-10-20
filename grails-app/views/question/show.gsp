<%@ page import="com.makingdevs.QuestionType"%>
<!DOCTYPE html>
<html>
<head>
  <meta name="layout" content="bootstrap"/>
  <title>Show Question </title>
</head>
<body>
  <div class="container">
    <h4>${question.description.decodeHTML()}</h4>
    <h3>Tipo de Pregunta: ${question.questionType}</h3>
    <div class="row-fluid">
      <div class="span12">
        <g:if test="${question.answers.size() > 0}">
          <g:each in="${question.answers}" var="answer" status="i">
          <ul>
            <li>
              <p>
                <g:if test="${answer.solution}">
                  ${answer.description} - <span class="badge badge-success">The solution</span>
                </g:if>
                <g:else>
                  ${answer.description} - <span class="badge badge-important">Not the solution</span>
                </g:else>
              </p>
            </li>
          </ul>
          </g:each>
        </g:if>
        <g:if test="${question.questionType == QuestionType.MULTIPLE_CHOICE || question.questionType == QuestionType.MULTIPLE_RESPONSE}">
          <g:link class="btn btn-primary" controller="answer" action="create" id="${question.id}">Agregar respuestas</g:link>
        </g:if>
        <g:if test="${question.questionType == QuestionType.TRUE_FALSE && question.answers.size() < 2}">
          <g:link class="btn btn-primary" controller="answer" action="create" id="${question.id}">Agregar respuestas</g:link>
        </g:if>
        <g:link class="btn btn-primary"  action="index">Crear otra Pregunta</g:link>
      </div>
    </div>
  </div>
</body>
</html>
