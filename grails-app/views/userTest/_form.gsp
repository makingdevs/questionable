<%@ page import="com.makingdevs.UserTest" %>



<div class="fieldcontain ${hasErrors(bean: userTest, field: 'atributo', 'error')} ">
	<label for="atributo">
		<g:message code="userTest.atributo.label" default="Atributo" />
		
	</label>
	<g:textField name="atributo" value="${userTest?.atributo}"/>
</div>

