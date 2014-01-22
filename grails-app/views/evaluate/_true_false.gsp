<strong>${question.description}</strong>
<label class="radio">
  <g:radio name="question[${index}].description" value="false"/>
  Falso
</label>
<label class="radio">
  <g:radio name="question[${index}].description" value="true"/>
  Cierto
</label>
<g:hiddenField name="question[${index}].id" value="${question.id}" />