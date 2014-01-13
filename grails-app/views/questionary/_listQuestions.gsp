<dl>
  <dt>
    Preguntas : 
  </dt>
  <g:each in="${questionary.questions}" var="questionario">
    <dd>
      ${questionario.description} 
    </dd>
  </g:each>
</dl>