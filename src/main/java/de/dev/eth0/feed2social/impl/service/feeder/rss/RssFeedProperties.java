/*
 * Copyright (c) 2019 dev-eth0.de All rights reserved.
 */
package de.dev.eth0.feed2social.impl.service.feeder.rss;

import javax.validation.constraints.NotBlank;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;

@ConfigurationProperties("feed.rss")
@ConditionalOnProperty("feed.rss.url")
@Validated
public class RssFeedProperties {

  @NotBlank
  private String url;

  public String getUrl() {
    return url;
  }

  public void setUrl(String url) {
    this.url = url;
  }
}
