package app.imclueless

import app.imclueless.pieces.Character
import app.imclueless.pieces.Location
import app.imclueless.pieces.PieceType
import app.imclueless.pieces.Weapon
import app.imclueless.state.Possibility
import java.io.File
import java.lang.RuntimeException
import java.util.*

fun main(args: Array<String>) {
    val commandLog = File("commands.log")
    if (!commandLog.exists()) {
        commandLog.createNewFile()
    }
    commandLog.writeText(commandLog.readText() + "--- " + Date().toString() + "\n")

    val possible = mutableListOf<Possibility>()
    for (character in Character.values()) {
        for (location in Location.values()) {
            for (weapon in Weapon.values()) {
                possible.add(Possibility(character, location, weapon))
            }
        }
    }

    val myWeapons = mutableListOf<Weapon>()
    val myCharacters = mutableListOf<Character>()
    val myLocations = mutableListOf<Location>()

    while (true) {
        try {
            val input = readln()
            commandLog.writeText(commandLog.readText() + input + "\n")
            val command = input.split(" ")
            when (command.getOrNull(0)) {
                "card" -> {
                    determinePiece(command.getOrNull(1))?.let { piece ->
                        possible.removeAll {
                            it.contains(piece)
                        }
                        println("Possibilities eliminated (${possible.size} left)")
                    }
                }

                "mycard" -> {
                    determinePiece(command.getOrNull(1))?.let { piece ->
                        possible.removeAll {
                            it.contains(piece)
                        }

                        if (piece is Location) {
                            myLocations.add(piece)
                        }
                        if (piece is Weapon) {
                            myWeapons.add(piece)
                        }
                        if (piece is Character) {
                            myCharacters.add(piece)
                        }

                        println("Possibilities eliminated (${possible.size} left)")
                    }
                }

                "eliminate" -> {
                    val possibility = listOf(
                        determinePiece(command.getOrNull(1)),
                        determinePiece(command.getOrNull(2)),
                        determinePiece(command.getOrNull(3))
                    )

                    possible.removeAll {
                        it.contains(possibility[0]) && it.contains(possibility[1]) && it.contains(possibility[2])
                    }
                    println("Possibilities eliminated (${possible.size} left)")
                }

                "guess" -> {
                    determinePiece(command.getOrNull(1))?.let { location ->
                        if (location !is Location) return@let

                        if (!myLocations.contains(location) && !possible.any { it.location == location }) {
                            println("Someone else has this location.")
                        }

                        val bestGuesses = possible.toMutableList()
                        bestGuesses.sortByDescending { possibility ->
                            possible.count { it.location == possibility.location } +
                                    possible.count { it.weapon == possibility.weapon } +
                                    possible.count { it.character == possibility.character }
                        }

                        val possibleGuesses = bestGuesses.toMutableList()
                        possibleGuesses.removeAll {
                            it.location !== location
                        }
                        bestGuesses.removeAll(possibleGuesses)

                        if (possibleGuesses.size > 0) {
                            println("Best possible:")
                            val guessesToPrint = possibleGuesses.take(5)
                            if (possible.size < 70) {
                                guessesToPrint.shuffled()
                            }
                            for (guess in guessesToPrint) {
                                println(guess)
                            }
                        } else {
                            println("Best possible (location unavailable):")
                            val guessesToPrint = bestGuesses.take(5)
                            if (possible.size < 40) {
                                guessesToPrint.shuffled()
                            }
                            for (guess in guessesToPrint) {
                                println(Possibility(guess.character, location, guess.weapon))
                            }
                        }
                    }
                }

                "location" -> {
                    val bestLocations = possible.toMutableList()
                    bestLocations.groupingBy { it.location }.eachCount().entries.sortedByDescending { it.value }.take(5)
                        .forEach {
                            println("${it.key.asString} (${it.value})")
                        }
                }
            }

            if (possible.size == 1) {
                println("CERTAIN")
                println(possible[0])
                return
            }
        } catch (e: Throwable) {

        }
    }
}

fun determinePiece(asString: String?): PieceType? {
    if (asString == null) return null
    for (character in Character.values()) {
        if (character.asString == asString) return character
    }
    for (location in Location.values()) {
        if (location.asString == asString) return location
    }
    for (weapon in Weapon.values()) {
        if (weapon.asString == asString) return weapon
    }
    println("Invalid piece $asString")
    throw RuntimeException("Invalid piece")
}