package de.dev.eth0.feed2social.service

import de.dev.eth0.feed2social.impl.model.FeedEntry

/**
 * Interface for a Service which publishes Tweets
 */
interface TwitterService {
  /**
   * Build a tweet and publish it
   */
  fun publish(entry: FeedEntry)
}