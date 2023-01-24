fun main() {
    val gravity: Double = 9.8
    val densityOfFluid = readln().toDouble()
    val heightOfColumn = readln().toDouble()
    val liquidPressure: Double = gravity * densityOfFluid * heightOfColumn
    println(liquidPressure)
}