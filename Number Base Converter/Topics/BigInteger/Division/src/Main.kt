import java.math.BigInteger

fun main() {
    val a = readln().toBigInteger()
    val b = readln().toBigInteger()
    val (quotient, remainder) = a.divideAndRemainder(b)
    println("${a} = ${b} * ${quotient} + ${remainder}")
}