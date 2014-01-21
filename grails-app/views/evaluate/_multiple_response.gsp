<strong>${question.description}</strong>
<g:each in="${question.answers}" var="respuesta" status="b"> 
  <label class="checkbox">
    <g:checkBox name="description" value="${respuesta.id}" checked="false"/>
    ${respuesta?.description?.trim()}
  </label>
</g:each>
<g:hiddenField name="id" value="${question.id}" />