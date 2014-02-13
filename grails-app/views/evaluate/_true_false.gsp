<strong>${question.description}</strong>
<g:each in="${question.answers.sort()}" var="respuesta" status="a"> 
  <label for="question[${index}].description">
  <input type="radio" name="question[${index}].description" value="${respuesta.id}" required/>
  <g:if test="${respuesta.description=='true'}">
    Verdadero
  </g:if>
  <g:else>
    Falso
  </g:else>
  </label>
</g:each>
<g:hiddenField name="question[${index}].id" value="${question.id}" />