package de.dev.eth0.feed2social.impl

import de.dev.eth0.feed2social.service.JsonTwitterService
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component
import java.text.SimpleDateFormat

@Component
class JsonTwitterScheduledTask {

  private val log = LoggerFactory.getLogger(JsonTwitterScheduledTask::class.java)
  private val dateFormat = SimpleDateFormat("HH:mm:ss")

  @Autowired
  private lateinit var jsonTwitterService : JsonTwitterService;

  @Scheduled(fixedRateString = "\${app.scheduler}")
  fun readJson() {
    jsonTwitterService.perform()
  }

}
