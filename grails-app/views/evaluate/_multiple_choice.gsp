<strong>${question.description}</strong>
<g:each in="${question.answers}" var="respuesta" status="a"> 
  <label class="radio">
    ${respuesta.description}
    <g:radio name="question[${index}].description" value="${respuesta.id}"/>
  </label>
</g:each>
<g:hiddenField name="question[${index}].id" value="${question.id}" />