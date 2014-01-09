package com.makingdevs

class Question {

	String description
	QuestionType questionType 

    static constraints = {
    	description blank:false, size:1..1000
    }
}