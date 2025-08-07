package game.effect

import game.GameManager
import game.Player

class Caltrop(
    private val damage: Int,
) : Effect("\uD83D\uDCCD","Fura Pneu","Ao usar, a armadilha é armada na posição imediatamente anterior a atual do jogador, ficando por lá até um jogador passar por cima. O jogador que cair na armadilha, volta $damage casas.") {

    override fun apply(gameManager: GameManager, player: Player) {

        gameManager.movePlayer(player, -damage)

        player.effect = null
    }
}