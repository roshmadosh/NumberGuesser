package com.hiroshisprojects

import java.util.Random
import kotlin.system.exitProcess


fun main() {
    // generate random number between 1 and 100
    val randomNumber = Random().nextInt(100) + 1
    val guesses = ArrayList<Int>()

    print("*******")
    print("WELCOME TO NUMBER GUESSER")
    print("*******")

    // ask the user for an input until he guesses right, or types "exit"
    while (true) {
        // extra space for readability
        println()

        // get user input
        println("Please enter a number between 1 and 100:")
        val input = readlnOrNull()

        // perform options, if entered
        when (input) {
            Options.LIST.label -> {
                println("The values you've entered so far are")
                println(guesses)
                continue
            }
            Options.QUIT.label -> {
                println("Exiting app...")
                exitProcess(1)
            }
        }

        // input validation
        val isValid = validateInput(input)
        if (!isValid)  {
            println("Invalid input. Please try again.")
            continue
        }

        // if input guess is correct, end program
        val guess = input!!.toInt()
        guesses += guess
        when {
            guess == randomNumber -> {
                println("You guessed correct! It took you ${guesses.size} many guesses!")
                break
            }
            guess > randomNumber -> println("You guessed too high.")
            else -> println("You guessed too low.")
        }


    }

}

enum class Options(val label: String) {
    QUIT("quit"),
    LIST("list")
}

fun validateInput(input: String?): Boolean {
    val isNull = input.isNullOrEmpty()
    val isNotNumber = input?.toIntOrNull() == null
    if (isNull || isNotNumber) {
        return false
    }

    return input!!.toInt() > 0 && input.toInt() < 101
}

