<%@ page import="com.makingdevs.Questionable" %>
<ul>
  <g:each in="${listQuestionaryAvailable}" var="questionary">
    <li>
      ${questionary.codeName}
      <g:link class="btn-small btn-primary" controller="questionaryPerInstance" 
      action="agregar" params="[questionaryId:questionary.id, instanceId : instance.id, clazz : instance.class.name]">
        <i class='icon-plus-sign icon-white'></i>Agregar
      </g:link> 
      <g:link class="btn-small btn-primary" controller="questionaryPerInstance" action="agregar">
        <i class='icon-search icon-white'></i>Ver
      </g:link>
    </li>
  </g:each>
</ul>