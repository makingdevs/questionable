<dl>
  <dt>
    Este cuestionario tiene las siguientes preguntas : 
  </dt>
  <g:each in="${questionary.questions}" var="questionario">
    <dd>
      ${questionario.description} 
    </dd>
  </g:each>
</dl>