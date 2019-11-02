/*
 * Copyright (c) 2019 dev-eth0.de All rights reserved.
 */

package de.dev.eth0.feed2social.impl.service.feeder.json

import com.fasterxml.jackson.core.type.TypeReference
import com.fasterxml.jackson.databind.ObjectMapper
import de.dev.eth0.feed2social.impl.model.FeedEntry
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.core.io.ClassPathResource
import org.springframework.test.context.TestPropertySource
import java.time.LocalDateTime

@SpringBootTest(classes = [JsonFeedParser::class])
@TestPropertySource(locations = ["classpath:jsonFeedParserTest.properties"])
class JsonFeedParserTest(@Autowired val jsonFeedParser: JsonFeedParser) {

  private val testPostsPath = "test_posts.json"

  private val objectMapper = ObjectMapper()

  @Test
  fun `Entry Data`() {
    assertThat(jsonFeedParser).isNotNull
    val parsedEntries = parseEntries(testPostsPath)
    assertThat(parsedEntries).hasSize(3)
    assertThat(parsedEntries[0].date).isEqualTo(LocalDateTime.of(2019, 11, 1, 20, 0, 0))
    assertThat(parsedEntries[1].date).isEqualTo(LocalDateTime.of(2019, 9, 10, 18, 0, 0))
    assertThat(parsedEntries[2].date).isEqualTo(LocalDateTime.of(2019, 8, 4, 18, 0, 0))
  }

  @Test
  fun `Keywords`() {
    assertThat(jsonFeedParser).isNotNull
    val parsedEntries = parseEntries(testPostsPath)
    assertThat(parsedEntries).hasSize(3)
    assertThat(parsedEntries[0].keywords).containsExactly("python", "opencv")
    assertThat(parsedEntries[1].keywords).containsExactly("react", "typescript", "javascript")
    assertThat(parsedEntries[2].keywords).containsExactly("docker", "spring", "kubernetes")
  }

  @Test
  fun `Uri`() {
    assertThat(jsonFeedParser).isNotNull
    val parsedEntries = parseEntries(testPostsPath)
    assertThat(parsedEntries).hasSize(3)
    assertThat(parsedEntries[0].uri).isEqualTo("https://www.dev-eth0.de/2019/11/01/object-detection-in-images-and-videos-using-python-opencv-and-yolov3/")
    assertThat(parsedEntries[1].uri).isEqualTo("https://www.dev-eth0.de/2019/09/10/using-withrouter-in-a-typescript-react-component/")
    assertThat(parsedEntries[2].uri).isEqualTo("https://www.dev-eth0.de/2019/08/04/spring-boot-application-in-openshift-/-okd/")
  }

  @Test
  fun `Text`() {
    assertThat(jsonFeedParser).isNotNull
    val parsedEntries = parseEntries(testPostsPath)
    assertThat(parsedEntries).hasSize(3)
    assertThat(parsedEntries[2].text).isEqualTo("Now that we have packaged an existing Spring Boot application into a Docker Image, we can deploy it to a Kubernet cluster as well.")
  }

  @Test
  fun `Text sanatize encoding`() {
    assertThat(jsonFeedParser).isNotNull
    val parsedEntries = parseEntries(testPostsPath)
    assertThat(parsedEntries).hasSize(3)
    assertThat(parsedEntries[0].text).isEqualTo("It’s surprisingly easy to use a pretrained model for Object Detection in Images or Videos.")
  }

  @Test
  fun `Text sanatize html`() {
    assertThat(jsonFeedParser).isNotNull
    val parsedEntries = parseEntries(testPostsPath)
    assertThat(parsedEntries).hasSize(3)
    assertThat(parsedEntries[1].text).isEqualTo("When combining TypeScript with React, some of the tutorials cannot be adapted that simple. In this example I show how to use withRouter to manipulate the history in a functional React component.")
  }

  private fun parseEntries(jsonFile: String): List<FeedEntry> {
    val entries = getFeed(jsonFile)
    return jsonFeedParser.parseEntries(entries, LocalDateTime.MIN)
  }

  private fun getFeed(jsonFile: String): List<Map<String, Any>> {
    val cpr = ClassPathResource(jsonFile)
    val typeReference = object : TypeReference<List<Map<String, Any>>>() {};
    return objectMapper.readValue(cpr.file, typeReference)
  }

}