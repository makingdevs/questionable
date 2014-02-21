<strong>${question.description}</strong>
<g:each in="${question.answers.sort()}" var="respuesta" status="b"> 
  <label class="checkbox">
    ${respuesta?.description?.trim()}
    <input type="checkbox" name="question[${index}].description" value="${respuesta.id}"/>
  </label>
</g:each>
<g:hiddenField name="question[${index}].id" value="${question.id}" />