<%@ page import="com.makingdevs.QuestionType"%>
<!DOCTYPE html>
<html>
<head>
  <meta name="layout" content="bootstrap"/>
  <r:require modules="validation"/>
	<title>Create question</title>
</head>
<body>
  <div class="container">
    <h1>Captura pregunta</h1>
    <div class="row-fluid">
      <g:form name="questionForm" action="save">
        <div class="span6">
          <label>Description</label>
          <g:textArea name="description" class="span12" rows="10" maxlength="1000" required=""></g:textArea>          
          <label>Tags</label>
          <g:textArea name="tags" class="span12" rows="2" maxlength="1000"></g:textArea>
        </div>
        <div class="span6">
          <label>QuestionType</label>
          <g:select name="questionType" from="${QuestionType.values()}"/>
        </div>
        <div class="span12">
          <input class="btn btn-primary" type="submit" value="Enviar" />
        </div>
      </g:form>
    </div>
  </div>
</body>
</html>