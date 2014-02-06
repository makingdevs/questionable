package com.makingdevs

class QuestionaryTagLib {
    
  def showQuestionaryForThisInstance = { attrs, body ->
    println attrs.instance
    println body
    //def listQuestioanryAvailable=Questionary.list()
    //listQuestioanryAvailable.each{it->
    //  out<<"<li><input type=\"radio\" value=\"${it.id}\" name=\"questionary\"/>${it.title}</li>"
    //}
    out << "<ul>"
      10.times { t ->
        out << """\
          <li>${g.link(controller:'questionaryController'){"${t}"}}</li>
        """
      }
    out << "</ul>"
  }



}
