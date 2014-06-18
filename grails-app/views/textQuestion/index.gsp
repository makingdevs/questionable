<!DOCTYPE html>
<html>
<head>
  <meta name="layout" content="bootstrap"/>
  <title>Create questionary</title>
</head>
<body>
  <div class="container">
    <h1>Escriba las preguntas y respuestas</h1>
    <div class="row-fluid">
      <div class="span6">
        <g:form name="questionsForm" action="create">          
          <label>Preguntas y respuestas:</label>
          <textarea class="field span12" rows="10" name="questionsAndAnswers"></textarea>
          <br>
          <input class="btn btn-primary" type="submit" value="Crear" />
        </g:form>
      </div>
    </div>
  </div>
</body>
</html>