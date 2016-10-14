<%@ page import="com.makingdevs.QuestionaryPerInstanceStatus" %>
<div>
    <h4>Tus Cuestionarios</h4>
    <table class="table table-bordered table-condensed">
      <thead>
        <tr>
          <th>Titulo</th>
          <th>Estatus</th>
          <th>Acción</th>
        </tr>
      </thead>
      <tbody>
        <g:each in="${questionaryPerInstanceLinks}" var="questionaryLink">
          <tr>
            <td>${questionaryLink.questionaryPerInstance.questionary.title}</td>
            <td>
              <g:if test="${questionaryLink?.questionaryPerInstance?.questionaryPerInstanceStatus==QuestionaryPerInstanceStatus.CONTESTADO}">
                <span class="label label-success">Contestado</span>
              </g:if>
              <g:else>
                <span class="label label-info">Sin contestar</span>
              </g:else>
            </td>
            <td>
              <g:if test="${questionaryLink?.questionaryPerInstance?.questionaryPerInstanceStatus==QuestionaryPerInstanceStatus.CONTESTADO}">
                <g:link class="btn-small btn-primary" controller="evaluate" action="evaluateQuestionary" params="[questionaryPerInstance:questionaryLink?.questionaryPerInstance?.id,questionaryPerInstanceLink:questionaryLink?.id,url:request.forwardURI]"><i class='icon-search icon-white'></i> Ver
                </g:link>
              </g:if>
              <g:elseif test="${questionaryLink?.questionaryPerInstance?.questionaryPerInstanceStatus==QuestionaryPerInstanceStatus.SIN_CONTESTAR}">
                <g:link class="btn-small btn-primary" controller="questionary" action="answerQuestionary" params="[id:questionaryLink.questionaryPerInstance.id,idQL:questionaryLink.id,url:request.forwardURI]"><i class='icon-pencil icon-white'></i> Contestar
                </g:link>
              </g:elseif>
            </td>
          </tr>
        </g:each>
      </tbody>
    </table>
</div>
