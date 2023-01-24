fun solution(first: List<Int>, second: List<Int>): MutableList<Int> {
    val third: MutableList<Int> = mutableListOf()
    third.addAll(first)
    third.addAll(second)
    return third
}