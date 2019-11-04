package de.dev.eth0.feed2social.impl.service.publisher.dummy

import de.dev.eth0.feed2social.impl.model.FeedEntry
import de.dev.eth0.feed2social.service.SocialPublisher
import org.slf4j.LoggerFactory
import org.springframework.context.annotation.Profile
import org.springframework.stereotype.Service

/**
 * Dummy implementation of SocialPublisher used when starting with the `development` profile.
 *
 * Publishes all FeedEntries to log
 */
@Profile("development")
@Service
open class DummyPublisherImpl : SocialPublisher {

  private val log = LoggerFactory.getLogger(DummyPublisherImpl::class.java)
  override fun publish(entry: FeedEntry) {
    log.info("Publishing $entry")
  }
}