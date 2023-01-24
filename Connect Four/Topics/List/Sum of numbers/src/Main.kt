fun solution(numbers: List<Int>): Int {
    var solve: Int = 0
    for (index in numbers.indices) {
        solve = solve + numbers[index]
    }
    return solve
}
