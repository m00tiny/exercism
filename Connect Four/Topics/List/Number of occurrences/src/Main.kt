fun solution(strings: List<String>, str: String): Int {
    var count: Int = 0
    for (i in strings) {
        if (i == str) {
            count += 1
        }
    }
    return count
}