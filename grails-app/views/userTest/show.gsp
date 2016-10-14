<%@ page import="com.makingdevs.UserTest" %>
<%@ page import="com.makingdevs.Questionable" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="bootstrap"/>
		<g:set var="entityName" value="${message(code: 'userTest.label', default: 'UserTest')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-userTest" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="index"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="show-userTest" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list userTest">

				<g:if test="${userTest?.atributo}">
				<li class="fieldcontain">
					<span id="atributo-label" class="property-label"><g:message code="userTest.atributo.label" default="Atributo" /></span>
						<span class="property-value" aria-labelledby="atributo-label"><g:fieldValue bean="${userTest}" field="atributo"/></span>
				</li>
				</g:if>

				<strong>Agrega un cuestionario</strong>
				<g:showQuestionaryForThisInstance instance="${userTest}"/>


			</ol>
			<g:form name="otro" url="[resource:userTest, action:'delete']" method="DELETE">
				<fieldset class="buttons">
					<g:link class="edit" action="edit" resource="${userTest}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
