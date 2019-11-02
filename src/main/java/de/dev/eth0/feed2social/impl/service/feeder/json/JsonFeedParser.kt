/*
 * Copyright (c) 2019 dev-eth0.de All rights reserved.
 */

package de.dev.eth0.feed2social.impl.service.feeder.json

import de.dev.eth0.feed2social.impl.model.FeedEntry
import de.dev.eth0.feed2social.impl.service.feeder.AbstractFeedParser
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.stereotype.Service
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

@Service
@ConditionalOnProperty("feed.json.url")
@EnableConfigurationProperties(JsonFeedProperties::class)
open class JsonFeedParser : AbstractFeedParser<Map<String, Any>>() {

  @Autowired
  lateinit var jsonFeedProperties: JsonFeedProperties

  override fun parseEntry(entry: Map<String, Any>): FeedEntry {
    val date = getEntryDate(entry)
    val uri = entry[jsonFeedProperties.fields.uri] as String
    val text = findText(entry)
    val keywords = (entry[jsonFeedProperties.fields.keywords] as? List<String>).orEmpty()
    return createFeedEntry(date, uri, text, keywords);
  }

  override fun getEntryDate(entry: Map<String, Any>): LocalDateTime {
    return LocalDateTime.parse(entry[jsonFeedProperties.fields.date] as String, DateTimeFormatter.ISO_OFFSET_DATE_TIME)
  }

  override fun validateEntry(entry: Map<String, Any>): Boolean {
    return entry.containsKey(jsonFeedProperties.fields.date)
        && entry.containsKey(jsonFeedProperties.fields.uri)
        && entry.containsKey(jsonFeedProperties.fields.keywords)
        && getTextKey(entry) != null
  }

  private fun findText(entry: Map<String, Any>): String {
    return (entry[getTextKey(entry)] as String)
  }

  private fun getTextKey(entry: Map<String, Any>): String? {
    return jsonFeedProperties.fields.text.first {
      entry.containsKey(it) && entry[it] != null
    }
  }
}