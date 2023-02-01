import java.util.Scanner

fun main() {
    val scanner = Scanner(System.`in`)
    var balance = readln().toInt()
    while (scanner.hasNextInt()) {
        val overdraft = scanner.nextInt()
        if (overdraft <= balance) {
            balance = balance - overdraft
        } else if (overdraft > balance) {
            return(println("Error, insufficient funds for the purchase. Your balance is $balance, but you need $overdraft."))
        }
    }
    println("Thank you for choosing us to manage your account! Your balance is $balance.")
}