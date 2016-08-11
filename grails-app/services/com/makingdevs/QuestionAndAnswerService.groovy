package com.makingdevs

import grails.transaction.Transactional


@Transactional
class QuestionAndAnswerService {

  def questionService
  def answerService
  def tagsService

  def createQuestionsWithAnswersFromSimpleText(fullQuestions){
    buildQuestions(getLines(fullQuestions))
  }

  private def getLines(fullQuestions) {
    def lines = fullQuestions.split('\n|\t')
    lines = lines*.trim()
    for(int i=0;i<lines.size();i++){
      if(lines[i]==~ /\#.+/) {
        while(lines[i+1] != null && !(isThisLineAnswer(lines[i+1]) || isThisLineAQuestion(lines[i+1]))){
          if(isThisLineTheBeginningOfTheCode(lines[i+1])){
            while(lines[i+1] != null && !isThisLineTheEndOfTheCode(lines[i+1])){
              lines[i]+="\n"+lines[i+1]
              lines.remove(i+1)
            }
          }
          if(lines[i+1] != null){
            lines[i]+="\n"+lines[i+1]
            lines.remove(i+1)
          }
          else
            throw new Exception("Cannot parse the answer, close the <pre> tag")
        }
      }
      if (!lines[i].trim()) {
        lines.remove(i)
      }
    }
    lines
  }

  private List buildQuestions(def lines) {
    List questions = []
    List questionTags = []
    lines.each { line ->
      if(isThisLineAQuestion(line)){
        Question question = questionService.buildQuestionFromText(line.trim())
        questions << question
        questionTags << questionService.getTagsFromText(line)
      } else if (line.trim()) {
        Answer answer = answerService.buildAnswerFromText(line.trim())
        if (questions) {
          questions.last().addToAnswers(answer)
        }
      }
    }

    questions*.save()

    questions.eachWithIndex{ q, index ->
      tagsService.addTagsToAQuestionFromSimpleText(q, questionTags[index])
    }

    questions
  }

  def isThisLineAQuestion(line){
    line.trim() && line.trim()[0]=="#"
  }

  def isThisLineAnswer(line){
    line ==~ /(\([\*|\ ]?\)|\[[\*|\s]?\]).+|F\*?$|V\*?$/
  }

  def isThisLineTheBeginningOfTheCode(line){
    line ==~ /\<pre*\>.*/
  }

  def isThisLineTheEndOfTheCode(line){
    line ==~ /.*\<\/pre\>.*/
  }
}
