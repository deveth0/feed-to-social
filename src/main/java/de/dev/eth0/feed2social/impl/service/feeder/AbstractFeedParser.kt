package de.dev.eth0.feed2social.impl.service.feeder

import de.dev.eth0.feed2social.impl.model.FeedEntry
import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import org.jsoup.safety.Whitelist
import java.time.LocalDateTime

/**
 * Abstract parser for feeds
 */
abstract class AbstractFeedParser<T> {

  fun parseEntries(entry: List<T>, lastRun: LocalDateTime): List<FeedEntry> {
    return entry
      .asSequence()
      .filter { getEntryDate(it).isAfter(lastRun)}
      .filter { validateEntry(it) }
      .map{parseEntry(it)}
      .filterNotNull()
      .toList()
  }

  protected abstract fun parseEntry(entry: T): FeedEntry?

  protected abstract fun validateEntry(entry: T): Boolean

  protected abstract fun getEntryDate(entry: T): LocalDateTime

  fun createFeedEntry(date: LocalDateTime, uri: String, text: String, keywords: List<String>): FeedEntry{
    return FeedEntry(date, uri, sanitizeText(text), keywords)
  }

  private fun sanitizeText(text: String): String {
    val document = Jsoup.parse(text)
    document.outputSettings(Document.OutputSettings().prettyPrint(false))
    document.select("br").append("\\n")
    document.select("p").prepend("\\n")
    val s = document.html().replace("\\n", " ")
    return Jsoup.clean(s, "", Whitelist.none(), Document.OutputSettings().prettyPrint(false))
  }
}