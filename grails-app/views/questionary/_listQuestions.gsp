<dl>
	<g:if test="${questionary?.questions.size()==0}">
		<dt>
			Este cuestionario aun no tiene preguntas
		</dt>
	</g:if>
	<g:else>
		<dt>
	    Este cuestionario tiene las siguientes preguntas : 
  	</dt>
 		<g:each status="i" in="${questionary.questions}" var="questionario">
    	<dd>
      	<span class="badge badge-info">${i+1} </span>- ${questionario.description} 
    	</dd>
  	</g:each>
	</g:else>
</dl>