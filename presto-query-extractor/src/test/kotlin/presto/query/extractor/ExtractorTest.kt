package presto.query.extractor

import presto.query.extractor.output.*
import java.io.File
import kotlin.test.Test

class ExtractorTest {
    @Test
    fun testGetTables() {
        val query = File("src/test/resources/01.sql").readText()
        val element = Element.Table
        val extractor = Extractor(query, element)
        assert(extractor.getTables() == arrayListOf("t"))
    }

    @Test
    fun testGetComparisonExpressions() {
        val query = File("src/test/resources/02.sql").readText()
        val element = Element.ComparisonExpression
        val extractor = Extractor(query, element)
        println(extractor.getComparisonExpressions().toString() == "[(region.regionkey = nation.regionkey)]")
    }

    @Test
    fun testGetInPredicates() {
        val query = File("src/test/resources/03.sql").readText()
        val element = Element.InPredicate
        val extractor = Extractor(query, element)
        assert(extractor.getInPredicates().toString() == "[(Country IN ('Germany', 'France', 'UK'))]")
    }
}