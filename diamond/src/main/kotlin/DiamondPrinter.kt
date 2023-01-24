import java.lang.StringBuilder

class DiamondPrinter {
    fun printToList(c: Char): List<String> {
        val charList = ('A'..c).toList()

        val top = charList.mapIndexed { index, c ->
            if(index == 0){
                return@mapIndexed StringBuilder()
                        .append(sideSpace(charList.size, index))
                        .append(c)
                        .append(sideSpace(charList.size, index))
                        .toString()
            }

            StringBuilder()
                    .append(sideSpace(charList.size, index))
                    .append(c)
                    .append(centerSpace(index))
                    .append(c)
                    .append(sideSpace(charList.size, index))
                    .toString()
        }

        val bottom = top.dropLast(1).reversed()

        return top + bottom
    }

    private fun sideSpace(listSize: Int, index: Int) = " ".repeat(listSize - (index+1) )

    private fun centerSpace(index: Int) =  " ".repeat( index + (index-1) )

}
