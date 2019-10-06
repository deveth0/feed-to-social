package de.dev.eth0.feed2social.impl.service

import org.springframework.cloud.openfeign.FeignClient
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.GetMapping

@FeignClient(
    name = "json-feed-client",
    url = "\${json.url}"
)
interface JsonFeedClient {

  @GetMapping(value = [""], consumes = [MediaType.APPLICATION_JSON_UTF8_VALUE])
  fun getFeed() : List<Map<String, Any>>

}