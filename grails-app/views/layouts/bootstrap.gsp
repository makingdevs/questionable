<html>
<head>
  <title>Questionable</title>
  <asset:stylesheet src="${session.questionableTheme ?: 'questionable'}"/>
  <g:layoutHead/>
</head>
<body>
  <g:layoutBody/>
  <asset:javascript src="${session.questionableTheme ?: 'questionable'}"/>
  <asset:javascript src="evaluation"/>
</body>
</html>
