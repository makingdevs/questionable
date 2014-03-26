modules = {

  validation{
    resource url:'js/jquery.validate.min.js'
    resource url:'js/questionary/validation.js'
  }
  
  questionable{
  	resource url:'css/bootstrap-responsive.min.css'
  	resource url:'css/bootstrap.min.css'
  	resource url:'images/glyphicons-halflings-white.png'
  	resource url:'images/glyphicons-halflings.png'
  	resource url:'js/bootstrap.min.js'
  }

  syntaxhighlighter{
    resource url:'js/syntaxhighlighter/shCore.js', disposition: 'head'
    resource url:'js/syntaxhighlighter/shCore.css', disposition: 'head'
    resource url:'js/syntaxhighlighter/shThemeMidnight.css', disposition: 'head'
    resource url:'js/syntaxhighlighter/configSyntax.js', disposition: 'head'
    resource url:'js/syntaxhighlighter/archivosJS/shBrushJScript.js', disposition: 'head'
    resource url:'js/syntaxhighlighter/archivosJS/shBrushCss.js', disposition: 'head'
    resource url:'js/syntaxhighlighter/archivosJS/shBrushGroovy.js', disposition: 'head'
    resource url:'js/syntaxhighlighter/archivosJS/shBrushJava.js', disposition: 'head'
    resource url:'js/syntaxhighlighter/archivosJS/shBrushRuby.js', disposition: 'head'
    resource url:'js/syntaxhighlighter/archivosJS/shBrushPython.js', disposition: 'head'
    resource url:'js/syntaxhighlighter/archivosJS/shBrushSql.js', disposition: 'head'
    resource url:'js/syntaxhighlighter/archivosJS/shBrushXml.js', disposition: 'head'
    resource url:'js/syntaxhighlighter/archivosJS/shBrushScala.js', disposition: 'head'
  }

}