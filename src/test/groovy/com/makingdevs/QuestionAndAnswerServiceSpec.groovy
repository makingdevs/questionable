package com.makingdevs

import grails.test.mixin.*
import spock.lang.*


@TestFor(QuestionAndAnswerService)
@Mock([Question,Answer])
class QuestionAndAnswerServiceSpec extends Specification {

  QuestionService questionService = Mock(QuestionService)
  AnswerService answerService = Mock(AnswerService)

  def setup(){
    service.questionService = questionService
    service.answerService = answerService
  }

  @Unroll
  def "Given a full text generate the question and answers"() {
    given:
      def fullQuestion = """#MULTIPLE_CHOICE What is Groovy?
      (*) un fw
      () un lenguaje
      ( ) una herramienta
    """
    and:
      Question question = new Question(description:"What is Groovy?",questionType:QuestionType.MULTIPLE_CHOICE)
      Answer answer = new Answer(description:"X",solution:false)
    when:
      question.setTags >> []
      questionService.buildQuestionFromText(_) >> question
      questionService.getTagsFromText(_) >> []
      answerService.buildAnswerFromText(_) >> answer
      def questions = service.createQuestionsWithAnswersFromSimpleText(fullQuestion)
      //questionService.verify()
    then:
      questions[0].id > 0
      //questions[0].answers.size() == 1*/
  }

  @Unroll
  def "Given a full text generate the questions with their answers"(){
    given:
      def fullQuestions = """#MULTIPLE_CHOICE What is Groovy?
      (*) un fw
      () un lenguaje
      ( ) una herramienta
      #MULTIPLE_RESPONSE What is Grails?
      [*] un framework
      [] una herramienta del sistema operativo
    """
    and:
      Question question = new Question(description:"What is Groovy?",questionType:QuestionType.MULTIPLE_CHOICE)
      questionService.buildQuestionFromText(_) >> question
      questionService.getTagsFromText(_) >> []
      questionService.buildQuestionFromText(_) >> question
      questionService.getTagsFromText(_) >> []
      answerService.buildAnswerFromText(_) >> new Answer(description:"X",solution:false)
      question.setTags >> []
    when:
      def questions = service.createQuestionsWithAnswersFromSimpleText(fullQuestions)
      //questionService.verify()
    then:
      questions.size() == 2
      questions[0].answers.size() == 3
      questions[1].answers.size() == 2
  }

  @Unroll
  def "Test creation with simple String"() {
    given:
      def fullQuestions = "#MULTIPLE_CHOICE What is Groovy?\n(*) un fw\n() un lenguaje\n( ) una herramienta\n#MULTIPLE_RESPONSE What is Grails?\n[*] un framework\n[] una herramienta del sistema operativo"
    and:
      Question question = new Question(description:"What is Groovy?",questionType:QuestionType.MULTIPLE_CHOICE)
      questionService.buildQuestionFromText(_) >> question
      questionService.getTagsFromText(_) >> []
      questionService.buildQuestionFromText >> question
      questionService.getTagsFromText(_) >> []

      answerService.buildAnswerFromText(_) >> new Answer(description:"X",solution:false)
      question.setTags >> []
    when:
      def questions = service.createQuestionsWithAnswersFromSimpleText(fullQuestions)
    then:
      questions.size() == 2
      questions[0].answers.size() == 3
      questions[1].answers.size() == 2
  }

  @Unroll
  def "Given a text generate the question with their answers and tags"() {
    given:
      def fullQuestion = _fullQuestion
    and:
      def currentTags = []
      Question question = new Question(description:"What is Groovy?",questionType:QuestionType.MULTIPLE_CHOICE)
      questionService.buildQuestionFromText(_) >> question
      questionService.getTagsFromText(_) >> []

      answerService.buildAnswerFromText(_) >> new Answer(description:"X",solution:false)
      question.setTags >> _currentTags
      currentTags = _currentTags
      question.getTags >> currentTags
    when:
      def questions = service.createQuestionsWithAnswersFromSimpleText(fullQuestion)
      //questionService.verify()
    then:
      questions.size() == 1
      questions[0].answers.size() == 2
      questions[0].tags == _currentTags
    where:
      _fullQuestion                                                                   ||  _currentTags
      "#MULTIPLE_CHOICE What is Groovy? [groovy,language]\n() un fw\n(*) un lenguaje" ||  ['groovy','language']
  }


  @Unroll
  def "Given a text with integrated code generate the question with their answers"(){
    given:
      def fullQuestion = """#MULTIPLE_CHOICE What is Groovy?
      <pre>
        def list = [3,5,3]

        println list
      </pre>
      (*) un fw
      () un lenguaje
      ( ) una herramienta
      """
    and:
      Question question = new Question(description:"""What is Groovy?
        <pre>
          def list = [3,5,3]
          println list
        </pre>
        (*) un fw
        () un lenguaje
        ( ) una herramienta
      """,questionType:QuestionType.MULTIPLE_CHOICE)
      questionService.buildQuestionFromText(_) >> question

      questionService.getTagsFromText(_) >> []

      answerService.buildAnswerFromText(_) >> new Answer(description:"X",solution:false)
      question.setTags >> []
    when:
      def questions = service.createQuestionsWithAnswersFromSimpleText(fullQuestion)
      //questionService.verify()
    then:
      questions.size() == 1
      questions[0].answers.size() == 3
  }
}
