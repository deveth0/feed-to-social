/*
 * Copyright (c) 2019 dev-eth0.de All rights reserved.
 */
package de.dev.eth0.feed2social.impl.service.feeder.json;

import java.util.List;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;

@ConfigurationProperties("feed.json")
@ConditionalOnProperty("feed.json.url")
@Validated
public class JsonFeedProperties {

  @NotBlank
  private String url;

  @NotNull
  private JsonFeedProperties.Fields fields;

  public String getUrl() {
    return url;
  }

  public void setUrl(String url) {
    this.url = url;
  }

  public Fields getFields() {
    return fields;
  }

  public void setFields(Fields fields) {
    this.fields = fields;
  }

  @Validated
  public static class Fields {

    @NotBlank
    private String date;
    @NotBlank
    private String uri;
    @NotEmpty
    private List<String> text;
    @NotBlank
    private String keywords;

    public String getDate() {
      return date;
    }

    public void setDate(String date) {
      this.date = date;
    }

    public String getUri() {
      return uri;
    }

    public void setUri(String uri) {
      this.uri = uri;
    }

    public List<String> getText() {
      return text;
    }

    public void setText(List<String> text) {
      this.text = text;
    }

    public String getKeywords() {
      return keywords;
    }

    public void setKeywords(String keywords) {
      this.keywords = keywords;
    }
  }
}
