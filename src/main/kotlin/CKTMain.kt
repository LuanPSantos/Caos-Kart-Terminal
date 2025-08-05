import game.*
import game.effect.*
import lib.StringPainter.BackgroundColor.*
import lib.StringPainter.TextColor.*
import lib.StringPainter.paint

fun main() {

    val game = Game(
        listOf(
            Player(1, Kart(0, "\uD83D\uDE38")),
            Player(2, Kart(0, "\uD83D\uDC36"))
        )
    )

//    val caltrop = Actionable(GuidedMissile(5, listOf()))

//    game.cards.add(Caltrop::class)
//    game.cards.add(FlameThrower::class)
//    game.cards.add(IceThrower::class)
//    game.cards.add(Nitro::class)
//    game.cards.add(PitStop::class)
//    game.cards.add(GuidedMissile::class)

    println(paint("Caos Kart Table", TXT_RED, BG_BLACK))
    println("=== REGRAS ===")
    println("1 - A cada rodada, cada jogador joga do dado 1 vez.")
    println(
        "2 - Caso o jogador caia em uma casa Coringa (${
            paint(
                "?",
                TXT_BLACK,
                BG_YELLOW
            )
        }), ele joga o dado novamente para pegar uma carta."
    )
    println("3 - Ganha quem cruzar a linha de chegada primeiro. Em caso de empate, ganha quem cruzou com mais velocidade (número maior do dado)")
    println()
    println("=== CARTAS ===")
    println()
//    println(GuidedMissile(5, listOf()))
//    println(Caltrop(5, 1, Player("", Kart(1, "")), game))
//    println(FlameThrower(5, 1, Player("", Kart(1, "")), game.players))
//    println(IceThrower(1, Player("", Kart(1, "")), game.players))
//    println(Nitro(5, Player("", Kart(1, ""))))
//    println(PitStop(5, Player("", Kart(1, ""))))
    println()

    game.run()
//    while (true) {
//        game.update()
//    }
}