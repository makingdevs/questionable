<%@ page import="com.makingdevs.Questionable" %>
<ul>
  <g:each in="${listQuestionaryAvailable}" var="questionary">
    <li>
      ${questionary.codeName}
      <g:each in="${questionaryLinks}" var="a">
        <g:if test="${a?.questionaryPerInstance?.questionary?.id==questionary.id}">
        <g:set var="questionaryLink" value="${a}" />
        </g:if>
      </g:each>
      <g:if test="${questionaryLink?.questionaryPerInstance?.questionary?.id==questionary.id}">
        <g:link class="btn-small btn-primary" controller="questionary" action="answerQuestionary" params="[id:questionaryLink.questionaryPerInstance.id]">
        <i class='icon-search icon-white'></i> Contestar
        </g:link>
      </g:if>
      <g:else>
        <g:link class="btn-small btn-primary" controller="questionaryPerInstance" 
        action="agregar" params="[questionaryId:questionary.id, instanceId : instance.id, clazz : instance.class.name]">
        <i class='icon-plus-sign icon-white'></i> Agregar
        </g:link>
      </g:else>
    </li>
  </g:each>
</ul>