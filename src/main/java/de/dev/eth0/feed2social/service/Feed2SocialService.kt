package de.dev.eth0.feed2social.service

/**
 * Interface describing a Service which loads information from a Feeder and then publishes them to all configured Publisher
 */
interface Feed2SocialService {

  /**
   * Perform the action
   */
  fun perform();
}