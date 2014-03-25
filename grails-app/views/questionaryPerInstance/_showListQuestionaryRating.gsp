<g:if test="${cuestionarios.size()==0}">
  <h6>Aun no hay evaluaciones realizadas</h6>
</g:if>
<g:else>
  <ul>
    <g:each in="${cuestionarios}" var="cuestionario" status="i">
      <li>${cuestionario} ${ratingUser.getAt(i)} puntos de un total de ${ratingTotal.getAt(i)}.00</li>
    </g:each>
  </ul>
</g:else>
