package de.dev.eth0.feed2social.impl.service.feeder.json

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.GetMapping

@ConditionalOnProperty("feed.json.url")
@FeignClient(
    name = "json-feed-client",
    url = "\${feed.json.url}"
)
interface JsonFeedClient {

  @GetMapping(value = [""], consumes = [MediaType.APPLICATION_JSON_UTF8_VALUE])
  fun getFeed() : List<Map<String, Any>>

}