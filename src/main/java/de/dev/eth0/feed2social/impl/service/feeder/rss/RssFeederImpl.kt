/*
 * Copyright (c) 2019 dev-eth0.de All rights reserved.
 */

package de.dev.eth0.feed2social.impl.service.feeder.rss

import com.rometools.rome.io.SyndFeedInput
import com.rometools.rome.io.XmlReader
import de.dev.eth0.feed2social.impl.model.FeedEntry
import de.dev.eth0.feed2social.service.Feeder
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.stereotype.Service
import java.net.URL
import java.time.LocalDateTime

@Service
@ConditionalOnProperty("feed.rss.url")
@EnableConfigurationProperties(RssFeedProperties::class)
class RssFeederImpl : Feeder {

  @Autowired
  lateinit var rssFeedProperties: RssFeedProperties

  @Autowired
  lateinit var rssFeedParser: RssFeedParser

  private val log = LoggerFactory.getLogger(RssFeederImpl::class.java)

  override fun getFeedEntries(lastRun: LocalDateTime): List<FeedEntry> {
    try {
      val xmlReader = XmlReader(URL(rssFeedProperties.url))
      val feed = SyndFeedInput().build(xmlReader)
      return rssFeedParser.parseEntries(feed.entries, lastRun)
    } catch (e: Exception) {
      log.error("Could not parse xml", e)
    }
    return listOf()
  }
}