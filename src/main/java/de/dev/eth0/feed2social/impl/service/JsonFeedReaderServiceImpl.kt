package de.dev.eth0.feed2social.impl.service

import de.dev.eth0.feed2social.config.JsonFeedProperties
import de.dev.eth0.feed2social.impl.model.FeedEntry
import de.dev.eth0.feed2social.service.JsonFeedParser
import de.dev.eth0.feed2social.service.JsonFeedReaderService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.stereotype.Service
import java.time.LocalDateTime

@Service
class JsonFeedReaderServiceImpl : JsonFeedReaderService {

  @Autowired
  lateinit var jsonFeedClient: JsonFeedClient

  @Autowired
  lateinit var jsonFeedParser: JsonFeedParser

  override fun getFeedEntries(lastRun: LocalDateTime): List<FeedEntry> {
    val entries = jsonFeedClient.getFeed()

    val parsedEntries = jsonFeedParser.parseEntries(entries);
    return parsedEntries.filter {
      it.date.isAfter(lastRun)
    }
  }
}