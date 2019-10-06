package de.dev.eth0.feed2social.impl.service.dummy

import de.dev.eth0.feed2social.impl.model.FeedEntry
import de.dev.eth0.feed2social.impl.service.JsonTwitterServiceImpl
import de.dev.eth0.feed2social.service.TwitterService
import org.slf4j.LoggerFactory
import org.springframework.context.annotation.Profile
import org.springframework.stereotype.Service

@Service
@Profile("dummy")
class DummyTwitterServiceImpl : TwitterService {


  private val log = LoggerFactory.getLogger(DummyTwitterServiceImpl::class.java)

  override fun publish(entry: FeedEntry) {
    log.info("publishing $entry")
  }
}