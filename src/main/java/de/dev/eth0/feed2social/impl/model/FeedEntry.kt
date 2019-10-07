/*
 * Copyright (c) 2019 dev-eth0.de All rights reserved.
 */

package de.dev.eth0.feed2social.impl.model

import java.time.LocalDateTime

/**
 * A feed entry
 */
data class FeedEntry(val date: LocalDateTime,
                     val uri: String,
                     val text: String,
                     val keywords: List<String>)