package presto.query.extractor.output

import com.facebook.presto.sql.tree.ComparisonExpression
import kotlinx.serialization.Serializable

class ComparisonExpressionOutputBuilder(private val comparisonExpression: ArrayList<ComparisonExpression>) {
    fun build(): ComparisonExpressionOutput {
        val serializableComparisonExpressions = ArrayList<SerializableComparisonExpression>()
        comparisonExpression.forEach { e ->
            val serializableComparisonExpression =
                SerializableComparisonExpression(e.left.toString(), e.right.toString())
            serializableComparisonExpressions.add(serializableComparisonExpression)
        }
        return ComparisonExpressionOutput(serializableComparisonExpressions)
    }
}

@Serializable
data class SerializableComparisonExpression(
    val left: String,
    val right: String
)

@Serializable
data  class ComparisonExpressionOutput(
    val comparisonExpressions: ArrayList<SerializableComparisonExpression>
    )