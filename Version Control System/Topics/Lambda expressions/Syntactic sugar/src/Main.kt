// You can experiment here, it won’t be checked

fun main(args: Array<String>) {
    fun first(value: Int, func: (Int) -> Int): Int = func(value)
    fun second(func: (Int) -> Int, value: Int): Int = func(value)
    first(5, { i -> i })
    first(5, { it })
    first(5) { it }
    second({ i -> i }, 5)
    second({ it }, 5)
    second(5) { it }
}
