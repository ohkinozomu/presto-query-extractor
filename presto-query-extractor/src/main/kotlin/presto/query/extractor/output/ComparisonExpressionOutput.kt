package presto.query.extractor.output

import com.facebook.presto.sql.tree.ComparisonExpression
import kotlinx.serialization.Serializable

class ComparisonExpressionOutputBuilder(private val comparisonExpression: ArrayList<ComparisonExpression>) {
    fun build(): ComparisonExpressionOutput {
        val serializableComparisonExpressions = ArrayList<SerializableComparisonExpression>()
        comparisonExpression.forEach { e ->
            val serializableComparisonExpression =
                SerializableComparisonExpression(e.right.toString(), e.left.toString())
            serializableComparisonExpressions.add(serializableComparisonExpression)
        }
        return ComparisonExpressionOutput(serializableComparisonExpressions)
    }
}

@Serializable
data class SerializableComparisonExpression(
    val right: String,
    val left: String
)

@Serializable
data  class ComparisonExpressionOutput(
    val comparisonExpressions: ArrayList<SerializableComparisonExpression>
    )