<strong>${question.description}</strong>
<g:each in="${question.answers}" var="respuesta" status="a"> 
  <label class="radio">
    ${respuesta.description}
    <g:radio name="description" value="${respuesta.id}"/>
  </label>
</g:each>
<g:hiddenField name="id" value="${question.id}" />