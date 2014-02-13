<strong>${question.description}</strong>
<g:each in="${question.answers.sort()}" var="respuesta" status="a"> 
  <label class="radio">
    ${respuesta.description}
    <input type="radio" name="question[${index}].description" value="${respuesta.id}" required/>
  </label>
</g:each>
<g:hiddenField name="question[${index}].id" value="${question.id}" />