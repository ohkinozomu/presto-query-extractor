package presto.query.extractor.output

import com.facebook.presto.sql.tree.InPredicate
import kotlinx.serialization.Serializable

class InPredicatesOutputBuilder(private val inPredicates: ArrayList<InPredicate>) {
    fun build(): InPredicatesOutput {
        val serializableInPredicates = ArrayList<SerializableInPredicate>()
        inPredicates.forEach { e ->
            val serializableInPredicate =
                SerializableInPredicate(e.value.toString(), e.valueList.toString())
            serializableInPredicates.add(serializableInPredicate)
        }
        return InPredicatesOutput(serializableInPredicates)
    }
}

@Serializable
data class SerializableInPredicate(
    val value: String,
    val valueList: String
)

@Serializable
data  class InPredicatesOutput(
    val inPredicates: ArrayList<SerializableInPredicate>
)