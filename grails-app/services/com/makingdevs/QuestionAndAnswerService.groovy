package com.makingdevs

import grails.transaction.Transactional


@Transactional
class QuestionAndAnswerService {

  def questionService
  def answerService
  def tagsService

  def createQuestionsWithAnswersFromSimpleText(fullQuestions){
    def questions = []
    def questionTags = []
    def answers = []
    def lines = fullQuestions.split('\n|\t')
    lines = lines*.trim()

    for(def i=0;i<lines.size();i++){
      if(lines[i]==~ /\#.+/){
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
    }

    lines.each{ line ->
      if(isThisLineAQuestion(line)){
        if(answers && questions){
          answers.each{ answer -> questions.last().addToAnswers(answer) }
          answers.clear()
        }
        def question = questionService.buildQuestionFromText(line.trim())
        questions << question
        questionTags << questionService.getTagsFromText(line)
      }
      if(line.trim() && !isThisLineAQuestion(line)) {
        answers << answerService.buildAnswerFromText(line.trim())
      }
    }

    if(questions){
      answers.each { answer -> questions.last().addToAnswers(answer)}
    }
    else{
      throw new RuntimeException("Cannot parse answers without questions")
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
