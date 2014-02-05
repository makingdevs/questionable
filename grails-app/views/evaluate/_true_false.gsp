<strong>${question.description}</strong>
<g:each in="${question.answers.sort()}" var="respuesta" status="a"> 
  <label class="radio">
  <g:radio name="question[${index}].description" value="${respuesta.id}"/>
  <g:if test="${respuesta.description=='true'}">
    Verdadero
  </g:if>
  <g:else>
    Falso
  </g:else>
  </label>
</g:each>
<g:hiddenField name="question[${index}].id" value="${question.id}" />