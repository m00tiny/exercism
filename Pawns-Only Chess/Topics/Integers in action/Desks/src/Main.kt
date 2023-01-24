fun main() {
    var a = readln().toInt()
    var b = readln().toInt()
    var c = readln().toInt()
    if (a % 2 == 1) {
        a += 1
    }
    if (b % 2 == 1) {
        b += 1
    }
    if (c % 2 == 1) {
        c += 1
    }
    print((a/2) + (b/2) + (c/2))
}