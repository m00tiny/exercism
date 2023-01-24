fun main() {
    val beginTimeHr = readln().toInt() //hour
    val beginTimeMin = readln().toInt() //minutes
    val beginTimeSec = readln().toInt() //seconds
    val endTimeHr = readln().toInt()
    val endTimeMin = readln().toInt()
    val endTimeSec = readln().toInt()
    val startTime: Int = beginTimeSec + (beginTimeMin * 60) + (beginTimeHr * 3600)
    val finishTime: Int = endTimeSec + (endTimeMin * 60) + (endTimeHr * 3600)
    println(finishTime - startTime)
}