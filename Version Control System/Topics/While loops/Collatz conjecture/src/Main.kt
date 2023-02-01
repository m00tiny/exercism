fun main() {
    var n = readln().toInt()
    while (n != 1) {
        if (n % 2 == 0) {
            print(n.toString() + " ")
            n /= 2
        } else {
            print(n.toString() + " ")
            n *= 3
            n += 1
        }
    }
    print(n)
}