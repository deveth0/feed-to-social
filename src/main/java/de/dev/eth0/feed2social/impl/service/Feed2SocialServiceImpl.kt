/*
 * Copyright (c) 2019 dev-eth0.de All rights reserved.
 */

package de.dev.eth0.feed2social.impl.service

import de.dev.eth0.feed2social.service.Feed2SocialService
import de.dev.eth0.feed2social.service.Feeder
import de.dev.eth0.feed2social.service.SocialPublisher
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.time.LocalDateTime

@Service
class Feed2SocialServiceImpl : Feed2SocialService {

  private val log = LoggerFactory.getLogger(Feed2SocialServiceImpl::class.java)

  @Autowired
  lateinit var feedReader: Feeder

  @Autowired
  lateinit var publisher: Array<SocialPublisher>

  private var lastRun: LocalDateTime = LocalDateTime.now()

  override fun perform() {
    val entries = feedReader.getFeedEntries(lastRun)

    log.info("Found new entries: $entries")

    entries.take(3).forEach { entry ->
      publisher.forEach { pub ->
        try {
          pub.publish(entry)
        } catch (e: Exception) {
          log.error("Could not publish $entry with $pub")
        }
      }
    }
    lastRun = LocalDateTime.now()
  }
}