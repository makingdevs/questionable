package com.makingdevs

import grails.transaction.Transactional

@Transactional
class AnswerService {

	def buildAnswerFromText(simpleText){    
		def answerType = getAnswerType(simpleText)
		def answer
		//log.error ">>>>>>>>>>>>>>>>>>>>>>>> " + answerType[0]
		if(answerType){ 
			def description = (simpleText - answerType[0])?.trim()
			if(description)
			answer = new Answer(solution:getSolution(answerType[0]),
				description:description)
			else if(answerType[0] ==~ /F\*|V\*|F|V/)
			answer = new Answer(solution:getSolution(answerType[0]),
				description:getDescriptionTrueOrFalse(answerType[0][0]))
			else
			throw new RuntimeException("Cannot parse answer '$simpleText'")
		}    
		else
		throw new RuntimeException("Cannot parse answer '$simpleText'")

		answer
	}

	private def getAnswerType(plainText){
    (plainText =~ /\(\*\)|\[\*\]|F\*|V\*|\(\)|\(\s\)|\[\]|\[\s\]|F|V/)
  }

  private def getDescriptionTrueOrFalse(trueOrFalseValue){
    trueOrFalseValue == "F" ? "Falso" : "Verdadero"    
  }
  
  private def getSolution(typeOfQuestion){
    if(typeOfQuestion ==~ /\(\)|\(\s\)|\[\]|\[\s\]|F|V/)
      return false
    else if(typeOfQuestion ==~ /\(\*\)|\[\*\]|F\*|V\*/)
      return true      
  }  
}
