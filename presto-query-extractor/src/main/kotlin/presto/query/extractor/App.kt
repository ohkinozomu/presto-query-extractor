package presto.query.extractor

import kotlinx.cli.ArgParser
import kotlinx.cli.ArgType
import kotlinx.cli.default
import kotlinx.cli.required
import java.io.File
import kotlin.system.exitProcess

class App {}

enum class Element {
    Table,
    ComparisonExpression,
    InPredicate
}

fun main(args: Array<String>) {
    val parser = ArgParser("presto-query-extractor")
    val file by parser.option(ArgType.String, shortName = "f", description = "SQL file").default("")
    var query by parser.option(ArgType.String, shortName = "q", description = "Query string").default("")
    val element by parser.option(ArgType.Choice<Element>(), shortName = "e", description = "Element to extract").required()
    parser.parse(args)

    if (file == "" && query == "") {
        print("Input file or query")
        exitProcess(1)
    }

    if (file != "" && query != "") {
        print("Input only one of file and query")
        exitProcess(1)
    }

    if (file != "") {
        query = File(file).readText()
    }

    val extractor = Extractor(query, element)
    extractor.run()
}