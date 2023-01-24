fun main(args: Array<String>) {
    if (args.size < 3) {
        println("first")
        println("second")
        println("third")
    } else {
        for (arg in args) {
            println(arg)
        }
    }
}
