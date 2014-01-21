<%@ page import="com.makingdevs.QuestionType"%>
<!DOCTYPE html>
<html>
<head>
  <meta name="layout" content="bootstrap"/>
  <title>Answer Questionary</title>
</head>
<body>
  <div class="container">
    <h1>Responde el cuestionario</h1>
    <div class="row">
      <g:form name="questionaryForm" action="">
        <g:each in="${questionary.first().questions}" var="question" status="i">
          <g:if test="${question.questionType==QuestionType.OPEN}">
            <g:render template="/evaluate/open" collection="${question}" var="question"/>
          </g:if>
          <g:elseif test="${question.questionType==QuestionType.MULTIPLE_CHOICE}">
            <g:render template="/evaluate/multiple_choice" collection="${question}" var="question"/>
          </g:elseif>
          <g:elseif test="${question.questionType==QuestionType.TRUE_FALSE}">
            <g:render template="/evaluate/true_false" collection="${question}" var="question"/>
          </g:elseif>
          <g:else>
            ${pregunta}
          </g:else>
        </g:each>
      </g:form>
    </div>
  </div>
</body>
</html>