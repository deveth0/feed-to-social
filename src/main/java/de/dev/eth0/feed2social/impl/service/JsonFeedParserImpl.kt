package de.dev.eth0.feed2social.impl.service

import de.dev.eth0.feed2social.config.JsonFeedProperties
import de.dev.eth0.feed2social.impl.model.FeedEntry
import de.dev.eth0.feed2social.service.JsonFeedParser
import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import org.jsoup.safety.Whitelist
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.stereotype.Service
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter


@Service
@EnableConfigurationProperties(JsonFeedProperties::class)
open class JsonFeedParserImpl : JsonFeedParser {

  @Autowired
  lateinit var jsonFeedProperties: JsonFeedProperties

  override fun parseEntries(entries: List<Map<String, Any>>): List<FeedEntry> {
    return entries.mapNotNull { parseEntry(it) }
  }

  private fun parseEntry(entry: Map<String, Any>): FeedEntry? {
    // verify that all required field are in the map
    if (!validateEntry(entry)) {
      return null;
    }
    val date = LocalDateTime.parse(entry[jsonFeedProperties.fields.date] as String, DateTimeFormatter.ISO_OFFSET_DATE_TIME)
    val uri = entry[jsonFeedProperties.fields.uri] as String
    val text = findText(entry)
    val keywords = (entry[jsonFeedProperties.fields.keywords] as? List<String>).orEmpty()
    return FeedEntry(date, uri, text, keywords)
  }

  private fun validateEntry(entry: Map<String, Any>): Boolean {
    return entry.containsKey(jsonFeedProperties.fields.date)
        && entry.containsKey(jsonFeedProperties.fields.uri)
        && entry.containsKey(jsonFeedProperties.fields.keywords)
        && getTextKey(entry) != null
  }

  private fun getTextKey(entry: Map<String, Any>): String? {
    return jsonFeedProperties.fields.text.first {
      entry.containsKey(it) && entry[it] != null
    }
  }

  private fun findText(entry: Map<String, Any>): String {
    val text = (entry[getTextKey(entry)] as String)
    return sanitizeText(text)
  }

  private fun sanitizeText(text: String): String {
    val document = Jsoup.parse(text)
    document.outputSettings(Document.OutputSettings().prettyPrint(false))//makes html() preserve linebreaks and spacing
    document.select("br").append("\\n")
    document.select("p").prepend("\\n")
    val s = document.html().replace("\\n", " ")
    return Jsoup.clean(s, "", Whitelist.none(), Document.OutputSettings().prettyPrint(false))
  }
}