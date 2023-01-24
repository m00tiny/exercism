package connectfour

const val WINNING_LENGTH = 4

class Player(val name: String, val token: Char, var score: Int = 0)

class GameState(val player1: Player, val player2: Player, val noOfGames: Int)

fun main() {
    println("Connect Four")

    println("First player's name:")
    val firstPlayer = readln()

    println("Second player's name:")
    val secondPlayer = readln()

    val player1 = Player(firstPlayer, 'o')
    val player2 = Player(secondPlayer, '*')

    val (rows, cols) = getBoardDimensions()

    val noOfGames = getValidNumberOfGames()

    val gameState = GameState(player1, player2, noOfGames)

    println("${player1.name} VS ${player2.name}")
    println("$rows X $cols board")
    if (noOfGames == 1) {
        println("Single game")
    } else {
        println("Total $noOfGames games")
    }

    repeat (noOfGames) {
        playSingleGame(it, gameState, rows, cols)
    }

    println("Game over!")
}

fun playSingleGame(gameNumber: Int, gameState: GameState, rows: Int, cols: Int) {
    val board = List(cols) { MutableList(rows) { ' ' } }

    if (gameState.noOfGames > 1) {
        println("Game #${gameNumber + 1}")
    }
    printBoard(board)

    var activePlayer = if (gameNumber % 2 == 0) gameState.player1 else gameState.player2

    while (true) {
        println("${activePlayer.name}'s turn:")

        val move = readln()
        if (move == "end") {
            println("Game over!")
            return
        } else if (!isNumber(move)) {
            println("Incorrect column number")
            continue
        } else {
            val colChoice = move.toInt()
            if (colChoice !in 1..cols) {
                println("The column number is out of range (1 - $cols)")
                continue
            }
            if (indexOfFreeRowInColumn(board, colChoice - 1)  < 0) {
                println("Column $colChoice is full")
                continue
            }

            board[colChoice - 1][ indexOfFreeRowInColumn(board, colChoice - 1)] = activePlayer.token

            printBoard(board)

            if (checkBoardForWin(board, activePlayer.token)) {
                activePlayer.score += 2

                println("Player ${activePlayer.name} won")
                printScore(gameState)
                return
            }

            val boardHasNoEmptyCell = board.all { chars -> chars.all { c -> c != ' ' } }
            if (boardHasNoEmptyCell) {
                gameState.player1.score++
                gameState.player2.score++

                println("It is a draw")
                printScore(gameState)
                return
            }

            activePlayer = swapActivePlayer(activePlayer, gameState)
        }
    }
}

fun printScore(gameState: GameState) {
    println("Score")
    println("${gameState.player1.name}: ${gameState.player1.score} ${gameState.player2.name}: ${gameState.player2.score}")
}

fun getValidNumberOfGames(): Int {
    while (true) {
        println("Do you want to play single or multiple games?")
        println("For a single game, input 1 or press Enter")
        println("Input a number of games:")

        val input = readln()
        if (input.isEmpty()) {
            return 1
        }
        if (!isNumber(input)) {
            println("Invalid input")
            continue
        }

        val noOfGames = input.toInt()
        if (noOfGames < 1) {
            println("Invalid input")
            continue
        }

        return noOfGames
    }
}

fun indexOfFreeRowInColumn(board: List<List<Char>>, col: Int): Int {
    return board[col].indexOfFirst { it == ' ' }
}

fun checkBoardForWin(board: List<List<Char>>, token: Char): Boolean {
    if (checkColsForWin(board, token)) {
        return true
    }

    if (checkRowsForWin(board, token)) {
        return true
    }

    if (checkDiagForWin(board, token)) {
        return true
    }

    if (checkDiagsForWin(board, token)) {
        return true
    }

    return false
}

fun checkColsForWin(board: List<List<Char>>, token: Char): Boolean {
    for (c in board.indices) {
        val column = board[c]
        for (r in 0..column.size - WINNING_LENGTH) {
            val cells = listOf(
                checkSlot(board, c, r),
                checkSlot(board, c, r + 1),
                checkSlot(board, c, r + 2),
                checkSlot(board, c, r + 3))

            if (cells.all { it == token }) {
                return true
            }
        }
    }
    return false
}

fun checkRowsForWin(board: List<List<Char>>, token: Char): Boolean {
    for (c in 0 .. board.size - WINNING_LENGTH) {
        val column = board[c]
        for (r in column.indices) {
            val cells = listOf(
                checkSlot(board, c, r),
                checkSlot(board, c + 1, r),
                checkSlot(board, c + 2, r),
                checkSlot(board, c + 3, r))

            if (cells.all { it == token }) {
                return true
            }
        }
    }
    return false
}

fun checkDiagForWin(board: List<List<Char>>, token: Char): Boolean {
    for (c in 0 .. board.size - WINNING_LENGTH) {
        val column = board[c]
        for (r in 0 .. column.size - WINNING_LENGTH) {
            val cells = listOf(
                checkSlot(board, c, r),
                checkSlot(board, c + 1, r + 1),
                checkSlot(board, c + 2, r + 2),
                checkSlot(board, c + 3, r + 3))

            if (cells.all { it == token }) {
                return true
            }
        }
    }
    return false
}

fun checkDiagsForWin(board: List<List<Char>>, token: Char): Boolean {
    for (c in 0 .. board.size - WINNING_LENGTH) {
        val column = board[c]
        for (r in WINNING_LENGTH - 1 until column.size) {
            val cells = listOf(
                checkSlot(board, c, r),
                checkSlot(board, c + 1, r - 1),
                checkSlot(board, c + 2, r - 2),
                checkSlot(board, c + 3, r - 3))

            if (cells.all { it == token }) {
                return true
            }
        }
    }
    return false
}

fun checkSlot(board: List<List<Char>>, col: Int, row: Int): Char {
    return board[col][row]
}

fun isNumber(s: String): Boolean {
    return Regex("\\d+").matches(s)
}

fun swapActivePlayer(player: Player, gameState: GameState): Player {
    return if (player == gameState.player1) gameState.player2 else gameState.player1
}

fun getBoardDimensions(): Pair<Int, Int> {
    println("Set the board dimensions (Rows x Columns)")
    println("Press Enter for default (6 x 7)")

    val regex = """\s*(\d)+\s*[xX]\s*(\d)+\s*""".toRegex()
    val userEntryForDimensions = readln()

    val (row, col) = when {
        regex.matches(userEntryForDimensions) -> regex.find(userEntryForDimensions)!!.destructured.toList().map { s -> s.toInt() }
        userEntryForDimensions.isEmpty() -> listOf(6, 7)
        else -> {
            println("Invalid input")
            return getBoardDimensions()
        }
    }

    if (row !in 5..9) {
        println("Board rows should be from 5 to 9")
        return getBoardDimensions()
    }

    if (col !in 5..9) {
        println("Board columns should be from 5 to 9")
        return getBoardDimensions()
    }

    return Pair(row, col)
}

fun printBoard(board: List<List<Char>>) {
    val noOfCols = board.size
    val noOfRows = board[0].size

    println(" " + (1..noOfCols).joinToString(" "))

    repeat (noOfRows) { row ->
        print("║")
        repeat (noOfCols) { col ->
            print("" + checkSlot(board, col, noOfRows - row - 1) + "║")
        }
        println()
    }

    val bottomRow = CharArray(noOfCols) { '═' }.joinToString("╩")
    println("╚$bottomRow╝")
}