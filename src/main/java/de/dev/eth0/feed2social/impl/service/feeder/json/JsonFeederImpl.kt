/*
 * Copyright (c) 2019 dev-eth0.de All rights reserved.
 */

package de.dev.eth0.feed2social.impl.service.feeder.json

import de.dev.eth0.feed2social.impl.model.FeedEntry
import de.dev.eth0.feed2social.service.Feeder
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty
import org.springframework.stereotype.Service
import java.time.LocalDateTime

/**
 * Implementation of Feeder which uses a JSON file as index
 */
@Service
@ConditionalOnProperty("feed.json.url")
class JsonFeederImpl : Feeder {

  @Autowired
  lateinit var jsonFeedClient: JsonFeedClient

  @Autowired
  lateinit var jsonFeedParser: JsonFeedParser

  override fun getFeedEntries(lastRun: LocalDateTime): List<FeedEntry> {
    val entries = jsonFeedClient.getFeed()
    return jsonFeedParser.parseEntries(entries, lastRun)
  }
}