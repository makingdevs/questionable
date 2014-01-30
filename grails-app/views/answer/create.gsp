<%@ page import="com.makingdevs.QuestionType"%>
<!DOCTYPE html>
<html>
<head>
  <title>Create answer</title>
  <meta name="layout" content="bootstrap"/>
</head>
<body>
  <div class="container">
    <h1>Captura respuesta para "${question.description}"</h1>
    <div class="row-fluid">
      <div class="span6">
        <g:if test="${question.questionType == QuestionType.TRUE_FALSE}">
          <g:form name="answer1Form" action="agregar">
            <label>¿Cual es la respuesta?:</label>
            <select name="description">
              <option value="false">False</option>
              <option value="true">True</option>
            </select>
            <input type="hidden" value="true" name="solution" />
            <input type="hidden" value="${question.id}" name="question" />
            <br>
            <input class="btn btn-primary" type="submit" value="Enviar" />
          </g:form>
        </g:if>
        <g:else>
          <g:form name="answerForm" action="agregar">
            <label>Descripción:</label>
            <textarea class="field span12" rows="4" name="description"></textarea>
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
