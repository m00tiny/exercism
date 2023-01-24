import java.util.Scanner

fun swapInts(ints: IntArray): IntArray {
    var swappedInts = intArrayOf(ints[1], ints[0])
    return swappedInts
}

fun main() {
    val scanner = Scanner(System.`in`)
    while (scanner.hasNextLine()) {
        var ints = intArrayOf(
            scanner.nextLine().toInt(),
            scanner.nextLine().toInt(),
        )
        println(ints[1])
        println(ints[0])
        swapInts(ints)
    }
}