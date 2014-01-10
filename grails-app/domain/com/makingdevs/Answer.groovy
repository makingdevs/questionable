package com.makingdevs

class Answer {

	String description

  static constraints = {
   description blank:false, size:1..1000
 }

 static belongsTo = [question: Question]
}
