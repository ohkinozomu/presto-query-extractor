package presto.query.extractor.output

import kotlinx.serialization.Serializable

@Serializable
data class TableOutput (private val tables: ArrayList<String>) {
}