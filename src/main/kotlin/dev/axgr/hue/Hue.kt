package dev.axgr.hue

import com.fasterxml.jackson.core.JsonParser
import com.fasterxml.jackson.databind.DeserializationContext
import com.fasterxml.jackson.databind.JsonDeserializer
import com.fasterxml.jackson.databind.JsonNode
import org.springframework.boot.jackson.JsonComponent
import org.springframework.stereotype.Service
import org.springframework.web.client.RestClient

@Service
class Hue(private val client: RestClient) {

  fun groups(): List<HueResource> {
    return client
      .get()
      .uri("/groups")
      .retrieve()
      .body(HueDictionaryResource::class.java)?.records ?: emptyList()
  }

  fun scenes(): List<HueResource> {
    return client
      .get()
      .uri("/scenes")
      .retrieve()
      .body(HueDictionaryResource::class.java)?.records ?: emptyList()
  }

  fun enable(scene: String, group: String) {
    client
      .put()
      .uri("/groups/$group/action")
      .body(mapOf("scene" to scene))
      .retrieve()
      .toBodilessEntity()
  }
}

data class HueDictionaryResource(val records: List<HueResource>)
data class HueResource(val id: String, val name: String)

@JsonComponent
class HueDictionaryDeserializer : JsonDeserializer<HueDictionaryResource>() {

  override fun deserialize(parser: JsonParser, context: DeserializationContext): HueDictionaryResource {
    val root = parser.codec.readTree<JsonNode>(parser)
    val records = mutableListOf<HueResource>()

    root.fieldNames().forEach { id ->
      val name = root[id]["name"].textValue()
      records.add(HueResource(id, name))
    }

    return HueDictionaryResource(records)
  }
}
