package de.dev.eth0.feed2social.service

import de.dev.eth0.feed2social.impl.model.FeedEntry
import java.time.LocalDateTime

/**
 * Interface for a Feeder
 */
interface Feeder {

  /**
   * @return list with all Feed Entries published after the given time
   */
  fun getFeedEntries(lastRun: LocalDateTime): List<FeedEntry>
}