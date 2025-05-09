package com.synac.quiztimespring

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class QuizTimeSpringBootServerApplication

fun main(args: Array<String>) {
	runApplication<QuizTimeSpringBootServerApplication>(*args)
}
