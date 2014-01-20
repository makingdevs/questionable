<!DOCTYPE html>
<html>
<head>
  <title></title>
</head>
<body>
  <h1>Pregunta Falso Verdadero</h1>
  <g:form name="questionForm" action="evaluate">
    <label>${question.first().description}</label><br>
    <label>Falso</label>
    <g:radio name="description" value="false"/>
    <label>Cierto</label>
    <g:radio name="description" value="true"/>
    <g:hiddenField name="id" value="${question.first().id}" />
    <input type="submit" value="Enviar" />
  </g:form>
</body>
</hml}>