modules = {

  validation{
    resource url:'js/jquery.validate.min.js'
    resource url:'js/questionary/validation.js'
  }
  
  questionable{
    dependsOn 'syntaxhighlighter'
  	resource url:'css/bootstrap-responsive.min.css'
  	resource url:'css/bootstrap.min.css'
  	resource url:'images/glyphicons-halflings-white.png'
  	resource url:'images/glyphicons-halflings.png'
  	resource url:'js/bootstrap.min.js'
  }

  syntaxhighlighter{
    resource id:"shCore", url:[plugin:'questionable',dir:'js/syntaxhighlighter',file:'shCore.js'], disposition:'head'
    resource id:"shCore",url:[plugin:"questionable",dir:"js/syntaxhighlighter",file:"shCore.css"], disposition:'head'
    resource id:"shThemeDefault",url:[plugin:"questionable",dir:"js/syntaxhighlighter",file:"shThemeDefault.css"], disposition:'head'
    resource id:"configSyntax",url:[plugin:"questionable",dir:"js/syntaxhighlighter",file:"configSyntax.js"], disposition:'head'
    resource id:"shBrushJScript",url:[plugin:"questionable",dir:"js/syntaxhighlighter/archivosJS",file:"shBrushJScript.js"], disposition:'head'
    resource id:"shBrushCss",url:[plugin:"questionable",dir:"js/syntaxhighlighter/archivosJS",file:"shBrushCss.js"], disposition:'head'
    resource id:"shBrushGroovy",url:[plugin:"questionable",dir:"js/syntaxhighlighter/archivosJS",file:"shBrushGroovy.js"], disposition:'head'
    resource id:"shBrushJava",url:[plugin:"questionable",dir:"js/syntaxhighlighter/archivosJS",file:"shBrushJava.js"], disposition:'head'
    resource id:"shBrushRuby",url:[plugin:"questionable",dir:"js/syntaxhighlighter/archivosJS",file:"shBrushRuby.js"], disposition:'head'
    resource id:"shBrushPython",url:[plugin:"questionable",dir:"js/syntaxhighlighter/archivosJS",file:"shBrushPython.js"], disposition:'head'
    resource id:"shBrushSql",url:[plugin:"questionable",dir:"js/syntaxhighlighter/archivosJS",file:"shBrushSql.js"], disposition:'head'
    resource id:"shBrushXml",url:[plugin:"questionable",dir:"js/syntaxhighlighter/archivosJS",file:"shBrushXml.js"], disposition:'head'
    resource id:"shBrushScala",url:[plugin:"questionable",dir:"js/syntaxhighlighter/archivosJS",file:"shBrushScala.js"], disposition:'head'
  }

}