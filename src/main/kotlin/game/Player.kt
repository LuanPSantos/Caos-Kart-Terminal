package game

import game.effect.Effect
import kotlin.random.Random

class Player(
    val number: Int,
    val kart: Kart,
    var canPlay: Boolean = true
) {
    var effect: Effect? = null

    fun render(): String {
        return kart.graphics
    }

    fun rollDice(): Int {
        return Random.nextInt(1, 7)
    }

    fun moveTo(distance: Int) {
        kart.position = distance
    }

    fun position(): Int {
        return kart.position
    }

    override fun toString(): String {
        return "Jogador [$number] ${render()}"
    }
}