package de.dev.eth0.feed2social.service

/**
 * Interface describing a Service which loads information from a JSON and then publishes them to Twitter
 */
interface JsonTwitterService {

  /**
   * Perform the action
   */
  fun perform();
}