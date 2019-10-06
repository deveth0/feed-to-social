package de.dev.eth0.feed2social.impl.service

import de.dev.eth0.feed2social.service.JsonFeedReaderService
import de.dev.eth0.feed2social.service.JsonTwitterService
import de.dev.eth0.feed2social.service.TwitterService
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.time.LocalDateTime

@Service
class JsonTwitterServiceImpl : JsonTwitterService {


  private val log = LoggerFactory.getLogger(JsonTwitterServiceImpl::class.java)

  @Autowired
  lateinit var jsonFeedReaderService: JsonFeedReaderService

  @Autowired
  lateinit var twitterService: TwitterService

  private var lastRun: LocalDateTime = LocalDateTime.now()

  override fun perform() {
    val entries = jsonFeedReaderService.getFeedEntries(lastRun)

    log.info("Found new entries: $entries")

    entries.take(3).forEach { twitterService.publish(it) }

    lastRun = LocalDateTime.now()
  }
}