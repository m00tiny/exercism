object SpiralMatrix {

    private var count = 1

    fun ofSize(n: Int): Array<IntArray> {
        val matrix = Array(n, { IntArray(n) })
        var indices = (0 until n)
        count = 1

        while (!indices.isEmpty()) {
            paintEdges(matrix, indices)
            indices = indices.dropFirstAndLast()
        }

        return matrix
    }

    private fun paintEdges(matrix: Array<IntArray>, indexRange: IntRange = (0 until matrix.size)) {
        val first = indexRange.first
        val last = indexRange.last
        (first..last)
                .forEach { matrix[first][it] = count++ }

        (first + 1..last)
                .forEach { matrix[it][last] = count++ }

        (last - 1 downTo first)
                .forEach { matrix[last][it] = count++ }

        (last - 1 downTo first + 1)
                .forEach { matrix[it][first] = count++ }
    }
}

fun IntRange.dropFirstAndLast(): IntRange {
    val elements = this.drop(1).dropLast(1)
    return when {
        elements.isEmpty() -> IntRange.EMPTY
        else -> IntRange(elements.first(), elements.last())
    }
}
