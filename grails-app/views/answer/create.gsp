<%@ page import="com.makingdevs.QuestionType"%>
<!DOCTYPE html>
<html>
<head>
  <title>Create answer</title>
  <meta name="layout" content="bootstrap"/>
  <r:require module="syntaxhighlighter"/>
</head>
<body>
  <div class="container">
    <h4>Captura respuesta para: ${question.description}</h4>
    <div class="row-fluid">
      <div class="span6">
        <g:if test="${question.questionType == QuestionType.TRUE_FALSE}">
          <g:form name="answer1Form" action="agregar">
            <label>¿Cual es la respuesta?:</label>
            <select name="description">
              <option value="false">False</option>
              <option value="true">True</option>
            </select>
            <label>¿Es la solución?:</label>
            <select name="solution">
              <option value="false">False</option>
              <option value="true">True</option>
            </select>
            <input type="hidden" value="${question.id}" name="question" />
            <br>
            <input class="btn btn-primary" type="submit" value="Enviar" />
          </g:form>
        </g:if>
        <g:else>
          <g:form name="answerForm" action="agregar">
            <label>Descripción:</label>
            <g:textArea name="description" class="span12" rows="10" maxlength="1000" required=""></g:textArea>
            <label>¿Es la solución?:</label>
            <select name="solution">
              <option value="false">False</option>
              <option value="true">True</option>
            </select>
            <input type="hidden" value="${question.id}" name="question" />
            <br>
            <input class="btn btn-primary" type="submit" value="Enviar" />
          </g:form>
        </g:else>
      </div>
    </div>
  </div>
</body>
</html>
