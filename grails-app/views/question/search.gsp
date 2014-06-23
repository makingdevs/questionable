<%@ page import="com.makingdevs.QuestionType"%>
<!DOCTYPE html>
<html>
<head>
  <meta name="layout" content="bootstrap"/>
  <r:require modules="validation"/>
  <title>Search questions</title>
</head>
<body>
  <div class="container">
    <h2>Buscar preguntas</h2>
    <div class="row-fluid"> 
      <div class="span12"> 
      <g:form name="searchForm" action="">
        <div class="span6">
          <input type="text" name=""/>
          <input class="btn btn-primary" type="submit" value="Buscar" />
        </div>

      </g:form>    
      </div>
    </div>
  </div>
</body>
</html>