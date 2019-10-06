package de.dev.eth0.feed2social.impl.service

import de.dev.eth0.feed2social.config.TwitterProperties
import de.dev.eth0.feed2social.impl.model.FeedEntry
import de.dev.eth0.feed2social.service.TwitterService
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.context.annotation.Profile
import org.springframework.stereotype.Service
import twitter4j.Twitter
import twitter4j.TwitterException
import twitter4j.TwitterFactory
import twitter4j.conf.ConfigurationBuilder

@Service
@Profile("!dummy")
@EnableConfigurationProperties(TwitterProperties::class)
class TwitterServiceImpl @Autowired constructor(
    private val twitterProperties: TwitterProperties
) : TwitterService {

  private val log = LoggerFactory.getLogger(TwitterService::class.java)

  private val twitter: Twitter;

  init {
    val cb = ConfigurationBuilder();
    cb.setDebugEnabled(true)
        .setOAuthConsumerKey(twitterProperties.apiKey)
        .setOAuthConsumerSecret(twitterProperties.apiSecret)
        .setOAuthAccessToken(twitterProperties.accessToken)
        .setOAuthAccessTokenSecret(twitterProperties.accessTokenSecret);
    val tf = TwitterFactory(cb.build());
    twitter = tf.instance;
  }

  override fun publish(entry: FeedEntry) {
    try {
      val tweet = buildTweet(entry);
      twitter.updateStatus(tweet)
    } catch (ex: TwitterException) {
      log.warn("Could not publish tweet", ex);
    }
  }


  private fun buildTweet(entry: FeedEntry): String {
    val keywords = entry.keywords.joinToString(separator = " ") { "#$it" }
    val tweet = if (keywords != "") {
      " $keywords ${entry.uri}"
    } else {
      " ${entry.uri}"
    }
    // tweets cannot be more than 280 letters, therefor we need to truncate them
    val tweetLength = entry.text.length + tweet.length;
    val text = if (tweetLength > 280) {
      "${entry.text.substring(0, entry.text.length - (tweetLength - 280) - 4)} ..."
    } else {
      entry.text
    }
    return "$text$tweet"
  }
}