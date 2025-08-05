package game.effect

import game.GameManager
import game.Player

class FlameThrower (
    private val damage: Int,
    private val range: Int
): Effect("\uD83D\uDD25","Lança Chamas", "Ao usar, o lança chamadas atinge todos os jogadores até $range casas a frente, fazendo eles voltarem $damage casas.") {

    override fun apply(gameManager: GameManager, player: Player) {
        val attacked = gameManager.enemiesOf(player).filter { it.kart.position >= player.kart.position &&  it.kart.position <= player.kart.position + range}

        attacked.forEach { gameManager.movePlayer(it, -damage) }

        println("$icon ${attacked.size} inimigos queimados!")
    }
}