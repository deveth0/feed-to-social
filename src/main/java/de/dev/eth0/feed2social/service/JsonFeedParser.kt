package de.dev.eth0.feed2social.service

import de.dev.eth0.feed2social.impl.model.FeedEntry

/**
 * Parser for a Json Feed
 */
interface JsonFeedParser {

  /**
   * parse given entries
   */
  fun parseEntries(entries: List<Map<String, Any>>): List<FeedEntry>
}