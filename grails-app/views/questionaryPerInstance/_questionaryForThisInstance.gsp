<%@ page import="com.makingdevs.Questionable" %>
<%@ page import="com.makingdevs.QuestionaryPerInstanceStatus" %>
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
        <g:if test="${questionaryLink?.questionaryPerInstance?.questionaryPerInstanceStatus==QuestionaryPerInstanceStatus.CONTESTADO}">
          <g:link class="btn-small btn-primary" controller="evaluate" action="evaluateQuestionary" params="[questionaryPerInstance:questionaryLink?.questionaryPerInstance?.id,questionaryPerInstanceLink:questionaryLink?.id,url:request.forwardURI]">
          <i class='icon-search icon-white'></i> Ver
          </g:link>
        </g:if>
        <g:else>
          <g:link class="btn-small btn-primary" controller="questionary" action="answerQuestionary" params="[id:questionaryLink.questionaryPerInstance.id,idQL:questionaryLink.id,url:request.forwardURI]">
          <i class='icon-pencil icon-white'></i> Contestar
          </g:link>
        </g:else>
      </g:if>
      <g:else>
        <g:link class="btn-small btn-primary" controller="questionaryPerInstance" 
        action="agregar" params="[questionaryId:questionary.id, instanceId : instance.id, clazz : instance.class.name,url:request.forwardURI]">      
        <i class='icon-plus-sign icon-white'></i> Agregar
        </g:link>
      </g:else>
      <g:if test="${questionaryLink?.questionaryPerInstance?.questionaryPerInstanceStatus!=null}">
        <g:if test="${questionaryLink?.questionaryPerInstance?.questionaryPerInstanceStatus==QuestionaryPerInstanceStatus.CONTESTADO}">
          <span class="label label-success">${questionaryLink?.questionaryPerInstance?.questionaryPerInstanceStatus}</span>
        </g:if>
        <g:else>
          <span class="label label-info">${questionaryLink?.questionaryPerInstance?.questionaryPerInstanceStatus}</span>
        </g:else>
      </g:if>
      <g:else>
        <span class="label">SIN_AGREGAR</span>
      </g:else>
    </li>
    <g:set var="questionaryLink" value="${null}" />
  </g:each>
</ul>