package svcs
import java.io.File

fun main(args: Array<String>) {
    if (!File("vcs/").isDirectory) {
        File("vcs/").mkdir()
        File("vcs/config.txt").createNewFile()
        File("vcs/index.txt").createNewFile()
    }
    if (args.isEmpty()) {
        main()
    } else {
        when (args[0]) {
            "config" -> {
                if (args.size == 2) {
                    config(args[1])
                } else {
                    config()
                }
            }
            "--help" -> {
                main()
            }
            "add" -> {
                if (args.size == 1 && File("vcs/index.txt").readLines().isEmpty()) {
                    File("vcs/index.txt").writeText("Tracked Files:\n")
                    println("Add a file to the index.")
                } else if (args.size == 1 && File("vcs/index.txt").readLines().isNotEmpty()) {
                    for (line in File("vcs/index.txt").readLines()) {
                        println(line)
                    }
                } else if (args.size == 2 && !File(args[1]).exists()) {
                        println("Can't find '${args[1]}'.")
                } else if (args[1] in File("vcs/index.txt").readText()) {
                        println("The file '${args[1]}' is tracked.")
                } else {
                    if (args[1] !in File("vcs/index.txt").readText()) {
                        File("vcs/index.txt").appendText(args[1] + "\n")
                        println("The file '${args[1]}' is tracked.")
                    }
                }
            }
            "log" -> {
                println("Show commit logs.")
            }
            "commit" -> {
                println("Save changes.")
            }
            "checkout" -> {
                println("Restore a file.")
            }
            else -> {
                println("'" + args[0] + "' is not a SVCS command.")
            }
        }
    }
}

fun main() {
    println("These are SVCS commands:")
    println("config     Get and set a username.")
    println("add        Add a file to the index.")
    println("log        Show commit logs.")
    println("commit     Save changes.")
    println("checkout   Restore a file.")
}

fun config(userName: String = "") {
    if (userName == "" && File("vcs/config.txt").readText() == "") {
        println("Please, tell me who you are.")
        val newUsername = readln()
        File("vcs/config.txt").writeText(newUsername)
        println("The username is $newUsername.")
    } else if (userName == "" && File("vcs/config.txt").readText() != "") {
        val newUserName = File("vcs/config.txt").readText()
        println("The username is $newUserName.")
    } else if (userName != "") {
        File("vcs/config.txt").writeText(userName)
        println("The username is $userName.")
    }
}