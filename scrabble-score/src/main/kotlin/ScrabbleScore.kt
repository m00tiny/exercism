object ScrabbleScore {
    fun scoreWord(input: String): Int {
        var point = 0

        val listOfPointOne = listOf('A', 'E', 'I', 'O', 'U', 'L', 'N', 'R', 'S', 'T')
        val listOfPointTwo = listOf('D', 'G')
        val listOfPointThree = listOf('B', 'C', 'M', 'P')
        val listOfPointFour = listOf('F', 'H', 'V', 'W', 'Y')
        val listOfPointFive = listOf('K')
        val listOfPointEight = listOf('J', 'X')
        val listOfPointTen = listOf('Q', 'Z')

        input.toCharArray().map {
            when (it.toUpperCase()) {
                in listOfPointOne -> point += 1
                in listOfPointTwo -> point += 2
                in listOfPointThree -> point += 3
                in listOfPointFour -> point += 4
                in listOfPointFive -> point += 5
                in listOfPointEight -> point += 8
                in listOfPointTen -> point += 10
            }
        }

        return point
    }
}
