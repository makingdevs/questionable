<!DOCTYPE html>
<html>
<head>
  <meta name="layout" content="bootstrap"/>
  <title>Create questionary</title>
</head>
<body>
  <div class="container">
    <h1>Formule un cuestionario</h1>
    <div class="row-fluid">
      <div class="span6">
        <g:form name="questionaryForm" action="create">
          <label>Titulo:</label>
          <textarea class="field span12" rows="2" name="title"></textarea>
          <label>Descripción:</label>
          <textarea class="field span12" rows="4" name="description"></textarea>
          <label>CodeName:</label>
          <textarea class="field span12" rows="1" name="codeName"></textarea>
          <br>
            <input class="btn btn-primary" type="submit" value="Crear" />
        </g:form>
      </div>
    </div>
  </div>
</body>
</html>
