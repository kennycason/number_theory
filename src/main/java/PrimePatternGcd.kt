import java.awt.Color
import java.awt.image.BufferedImage
import java.io.File
import javax.imageio.ImageIO

/**
 * Created by kenny on 7/6/16.
 */
fun main(args: Array<String>) {
    PrimePatternGcd().run()
}

class PrimePatternGcd() {
    fun run() {
        val width = 10000
        val height = 10000
        val grid: BufferedImage = BufferedImage(width, height, BufferedImage.TYPE_INT_RGB)
        val graphics = grid.graphics!!

        graphics.color = Color.BLACK
        graphics.fillRect(0, 0, width, height)

        // draw factors

        (0 .. height).forEach next@ { i ->
            var y = 0
            do {
                graphics.color = Color.WHITE
                graphics.drawLine(y, i, y + i, i)
//                println("$y, $i, ${y + i}, $i")

                // add vertical line until greatest factor is found, or is prime
                if (y == 0 && i < height) {
                    for (y2 in (i - 2 downTo 0 step 1)) {
                        if (grid.getRGB(i, y2) == Color.BLACK.rgb) {
                            // hit white, not prime
                           // println("hit ${i} ${y2}")
                            graphics.color = Color.GREEN
                          //  graphics.drawLine(i, i - 1, i, y2)
                            break
                        }
                        else if (y2 == 0
                                && grid.getRGB(i, y2) == Color.WHITE.rgb) {
                            println("prime ${i + 1}")
                            graphics.color = Color.BLACK
                            graphics.drawLine(i, i - 1, i, y2)
                        }
                    }
                }

                y += i + 2

                if (y >= width) { break; }
            } while (true)
        }


        ImageIO.write(grid, "png", File("output/number_factors_with_primes_only_black_$width.png"))
    }
}
