/*
 * Copyright (c) 2019 dev-eth0.de All rights reserved.
 */

package de.dev.eth0.feed2social.impl.service.feeder.rss

import com.rometools.rome.feed.synd.SyndEntry
import de.dev.eth0.feed2social.impl.model.FeedEntry
import de.dev.eth0.feed2social.impl.service.feeder.AbstractFeedParser
import org.apache.commons.lang3.StringUtils
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty
import org.springframework.stereotype.Service
import java.sql.Timestamp
import java.time.LocalDateTime

@Service
@ConditionalOnProperty("feed.rss.url")
class RssFeedParser : AbstractFeedParser<SyndEntry>() {

  override fun validateEntry(entry: SyndEntry): Boolean {
    return StringUtils.isNoneBlank(entry.title, entry.uri, entry.description.value)
  }

  override fun parseEntry(entry: SyndEntry): FeedEntry {
    return createFeedEntry(getEntryDate(entry), entry.uri, entry.description.value, listOf())
  }

  override fun getEntryDate(entry: SyndEntry): LocalDateTime {
    return Timestamp(entry.updatedDate?.time ?: entry.publishedDate.time).toLocalDateTime()
  }
}