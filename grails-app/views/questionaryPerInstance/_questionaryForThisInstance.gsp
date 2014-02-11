<%@ page import="com.makingdevs.Questionable" %>
<ul>
  <g:each in="${listQuestionaryAvailable}" var="questionary">
    <li>
      ${questionary.codeName}
      <g:if test="${lista?.questionaryPerInstance?.questionary?.id==questionary.id}">
        <g:link class="btn-small btn-primary" controller="questionary" action="answerQuestionary" params="[questionaryPerInstanceId:lista.questionaryPerInstance.id]">
        <i class='icon-search icon-white'></i> Ver
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