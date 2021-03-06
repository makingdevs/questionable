package com.makingdevs

import grails.test.mixin.*
import static org.junit.Assert.*
import org.junit.*
import spock.lang.*


@TestFor(QuestionService)
@Mock([Question, Answer])
class QuestionServiceSpec extends Specification{

  @Unroll
  def "Evaluar una pregunta abierta siendo la respuesta \"#_answer\" "(){
    given:
      def question = anOpenQuestion()
    when:
      def evaluate = service.evaluateAnswer(question.id,_answer)
    then:
      evaluate == rating
      question.questionType == QuestionType.OPEN
    where:
      _answer                                       || rating
      "Esta es una respuesta abierta a la pregunta" || 1.0
      ""                                            || 0.0
  }

  @Unroll
  def """Cuando la respuesta correcta a una pregunta F/V es "#rightAnswer"
      y se responde "#_answer" el puntaje debe ser "#rating" """(){
    given:
      def question = aTrueFalseQuestion(answer_1,solution_1,answer_2,solution_2)
    when:
      def answer = Answer.findByDescriptionLike("%${_answer}%")
      def evaluate = service.evaluateAnswer(question.id, answer.id)
    then:
      evaluate == rating
      question.questionType == QuestionType.TRUE_FALSE
    where:
      answer_1 | solution_1 | answer_2  | solution_2  |  _answer   || rating
      "true"   |   true     |  "false"  |  false      |  "false"   || 0.0
      "true"   |   true     |  "false"  |  false      |  "true"    || 1.0
      "false"  |   true     |  "true"   |  false      |  "false"   || 1.0
      "false"  |   true     |  "true"   |  false      |  "true"    || 0.0
  }

  @Unroll
  def """Siendo la pregunta de opción múltiple "'Groovy es un lenguaje de tipo:'"
      y la respuesta "#_answer" el puntaje debe ser "#rating" """(){
    given:
      def question = aMultipleChoiceQuestion()
    when:
      def answer = Answer.findByDescriptionLike("%${_answer}%")
      def evaluate = service.evaluateAnswer(question.id, answer.id)
    then:
      evaluate == rating
      question.questionType == QuestionType.MULTIPLE_CHOICE
    where:
      _answer     || rating
      "Dinamico"  || 1.0
      "Estatico"  || 0.0
      "Funcional" || 0.0
  }

  @Unroll
  def """Siendo la pregunta de multi solución "'Groovy es:'"
    y las respuestas "#_answer_user" el puntaje debe ser "#rating" """(){
    given:
      def listMap = [[description:"Lenguaje dinamico",solution:true],
                    [description:"Lenguaje orientado a objetos",solution:true],
                    [description:"Lenguaje funcional",solution:false]]
      def question = aMultipleResponse(listMap)
    when:
      def answers = Answer.findAllByDescriptionInList(_answer_user)
      def evaluate = service.evaluateAnswer(question.id, answers.id)
    then:
      evaluate == rating
      question.questionType == QuestionType.MULTIPLE_RESPONSE
    where:
                    _answer_user                                                 || rating
      ["Lenguaje dinamico","Lenguaje orientado a objetos"]                       || 1.00
      ["Lenguaje dinamico","Lenguaje orientado a objetos","Lenguaje funcional"]  || 0.66
      ["Lenguaje dinamico","Lenguaje funcional"]                                 || 0.33
      ["Lenguaje orientado a objetos","Lenguaje funcional"]                      || 0.33
      ["Lenguaje funcional"]                                                     || 0.00
      []                                                                         || 0.33
  }


  @Unroll
  def """Siendo la pregunta de multi solución "'Groovy es:'"
    y las respuestas "#_answer_user" el puntaje debe ser "#rating" """(){
    given:
      def listMap = [[description:"Lenguaje dinamico",solution:true],
                    [description:"Lenguaje orientado a objetos",solution:true],
                    [description:"Lenguaje estatico",solution:false],
                    [description:"Lenguaje funcional",solution:false]]
      def question = aMultipleResponse(listMap)
    when:
      def answers = Answer.findAllByDescriptionInList(_answer_user)
      def evaluate = service.evaluateAnswer(question.id, answers.id)
    then:
      evaluate == rating
      question.questionType == QuestionType.MULTIPLE_RESPONSE
    where:
                    _answer_user                                                                          || rating
      ["Lenguaje dinamico","Lenguaje orientado a objetos"]                                                || 1.0
      ["Lenguaje dinamico","Lenguaje orientado a objetos","Lenguaje estatico","Lenguaje funcional"]       || 0.5
      ["Lenguaje dinamico","Lenguaje orientado a objetos","Lenguaje funcional"]                           || 0.75
      ["Lenguaje dinamico","Lenguaje orientado a objetos","Lenguaje estatico"]                            || 0.75
      ["Lenguaje dinamico","Lenguaje estatico","Lenguaje funcional"]                                      || 0.25
      ["Lenguaje orientado a objetos","Lenguaje estatico","Lenguaje funcional"]                           || 0.25
      ["Lenguaje orientado a objetos","Lenguaje funcional"]                                               || 0.5
      ["Lenguaje dinamico","Lenguaje funcional"]                                                          || 0.5
      ["Lenguaje orientado a objetos","Lenguaje estatico"]                                                || 0.5
      ["Lenguaje dinamico","Lenguaje estatico"]                                                           || 0.5
      ["Lenguaje dinamico"]                                                                               || 0.75
      ["Lenguaje orientado a objetos"]                                                                    || 0.75
      ["Lenguaje estatico","Lenguaje funcional"]                                                          || 0.0
      ["Lenguaje estatico" ]                                                                              || 0.25
      ["Lenguaje funcional" ]                                                                             || 0.25
      []                                                                                                  || 0.5
  }


  private Question anOpenQuestion(){
    new Question(description:"¿Es esta una pregunta abierta?, Describa",questionType:QuestionType.OPEN).save()
  }

  private Question aTrueFalseQuestion(answer_1,solution_1,answer_2,solution_2){
    def question = new Question(description:"¿Falso es igual a verdadero?", questionType:QuestionType.TRUE_FALSE)
    def answer1 = new Answer(description:answer_1,solution:solution_1)
    def answer2 = new Answer(description:answer_2,solution:solution_2)
    question.addToAnswers(answer1)
    question.addToAnswers(answer2)
    question.save(validate:false)
    question
  }

  private Question aMultipleChoiceQuestion(){
    def question = new Question(description:"¿Groovy es un lenguaje de tipo?", questionType:QuestionType.MULTIPLE_CHOICE)
    def answer1 = new Answer(description:"Dinamico", solution:true)
    def answer2 = new Answer(description:"Estatico", solution:false)
    def answer3 = new Answer(description:"Funcional", solution:false)
    question.addToAnswers(answer1)
    question.addToAnswers(answer2)
    question.addToAnswers(answer3)
    question.save(validate:false)
    question
  }

  private Question aMultipleResponse(listMap){
    def question =new Question(description:"¿Ques es Groovy?",questionType:QuestionType.MULTIPLE_RESPONSE)
    for(i in listMap) {
      def answer = new Answer(description:i.description, solution:i.solution)
      question.addToAnswers(answer)
    }
    question.save(validate:false)
    question
  }

}
