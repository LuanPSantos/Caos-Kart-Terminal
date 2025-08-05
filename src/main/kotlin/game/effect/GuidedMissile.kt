package game.effect

import game.GameManager
import game.Player

class GuidedMissile(
    private val damage: Int
) : Effect(
    "\uD83D\uDE80",
    "Míssil Teleguiado",
    "O jogador com o míssil escolhe outro jogador na pista como alvo. O alvo atingido volta $damage casas."
) {

    override fun apply(gameManager: GameManager, player: Player) {
        val enemies = gameManager.enemiesOf(player)
        println("Você usou o $icon $name.")
        println("Escolha um jogador como alvo e ele voltará $damage casas: ${enemies.map { "[${it.number}]${it.render()}, " }}")

        var enemy: Player?
        do {
            print("Digite o número do jogador: ")

            val selected = readln().toInt()

            enemy = enemies.find { it.number == selected }
            if(enemy == null) println("Inválido")
        } while (enemy == null)

        gameManager.movePlayer(enemy, -damage)
        player.effect = null
    }
}