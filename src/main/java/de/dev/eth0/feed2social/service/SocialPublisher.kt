package de.dev.eth0.feed2social.service

import de.dev.eth0.feed2social.impl.model.FeedEntry

/**
 * Interface for Social Network Publisher
 */
interface SocialPublisher {
  /**
   * Publish the given Feed Entry
   */
  fun publish(entry: FeedEntry)
}