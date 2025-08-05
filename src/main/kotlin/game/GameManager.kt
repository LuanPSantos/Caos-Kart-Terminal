package game

import game.effect.*
import kotlin.random.Random

class GameManager(
    val players: List<Player>,
    val gameMap: GameMap
) {
    private val surprises: List<Any> = listOf(
//        Nitro(6),
//        PitStop(6),
//        GuidedMissile(8),
//        Caltrop(3),
//        FlameThrower(4, 5),
        IceThrower(4)
    )

    private var currentPlayerIndex = 0
    private var roundCounter: Int = 0

    private val finishers: MutableList<Player> = mutableListOf()

    fun nextPlayer(): Player {
        currentPlayerIndex = (currentPlayerIndex + 1) % players.size
        return players[currentPlayerIndex]
    }

    fun currentPlayer(): Player {
        return players[currentPlayerIndex]
    }

    fun nextRound(): Int {
        return ++roundCounter
    }

    fun movePlayer(player: Player, distance: Int) {

        val newPosition = (0).coerceAtLeast(player.position() + distance).coerceAtMost(gameMap.size)

        player.moveTo(newPosition)

        if (player.position() >= gameMap.size) {
            finishers.add(player)
        }

        gameMap.cells[newPosition].effect?.let {
            when(it) {
                is Caltrop -> it.apply(this, player)
            }
        }

        if (gameMap.cells[newPosition].isSurprise) {
            val surprise = surprises[Random.nextInt(surprises.size)]

            when (surprise) {
                is Nitro -> surprise.apply(this, player)
                is PitStop -> surprise.apply(this, player)
                is GuidedMissile -> {
                    print("Você ganhou um $surprise! Para usar agora digite (Y/N): ")
                    if (Game.readPlayerCommand() == Game.Commands.YES) {
                        surprise.apply(this, player)
                    } else {
                        player.effect = surprise
                    }
                }
                is Caltrop -> {
                    print("Você ganhou um $surprise! Para usar agora digite (Y/N): ")
                    if (Game.readPlayerCommand() == Game.Commands.YES) {

                        val playersInCaltropPosition = players.filter { it.position() == (player.position()-1) }
                        if(playersInCaltropPosition.isEmpty()){
                            gameMap.cells[player.position()-1].effect = surprise
                        }else{
                            playersInCaltropPosition.forEach { surprise.apply(this, it) }
                        }
                    } else {
                        player.effect = surprise
                    }
                }
                is FlameThrower -> {
                    print("Você ganhou um $surprise! Para usar agora digite (Y/N): ")
                    if (Game.readPlayerCommand() == Game.Commands.YES) {
                        surprise.apply(this, player)
                    } else {
                        player.effect = surprise
                    }
                }
                is IceThrower -> {
                    print("Você ganhou um $surprise! Para usar agora digite (Y/N): ")
                    if (Game.readPlayerCommand() == Game.Commands.YES) {
                        surprise.apply(this, player)
                    } else {
                        player.effect = surprise
                    }
                }
            }
        }
    }

    fun enemiesOf(player: Player): List<Player> {
        return players.filter { it != player }
    }

    fun hasWinner(): Boolean {
        return finishers.isNotEmpty()
    }

    fun winners(): List<Player> {
        return finishers
    }

    fun applyPlayerEffect(player: Player) {
        when (val effect = player.effect) {
            is GuidedMissile -> effect.apply(this, player)
            else -> println("Nada para usar.")
        }
    }
}