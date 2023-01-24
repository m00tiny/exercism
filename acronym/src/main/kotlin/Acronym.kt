object Acronym {

    fun generate(phrase : String) : String
            = phrase.replace("-", " ")
            .split(" ")
            .filter { it.isNotEmpty() }
            .map { it.first() }
            .joinToString("")
            .toUpperCase()
}
