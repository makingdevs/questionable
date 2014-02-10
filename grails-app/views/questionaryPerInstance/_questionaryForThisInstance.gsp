<ul>
  <g:each in="${listQuestioanryAvailable}" var="questionary">
    <li>
      <g:link controller="questionary" action="answerQuestionary" id="${questionary.id}">${questionary.codeName}
        <a class='btn-small btn-primary' href=''><i class='icon-plus-sign'></i>Agregar</a>
        <a class='btn-small btn-primary' href=''><i class='icon-search'></i>Ver</a>
      </g:link>
  </li>
  </g:each>
</ul>