<label>${question.description}</label>
<g:each in="${question.answers}" var="respuesta" status="a"> 
  <label>${respuesta.description}</label>
  <g:radio name="description" value="${respuesta.id}"/>
</g:each>
<g:hiddenField name="id" value="${question.id}" />