fun main() {
    val number = readln().toInt()
    println(round(number))
}


fun round(number: Int): Int {
    return if (number >= 1000) 0 else number
}