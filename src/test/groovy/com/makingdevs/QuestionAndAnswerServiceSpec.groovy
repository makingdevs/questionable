package com.makingdevs

import grails.test.mixin.*
import spock.lang.*


@TestFor(QuestionAndAnswerService)
@Mock([Question,Answer])
class QuestionAndAnswerServiceSpec extends Specification {

  @Unroll
  def "Given a full text generate the question and answers"() {
    given:
      def fullQuestion = """#MULTIPLE_CHOICE What is Groovy?
      (*) un fw
      () un lenguaje
      ( ) una herramienta
    """
    and:
      def questionServiceMock = Mock(QuestionService)
      def answerServiceMock = Mock(AnswerService)
      questionServiceMock.demand.buildQuestionFromText{ t ->
        new Question(description:"What is Groovy?",questionType:QuestionType.MULTIPLE_CHOICE)
      }

      questionServiceMock.demand.getTagsFromText(){ l -> []}

      answerServiceMock.demand.buildAnswerFromText(3..3){ t ->
        new Answer(description:"X",solution:false)
      }
      service.questionService = questionServiceMock.createMock()
      service.answerService = answerServiceMock.createMock()
      Question.metaClass.setTags = {tagsList ->}
    when:
      def questions = service.createQuestionsWithAnswersFromSimpleText(fullQuestion)
      questionServiceMock.verify()
    then:
      questions[0].id > 0
      questions[0].answers.size() == 3
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
      def questionServiceMock = Mock(QuestionService)
      def answerServiceMock = Mock(AnswerService)
      questionServiceMock.demand.buildQuestionFromText(){ t ->
        new Question(description:"What is Groovy?",questionType:QuestionType.MULTIPLE_CHOICE)
      }
      questionServiceMock.demand.getTagsFromText(){ l -> []}

      questionServiceMock.demand.buildQuestionFromText{ t ->
        new Question(description:"What is Groovy?",questionType:QuestionType.MULTIPLE_CHOICE)
      }

      questionServiceMock.demand.getTagsFromText() { l -> []}
      answerServiceMock.demand.buildAnswerFromText(5..5){ t ->
        new Answer(description:"X",solution:false)
      }
      service.questionService = questionServiceMock.createMock()
      service.answerService = answerServiceMock.createMock()
      Question.metaClass.setTags = {tagsList ->}
    when:
      def questions = service.createQuestionsWithAnswersFromSimpleText(fullQuestions)
      questionServiceMock.verify()
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
      def questionServiceMock = Mock(QuestionService)
      def answerServiceMock = Mock(AnswerService)
      questionServiceMock.demand.buildQuestionFromText(){ t ->
        new Question(description:"What is Groovy?",questionType:QuestionType.MULTIPLE_CHOICE)
      }
      questionServiceMock.demand.getTagsFromText() { l -> []}
      questionServiceMock.demand.buildQuestionFromText{ t ->
        new Question(description:"What is Groovy?",questionType:QuestionType.MULTIPLE_CHOICE)
      }
      questionServiceMock.demand.getTagsFromText() { l -> []}

      answerServiceMock.demand.buildAnswerFromText(5..5){ t ->
        new Answer(description:"X",solution:false)
      }
      service.questionService = questionServiceMock.createMock()
      service.answerService = answerServiceMock.createMock()
      Question.metaClass.setTags = {tagsList ->}
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
      def questionServiceMock = Mock(QuestionService)
      def answerServiceMock = Mock(AnswerService)
      questionServiceMock.demand.buildQuestionFromText(1..1){ t ->
        new Question(description:"What is Groovy?",questionType:QuestionType.MULTIPLE_CHOICE)
      }
      questionServiceMock.demand.getTagsFromText() { l -> []}

      answerServiceMock.demand.buildAnswerFromText(2..2){ t ->
        new Answer(description:"X",solution:false)
      }
      service.questionService = questionServiceMock.createMock()
      service.answerService = answerServiceMock.createMock()
      Question.metaClass.setTags = { currentTags = _currentTags}
      Question.metaClass.getTags = { currentTags }
    when:
      def questions = service.createQuestionsWithAnswersFromSimpleText(fullQuestion)
      questionServiceMock.verify()
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
      def questionServiceMock = Mock(QuestionService)
      def answerServiceMock = Mock(AnswerService)
      questionServiceMock.demand.buildQuestionFromText(){ t ->
        new Question(description:"""What is Groovy?
        <pre>
          def list = [3,5,3]

          println list
        </pre>
        (*) un fw
        () un lenguaje
        ( ) una herramienta
      """,questionType:QuestionType.MULTIPLE_CHOICE)
      }
      questionServiceMock.demand.getTagsFromText() { l -> []}

      answerServiceMock.demand.buildAnswerFromText(3..3){ t ->
        new Answer(description:"X",solution:false)
      }
      service.questionService = questionServiceMock.createMock()
      service.answerService = answerServiceMock.createMock()
      Question.metaClass.setTags = { }
    when:
      def questions = service.createQuestionsWithAnswersFromSimpleText(fullQuestion)
      questionServiceMock.verify()
    then:
      questions.size() == 1
      questions[0].answers.size() == 3
  }
}
