package game

import com.sun.org.apache.xpath.internal.operations.Bool
import game.effect.Effect
import lib.StringPainter
import lib.StringPainter.BackgroundColor.BG_YELLOW
import lib.StringPainter.TextColor.TXT_BLACK
import lib.StringPainter.TextColor.TXT_YELLOW
import lib.StringPainter.paint
import kotlin.random.Random

class GameMap(
    private val players: List<Player>,
    val size: Int = 30,
    luckCellPercentage: Int = 25
) {

    val cells: Array<Cell> = (0..size).mapIndexed { index, _ ->
        if(index == 0) {
            Cell(index, " * ")
        }else if(index == size) {
            Cell(index, "|#|")
        }else if(Random.nextInt(0, 100) > (100 - luckCellPercentage)){
            Cell(index, paint(" ? ", TXT_YELLOW), true)
        }else{
            Cell(index, " * ")
        }
    }.toTypedArray()

    fun render() {
        cells.forEach { _ -> print("---") }
        println()
        players.forEach {
            for(cell in cells) {
                if(it.position() == cell.index) {
                    print(it.render())
                }else{
                    print(cell.render())
                }
            }
            println()
        }
        cells.forEach { _ -> print("---") }
        println()
    }

    open class Cell(
        val index: Int,
        val graphics: String,
        val isSurprise: Boolean = false,
        var effect: Effect? = null
    ) {
        fun render(): String {
            return effect?.icon ?: graphics
        }


    }


}