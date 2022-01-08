package presto.query.extractor

import com.facebook.presto.sql.parser.SqlParser
import com.facebook.presto.sql.tree.*
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.*
import com.facebook.presto.sql.parser.ParsingOptions
import presto.query.extractor.output.*
import java.util.*


class Extractor(queryStr: String, private val element: Element) {
    private val parser = SqlParser()
    private val statement = parser.createStatement(queryStr, ParsingOptions())

    fun getTables(): ArrayList<String> {
        val tables = ArrayList<String>()
        object : DefaultTraversalVisitor<Void?, Void?>() {
            override fun visitTable(node: Table?, context: Void?): Void? {
                if (node != null) {
                    tables.add(node.name.toString())
                }
                return null
            }
        }.process(statement, null)
        return tables
    }

    fun getComparisonExpressions(): ArrayList<ComparisonExpression> {
        val comparisonExpressions = ArrayList<ComparisonExpression>()
        object : DefaultTraversalVisitor<Void?, Void?>() {
            override fun visitComparisonExpression(node: ComparisonExpression?, context: Void?): Void? {
                if (node != null) {
                    comparisonExpressions.add(node)
                }
                return null
            }
        }.process(statement, null)
        return comparisonExpressions
    }

    fun getInPredicates(): ArrayList<InPredicate> {
        val inPredicates = ArrayList<InPredicate>()
        object : DefaultTraversalVisitor<Void?, Void?>() {
            override fun visitInPredicate(node: InPredicate?, context: Void?): Void? {
                if (node != null) {
                    inPredicates.add(node)
                }
                return null
            }
        }.process(statement, null)
        return inPredicates
    }

    fun run() {
        when (element) {
            Element.Table -> {
                val tables = getTables()
                println(Json{ prettyPrint = true }.encodeToString(TableOutput.serializer(), TableOutput(tables)))
            }
            Element.ComparisonExpression -> {
                val comparisonExpressions = getComparisonExpressions()
                println(Json{ prettyPrint = true }.encodeToString(ComparisonExpressionOutput.serializer(), ComparisonExpressionOutputBuilder(comparisonExpressions).build()))
            }
            Element.InPredicate -> {
                val inPredicates = getInPredicates()
                println(Json{ prettyPrint = true }.encodeToString(InPredicatesOutput.serializer(), InPredicatesOutputBuilder(inPredicates).build()))
            }
        }
    }
}