<!DOCTYPE HTML>
<html lang="en">
<head>
  <meta name="layout" content="bootstrap"/>
  <r:require modules="validation"/>
  <title>Search questions</title>
</head>
<body>  
  <div class="container">
    <h2>Buscar preguntas</h2>
    <g:form name="searchForm" action="">
      <div class="row">
        <div class="span3">
          <input type="text" placeholder="Escribe una etiqueta" />
        </div>
        <button type="submit" class="btn btn-primary">
          <i class="icon-search"></i> Buscar
        </button>
      </div>
    </g:form>    
  </div>  
</body>
</html>