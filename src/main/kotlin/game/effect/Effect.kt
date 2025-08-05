package game.effect

import game.GameManager
import game.Player
import lib.StringPainter

abstract class Effect(
    val icon: String,
    val name: String,
    val description: String,
) {

    abstract fun apply(gameManager: GameManager, player: Player)

    override fun toString(): String {
        return "$icon ${StringPainter.paint(name, StringPainter.TextColor.TXT_GREEN)}"
    }
}