package de.dev.eth0.feed2social.impl.service.feeder.dummy

import de.dev.eth0.feed2social.impl.model.FeedEntry
import de.dev.eth0.feed2social.service.Feeder
import org.apache.commons.lang3.RandomStringUtils
import org.apache.commons.lang3.RandomUtils
import org.springframework.context.annotation.Profile
import org.springframework.stereotype.Service
import java.time.LocalDateTime

/**
 * Dummy implementation of Feeder used when starting with the `development` profile.
 *
 * Returns a random list of entries on each run
 */
@Profile("development")
@Service
open class DummyFeederImpl : Feeder {
  override fun getFeedEntries(lastRun: LocalDateTime): List<FeedEntry> {
    if (RandomUtils.nextBoolean()) {
      return buildRandomFeedEntries()
    }
    return emptyList()
  }

  private fun buildRandomFeedEntries(): List<FeedEntry> {
    return List(RandomUtils.nextInt(1, 3)) {
      buildRandomFeedEntry()
    }
  }

  private fun buildRandomFeedEntry(): FeedEntry {
    val tags = List(RandomUtils.nextInt(0, 4)) {
      RandomStringUtils.randomAlphabetic(4)
    }
    return FeedEntry(
      LocalDateTime.now().plusMinutes(RandomUtils.nextLong(1, 120)),
      RandomStringUtils.randomAlphabetic(20),
      RandomStringUtils.randomAlphabetic(20),
      tags
    )
  }
}