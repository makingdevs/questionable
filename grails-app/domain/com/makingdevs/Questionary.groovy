package com.makingdevs

@groovy.transform.ToString
class Questionary {

  String title
  String description

  static constraints = {
    title blank:false, size:1..30
    description blank:false, size:1..100
  }

  static hasMany = [questions : Question]
}