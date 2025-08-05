package game.effect

import game.GameManager
import game.Player

class IceThrower (
    private val range: Int,
): Effect("❄","Lança Gelo", "Ao usar, o lança gelo atinge todos os jogadores até $range casas a frente, fazendo eles perderem a vez na rodada.") {

    override fun apply(gameManager: GameManager, player: Player) {
        val attacked = gameManager.enemiesOf(player).filter { it.kart.position >= player.kart.position &&  it.kart.position <= player.kart.position + range}

        attacked.forEach { it.canPlay = false }

        println("$icon ${attacked.size} inimigos congelados!")
    }
}