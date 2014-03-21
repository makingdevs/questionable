<ul>
  <g:each in="${cuestionarios}" var="cuestionario" status="i">
    <li>${cuestionario} ${ratingUser.getAt(i)} puntos de un total de ${ratingTotal.getAt(i)}.00</li>
  </g:each>
</ul>