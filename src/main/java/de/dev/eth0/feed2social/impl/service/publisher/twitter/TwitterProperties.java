/*
 * Copyright (c) 2019 dev-eth0.de All rights reserved.
 */
package de.dev.eth0.feed2social.impl.service.publisher.twitter;

import javax.validation.constraints.NotBlank;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;

@ConfigurationProperties("publisher.twitter")
@ConditionalOnProperty("publisher.twitter.apikey")
@Validated
public class TwitterProperties {

  @NotBlank
  private String apiKey;
  @NotBlank
  private String apiSecret;
  @NotBlank
  private String accessToken;
  @NotBlank
  private String accessTokenSecret;

  public String getApiKey() {
    return apiKey;
  }

  public void setApiKey(String apiKey) {
    this.apiKey = apiKey;
  }

  public String getApiSecret() {
    return apiSecret;
  }

  public void setApiSecret(String apiSecret) {
    this.apiSecret = apiSecret;
  }

  public String getAccessToken() {
    return accessToken;
  }

  public void setAccessToken(String accessToken) {
    this.accessToken = accessToken;
  }

  public String getAccessTokenSecret() {
    return accessTokenSecret;
  }

  public void setAccessTokenSecret(String accessTokenSecret) {
    this.accessTokenSecret = accessTokenSecret;
  }
}
