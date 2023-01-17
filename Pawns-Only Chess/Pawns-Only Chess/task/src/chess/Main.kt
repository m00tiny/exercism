var inp = ""
var list : MutableList<MutableList<String>> = MutableList(8) { MutableList(10) { " " } }
val l = listOf(' ', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', ' ')
val regex = Regex("""[a-h][1-8][a-h][1-8]""")
var turn = 1
var enPassantPermission = false


fun main() {
    list[1] = MutableList(10) { "W" }
    list[6] = MutableList(10) { "B" }
    println("Pawns-Only Chess")
    println("First Player's name:")
    val n1 = readln()
    println("Second Player's name:")
    val n2 = readln()
    board(list)
    gameLoop(n1, n2, regex, list)
}
fun gameLoop(n1 :String, n2 :String, regex: Regex, list: MutableList<MutableList<String>>) {
    loop@while(true) {
        if (turn == 1) println("$n1's turn:") else println("$n2's turn:")
        inp = readln()
        if (inp == "exit") break
        val v1 = inp[1].digitToInt()
        val v2 = inp[3].digitToInt()
        val h1 = inp[0]
        val h2 = inp[2]

        if (!inp.matches(regex)) { println("Invalid Input");continue@loop }

        if (turn == 1) {
            if (list[v1 - 1][l.indexOf(h1)] != "W") {//for No White Pawn
                println("Invalid Input") ;continue@loop
                //println("No White Pawn At ${inp.substring(0, 2)}") ;continue@loop
            }

            if (!enPassant(v1, v2, h1, h2) && !forward(v1, v2, h1, h2) && !capture(v1, v2, h1, h2)) {
                println("Invalid Input");continue@loop
            }
            enPassantActivation(v1, v2, h1, h2)
            if (winCondition(v1, v2, h1, h2)) {
                break
            }
        }
        if (turn == 2) {
            if (list[v1 - 1][l.indexOf(h1)] != "B") {//for No Black Pawn
                println("No Black Pawn At ${inp.substring(0, 2)}");continue@loop
            }
            if (!enPassant(v1, v2, h1, h2) && !forward(v1, v2, h1, h2) && !capture(v1, v2, h1, h2)) {
                println("Invalid Input");continue@loop
            }
            enPassantActivation(v1, v2, h1, h2)
            if (winCondition(v1, v2, h1, h2)) {
                break
            }
        }
        board(list)
        if (staleMate()) break
        turn = if (turn == 1) 2 else 1
    }
    println("Bye!")
}

fun staleMate(): Boolean {
    var remainingPawn = 0
    var blockedPawn = 0
    for (i in list.lastIndex downTo 0) {
        if (turn == 1) remainingPawn += list[i].count { it == "B" }
        if (turn == 2) remainingPawn += list[i].count { it == "W" }
        for (j in list[i].lastIndex downTo 0) {
            if (turn == 1 && list[i][j] == "B" && list[i - 1][j] == "W" && list[i - 1][j - 1] != "W"
                && list[i -1][j + 1] != "W") {
                ++blockedPawn
            }
            if (turn == 2 && list[i][j] == "W" && list[i + 1][j] == "B" && list[i + 1][j - 1] != "B"
                && list[i + 1][j + 1] != "B") {
                ++blockedPawn
            }
        }
    }

    if (blockedPawn == remainingPawn) {
        println("Stalemate!")
        return true
    }

    println("remainingPawn = $remainingPawn")
    println("blockedPawn = $blockedPawn")
    return false
}

fun winCondition(v1: Int, v2: Int, h1: Char, h2: Char): Boolean {
    when (turn) {
        1 -> if (list[7].contains("W") || !list.any { it.contains("B") }) { //if(row 8 contains "W" or list doesn't contain any "B"
            board(list)
            println("White Wins!")
            return true
        }
        2 -> if (list[0].contains("B") || !list.any { it.contains("W") }) {
            board(list)
            println("Black Wins!")
            return true
        }
    }
    return false
}

fun enPassant(v1: Int, v2: Int, h1: Char, h2: Char): Boolean {
    var e = false
    when (turn) {
        1 -> if (enPassantPermission && list[v2 - 2][l.indexOf(h2)] == "B") {
            list[v1 - 1][l.indexOf(h1)] = " "//for move
            list[v2 - 1][l.indexOf(h2)] = "W"//for move
            list[v2 - 2][l.indexOf(h2)] = " "//for capture
            e = true
        }
        else if (enPassantPermission && list[v2 - 2][l.indexOf(h2)] != "B") {
            enPassantPermission = false
        }
        2 -> if (enPassantPermission && list[v2][l.indexOf(h2)] == "W") {
            list[v1 - 1][l.indexOf(h1)] = " "
            list[v2 - 1][l.indexOf(h2)] = "B"
            list[v2][l.indexOf(h2)] = " "
            e = true
        }
        else if (enPassantPermission && list[v2 - 2][l.indexOf(h2)] != "W") {
            enPassantPermission = false
        }
    }
    return e
}

fun enPassantActivation(v1: Int, v2: Int, h1: Char, h2: Char): Boolean {
    var e = false
    when (turn) {
        1 -> if (list[v2 - 1][l.indexOf(h2) - 1] == "B" || list[v2 - 1][l.indexOf(h2) + 1] == "B") {
            enPassantPermission = true
            e = true
        }
        2 -> if (list[v2 - 1][l.indexOf(h2) - 1] == "W" || list[v2 - 1][l.indexOf(h2) + 1] == "W") {
            enPassantPermission = true
            e = true
        }
    }
    return e
}

fun capture(v1: Int, v2: Int, h1: Char, h2: Char): Boolean {
    var e = false
    when (turn) {
        1 -> if (list[(v2 - 1) ][l.indexOf(h2)] == "B" && v2 == v1 + 1 && (h2 == h1 + 1 || h2 == h1 - 1)) {
            list[v1 - 1][l.indexOf(h1)] = " "
            list[v2 - 1][l.indexOf(h2)] = "W"
            e = true
        }
        2 -> if (list[v2 - 1][l.indexOf(h2)] == "W" && v2 == v1 - 1 && (h2 == h1 + 1 || h2 == h1 - 1)) {
            list[v1 - 1][l.indexOf(h1)] = " "
            list[v2 - 1][l.indexOf(h2)] = "B"
            e = true
        }
    }
    return e
}

fun board(list: MutableList<MutableList<String>>) {
    val rows = "  +---+---+---+---+---+---+---+---+"
    println(rows)
    for (x in list.lastIndex downTo 0) {
        list[x][0] = "${x + 1}"
        list[x][9] = " "
        println(list[x].subList(0, 9).joinToString(" | ") + " | ")
        println(rows)
    }
    println(l.joinToString("   "))
}

fun forward(v1: Int, v2: Int, h1: Char, h2: Char): Boolean {
    var e = false
    when (turn){
        1 -> if (v1 == 2 && v2 <= v1 + 2 && h1 == h2 && v1 < v2 && (list[v2 - 1][l.indexOf(h2)] != "B") ||
            v1 != 2 && v2 == v1 + 1 && h1 == h2 && v1 < v2 && (list[v2 - 1][l.indexOf(h2)] != "B") ) {
            list[v1 - 1][l.indexOf(h1)] = " "
            list[v2 - 1][l.indexOf(h2)] = "W"
            e = true
        }
        2 -> if (v1 == 7 && v2 >= v1 - 2 && h1 == h2 && v1 > v2 && (list[v2 - 1][l.indexOf(h2)] != "W") ||
            v1 != 7 && v2 == v1 - 1 && h1 == h2 && v1 > v2 && (list[v2 - 1][l.indexOf(h2)] != "W")) {
            list[v1 - 1][l.indexOf(h1)] = " "
            list[v2 - 1][l.indexOf(h2)] = "B"
            e = true
        }

    }
    return e
}