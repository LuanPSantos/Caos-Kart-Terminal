package game.effect

import game.GameManager
import game.Player
import lib.StringPainter.BackgroundColor.*
import lib.StringPainter.TextColor.TXT_BLACK
import lib.StringPainter.TextColor.TXT_RED
import lib.StringPainter.paint

class PitStop(
    private val distance: Int,
) : Effect(
    "\uD83D\uDED1",
    "Pit Stop",
    "O jogador sofre com problemas mecânicos e por isso precisa passar no Pitstop. Volte $distance casas imediatamente."
) {

    override fun apply(gameManager: GameManager, player: Player) {
        println("Você teve que parar $icon $name e perdeu ${paint(" -$distance ", TXT_BLACK, BG_RED)} casas!")
        gameManager.movePlayer(player, -distance)
    }

    override fun toString(): String {
        return "$icon ${paint(name, TXT_RED)} - $description"
    }
}