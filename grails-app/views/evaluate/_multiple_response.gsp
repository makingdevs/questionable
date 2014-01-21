<label>${question.description}</label>
<g:each in="${question.answers}" var="respuesta" status="b"> 
<label>${respuesta.description}</label>
<g:checkBox name="description" value="${respuesta.id}" checked="false"/>
</g:each>
<g:hiddenField name="id" value="${question.id}" />