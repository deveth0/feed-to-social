# feed-to-social
Spring Boot Application which publishes Blog Posts (or other Feeds) to Social Platforms

## Description

The `feed-to-social` application provides an easy way to publish data from a feed (e.g. json) to social platforms.

It parses a feed from a blog or similar on a regular basis and publishes any new entries in the feed to the configured social platforms.

### Supported Parser

* RSS Feed (see [RSS Feed](#rss-feed))
* JSON Feed (see [JSON Feed](#json-feed))

### Supported Publisher

* Twitter (see [Twitter Publisher](#twitter-publisher))

## Usage

### Docker

The prefered way to run this project is to use a prebuild Docker Image and configure the application using docker-compose.

You can find an example [docker-compose.yml](https://github.com/deveth0/feed-to-social/blob/master/docker-compose.yml) in the project's root which contains all configurations.

### Build and Run Spring Boot

You can also build and run this repository directly. It requires JDK11 and a current Maven version to build.

```bash
mvn clean install
```

You can add the configuration to `application.yml` or provide all required configs using environment variables (see below).

## Configuration

All configurations can be found in `application.yml` and also passed as Environment Variables as documented below

### App

| Config | Description  | Example |
|---|---|---|
| APP_SCHEDULER | Update rate for Feed in ms | 5000 |

### RSS Feed

| Config | Description  | Example |
|---|---|---|
| FEED_RSS_URL | Url of the RSS feed | http://example.com/index.xml |

### JSON Feed

| Config | Description  | Example |
|---|---|---|
| FEED_JSON_URL | Url of the json feed | http://example.com/feed.json |
| FEED_JSON_FIELDS_DATE | Field in json which contains the date (ISO) | date |
| FEED_JSON_FIELDS_URI | Field in json which contains the uri of the entry | uri |
| FEED_JSON_FIELDS_KEYWORDS | Field in json which contains keywords (used as hashtags) | keywords |
| FEED_JSON_FIELDS_TEXT | List of fields that might contain text. First field with content is used. | text:<br>  - summary<br>   - title|

Example for an entry in json:

```json
{
  "date": "2019-09-10T18:00:00+02:00",
  "keywords": [
    "react",
    "typescript",
    "javascript"
  ],
  "summary": "When combining TypeScript with React, some of the tutorials cannot be adapted that simple.</br>In this example I show how to use <code>withRouter</code> to manipulate the history in a functional React component.",
  "title": "Using withRouter in a TypeScript React Component",
  "uri": "https://dev.dev-eth0.de/2019/09/10/using-withrouter-in-a-typescript-react-component/"
}

```
### Twitter Publisher

To use the Twitter publisher, you need to create a Twitter application and configure the following properties.
See [developer.twitter.com](https://developer.twitter.com/en/docs/basics/authentication/guides/access-tokens) on more information about the registration.

| Config | Description  | Example |
|---|---|---|
| PUBLISHER_TWITTER_APIKEY | Twitter Api Key |  |
| PUBLISHER_TWITTER_APISECRET | Twitter Api Secret | |
| PUBLISHER_TWITTER_ACCESSTOKEN | Twitter Access Token| |
| PUBLISHER_TWITTER_ACCESSTOKENSECRET | Twitter Access Token Secret | |

## References

* [github.com - feed-to-social](https://github.com/deveth0/feed-to-social)
* [dockerhub.com - feed-to-social](https://hub.docker.com/r/deveth0/feed-to-social)
