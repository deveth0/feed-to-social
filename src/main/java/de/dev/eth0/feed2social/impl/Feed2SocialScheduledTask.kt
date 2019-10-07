/*
 * Copyright (c) 2019 dev-eth0.de All rights reserved.
 */

package de.dev.eth0.feed2social.impl

import de.dev.eth0.feed2social.service.Feed2SocialService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component

@Component
class Feed2SocialScheduledTask {

  @Autowired
  private lateinit var feed2SocialService: Feed2SocialService;

  @Scheduled(fixedRateString = "\${app.scheduler}")
  fun readJson() {
    feed2SocialService.perform()
  }
}
