fun main() {
    val firstArray = readLine()!!.split(' ').map { it }.toTypedArray()
    val secondArray = readLine()!!.split(' ').map { it }.toTypedArray()
    // do not touch the lines above
    // write your code here
    var arrayArray: Array<String> = emptyArray()
    arrayArray += firstArray
    arrayArray += secondArray
    println(arrayArray.joinToString())
}