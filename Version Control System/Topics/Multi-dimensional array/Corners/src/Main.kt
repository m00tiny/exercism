fun main() {
    //Do not touch code below
    var inputArray: Array<Array<String>> = arrayOf()
    val n = readLine()!!.toInt()
    for (i in 0 until n) {
        val strings = readLine()!!.split(' ').toTypedArray()
        inputArray += strings
    }
    //write your code here
    println(inputArray[0][0] + " " + inputArray[0][n - 1])
    println(inputArray[n - 1][0] + " " + inputArray[n - 1][n - 1])
}