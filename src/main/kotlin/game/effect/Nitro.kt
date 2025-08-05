package game.effect

import game.GameManager
import game.Player
import lib.StringPainter.BackgroundColor.BG_GREEN
import lib.StringPainter.TextColor.*
import lib.StringPainter.paint

class Nitro(
    private val distance: Int
): Effect("⛽","Nitro", "Com o impulso do Nitro, o jogador avança $distance casas imediatamente.") {

    override fun apply(gameManager: GameManager, player: Player) {
        println("Você ganhou um $icon $name e avançou ${paint(" +$distance ", TXT_BLACK, BG_GREEN)} casas!")
        gameManager.movePlayer(player, distance)
    }

    override fun toString(): String {
        return "$icon ${paint(name, TXT_BLUE)} - $description"
    }
}