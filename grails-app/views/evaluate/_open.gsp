<label><strong>${question.description}</strong></label>
<g:textArea class="span6" rows="3" name="question[${index}].description" style="overflow:auto; resize:none"></g:textArea>
<g:hiddenField name="question[${index}].id" value="${question.id}" />
