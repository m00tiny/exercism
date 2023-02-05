object Acronym {

    fun generate(phrase : String) : String
        var acronym = readln().split(' ').mutableListOf()
        var myAcronym = mutableListOf()
        for (i in 0...acronym.size) {
                myAcronym[i] += acronym[i].first()
        }
        println(myAcronym)
}
