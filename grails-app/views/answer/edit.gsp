<!DOCTYPE html>
<html>
<head>
	<title></title>
	<meta name="layout" content="bootstrap"/>
	<r:require modules="syntaxhighlighter"/>
</head>
<body>
	<div class="container">
    <div  class="row-fluid">
      <div class="span12">
      	<h3>Descripción de la respuesta</h3>
      	<g:form class="form" name="questionForm" action="update">
          <div class="control-grou">
            <g:textArea name="description" class="span6" value="${answer.description}" rows="10" maxlength="1000" required=""></g:textArea>
            <input type="hidden" value="${question}" name="question" />
            <input type="hidden" value="${answer.id}" name="id" />
          </div>
          <div class="control">
            <button type="submit" class="btn btn-primary"><i class="icon-ok icon-white"></i> Aceptar</button>
            <g:link class="btn btn-info" action="detail" controller="question" id="${answer.id}"><i class="icon-remove icon-white"></i> Cancelar</g:link>
          </div>
        </g:form>
      </div>
    </div>
	</div>
</body>
</html>