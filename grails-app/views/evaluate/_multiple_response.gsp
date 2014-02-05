<strong>${question.description}</strong>
<g:each in="${question.answers.sort()}" var="respuesta" status="b"> 
  <label class="checkbox">
    <g:checkBox name="question[${index}].description" value="${respuesta.id}" checked="false"/>
    ${respuesta?.description?.trim()}
  </label>
</g:each>
<g:hiddenField name="question[${index}].id" value="${question.id}" />