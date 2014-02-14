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
 		<g:each in="${questionary.questions}" var="questionario">
    	<dd>
      	${questionario.description} 
    	</dd>
  	</g:each>
	</g:else>
</dl>