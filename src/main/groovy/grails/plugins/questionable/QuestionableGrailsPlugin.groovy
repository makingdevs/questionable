package grails.plugins.questionable

import grails.plugins.*

class QuestionableGrailsPlugin extends Plugin {
    def grailsVersion = "3.1.8 > *"
    def pluginExcludes = [
        "grails-app/views/error.gsp",
        "grails-app/controllers/com/makingdevs/UserTestController.groovy",
        "grails-app/domain/com/makingdevs/UserTest.groovy",
        "grails-app/domain/com/makingdevs/AnotherUserTest.groovy",
        "grails-app/views/userTest/*"
    ]


    def author = "Jorge Acosta Lemus"
    def authorEmail = "jorge@makingdevs.com"
    def description = '''\
That plugin add questionarys for evaluate something
'''
    def profiles= ['web']

    def documentation = "https://github.com/makingdevs/questionable"
    def license = "APACHE"
    def organization = [ name: "MakingDevs", url: "http://makingdevs.com/" ]

    def developers = [
      [ name: "Jorge Acosta Lemus", email: "jorge@makingdevs.com" ],
      [ name: "Sergio Rodríguez", email: "sergio@makingdevs.com" ],
      [ name: "Felipe Juárez", email: "felipe@makingdevs.com" ],
      [ name: "Rodrigo Martínez", email: "rockdrigo.mtz@gmail.com" ],
      [ name: "José Juan Reyes", email: "juan@makingdevs.com" ],
      [ name: "Marlen Rodríguez Reyes", email: "marlen@makingdevs.com"]
    ]

    def issueManagement = [ system: "GITHUB", url: "https://github.com/makingdevs/questionable/issues" ]

    def scm = [ url: "https://github.com/makingdevs/questionable" ]

    Closure doWithSpring() { {->
      // TODO Implement runtime spring config (optional)
    }
    }

    void doWithDynamicMethods() {
      // TODO Implement registering dynamic methods to classes (optional)
    }

    void doWithApplicationContext() {
      // TODO Implement post initialization spring config (optional)
    }

    void onChange(Map<String, Object> event) {
      // TODO Implement code that is executed when any artefact that this plugin is
      // watching is modified and reloaded. The event contains: event.source,
      // event.application, event.manager, event.ctx, and event.plugin.
    }

    void onConfigChange(Map<String, Object> event) {
      // TODO Implement code that is executed when the project configuration changes.
      // The event is the same as for 'onChange'.
    }

    void onShutdown(Map<String, Object> event) {
      // TODO Implement code that is executed when the application shuts down (optional)
    }

}
