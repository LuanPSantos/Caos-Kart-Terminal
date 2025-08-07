package game

import lib.StringPainter.BackgroundColor.*
import lib.StringPainter.TextColor.*
import lib.StringPainter.paint
import java.lang.Thread.sleep
import kotlin.reflect.KClass

class Game(
    players: List<Player>,
    val cards: List<KClass<*>> = listOf(),
    private val gameMap: GameMap = GameMap(players),
    private val gameManager: GameManager = GameManager(players, gameMap)
) {

    fun run() {
        while (true) {
            println(paint("********** Início da rodada ${gameManager.nextRound()} **********", TXT_BLUE))
            println()
            sleep(500)
            gameManager.players.forEach { currentPlayer ->

                if(!currentPlayer.canPlay) {
                    println("$currentPlayer não jogará nessa rodada.")
                    currentPlayer.canPlay = true
                    return@forEach
                }

                println("Vez do(a) $currentPlayer!")

                gameMap.render()
                println()

                currentPlayer.effect?.let {
                    print("-> $currentPlayer, digite D para lançar o dado ou P para usar seu ${currentPlayer.effect}: ")
                } ?: print("-> $currentPlayer, digite D para lançar o dado: ")
                val command = readPlayerCommand()

                val dice = when(command) {
                    Commands.ROLL_DICE -> {
                        currentPlayer.rollDice()
                    }
                    Commands.USE_POWER_UP -> {
                        gameManager.applyPlayerEffect(currentPlayer)

                        currentPlayer.rollDice()
                    }
                    else -> 0
                }

                println("-> $currentPlayer, você tirou ${paint(" $dice ", TXT_BLACK, BG_CYAN)} no dado!")

                gameManager.movePlayer(currentPlayer, dice)

                gameMap.render()

                gameManager.checkEffect(currentPlayer)

                println("Próximo jogador: ${gameManager.nextPlayer()}. Digite Enter para continuar...")
                readln()
            }
            println(paint("********** Fim da rodada ${gameManager.nextRound()} **********", TXT_BLUE))

            if(gameManager.hasWinner()) {
                println(paint("**** FIM DE JOGO!!! ****", TXT_BLACK, BG_GREEN))
                gameManager.winners().forEach{println("Parabéns $it, você ganhou!")}
                return
            }
        }

    }

    companion object {
        fun readPlayerCommand(): Commands {
            var command: Commands?
            do {

                val input = readln()

                command = when(input) {
                    "D" -> Commands.ROLL_DICE
                    "P" -> Commands.USE_POWER_UP
                    "Y" -> Commands.YES
                    "N" -> Commands.NO
                    else -> {
                        println("Inválido")
                        null
                    }
                }
            } while (command == null)

            return command
        }
    }



    enum class Commands {
        ROLL_DICE, USE_POWER_UP, YES, NO
    }
}