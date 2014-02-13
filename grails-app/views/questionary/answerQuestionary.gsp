<%@ page import="com.makingdevs.QuestionType"%>
<!DOCTYPE html>
<html>
<head>
  <meta name="layout" content="bootstrap"/>
  <r:require module="validation"/>

  <title>Answer Questionary</title>
</head>
<body>
  <div class="container">
    <h1>${questionaryPerInstance.questionary.title}</h1>
    <h4>${questionaryPerInstance.questionary.description} - Responde a las siguientes preguntas</h4>
    <hr>
    <div class="row-fluid">
      <g:form name="questionaryForm" id="questionaryForm" url="[action:'evaluateQuestionary',controller:'evaluate']">
        <g:each in="${questionaryPerInstance.questionary.questions.sort()}" var="question" status="i">
          <g:if test="${question.questionType==QuestionType.OPEN}">
            <g:render template="/evaluate/open" model="[question:question,index:i]"/>
          </g:if>
          <g:elseif test="${question.questionType==QuestionType.MULTIPLE_CHOICE}">
            <g:render template="/evaluate/multiple_choice" model="[question:question,index:i]"/>
          </g:elseif>
          <g:elseif test="${question.questionType==QuestionType.TRUE_FALSE}">
            <g:render template="/evaluate/true_false" model="[question:question,index:i]"/>
          </g:elseif>
          <g:elseif test="${question.questionType==QuestionType.MULTIPLE_RESPONSE}">
            <g:render template="/evaluate/multiple_response" model="[question:question,index:i]"/>
          </g:elseif>
          <hr>
        </g:each>
        <g:hiddenField name="numPreguntas" value="${numPreguntas}" />
        <g:hiddenField name="questionaryPerInstance" value="${questionaryPerInstance.id}" />
        <g:hiddenField name="questionaryPerInstanceLink" value="${questionaryPerInstanceLink}" />
        <div class="offset3 span6 offset3">
          <input class="btn btn-large btn-block btn-primary" type="submit" value="Evaluar Cuestionario" />
        </div>
      </g:form>
    </div>
  </div>
</body>
</html>