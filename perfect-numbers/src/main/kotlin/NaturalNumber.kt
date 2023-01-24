enum class Classification {
    DEFICIENT, PERFECT, ABUNDANT
}

fun classify(naturalNumber: Int): Classification {

    if(naturalNumber < 1) throw RuntimeException()

    val listNum = mutableListOf<Int>()

    for (i in 1 until naturalNumber){
        if(naturalNumber % i == 0){
            listNum.add(i)
        }
    }
    
    return when{
        naturalNumber == listNum.sum() -> Classification.PERFECT
        naturalNumber > listNum.sum() -> Classification.DEFICIENT
        naturalNumber < listNum.sum() -> Classification.ABUNDANT
        else -> throw RuntimeException()
    }

}
