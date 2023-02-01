import java.util.Scanner

fun main() {
    val scanner = Scanner(System.`in`)
    var maxNum = scanner.nextInt()
    var pos = 1
    var maxPos = 1

    while (scanner.hasNextInt()) {
        pos++
        val nextNum = scanner.nextInt()
        if (nextNum > maxNum) {
            maxNum = nextNum
            maxPos = pos
        }
    }

    print("$maxNum $maxPos")
}