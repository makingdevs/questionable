package com.makingdevs

class Answer {

	String description
  Boolean solution

  static constraints = {
   description blank:false, size:1..1000
 }

 static belongsTo = [question: Question]
}
