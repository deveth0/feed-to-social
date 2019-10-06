# feed-to-social
Spring Boot Application which publishes Blog Posts (or other Feeds) to Social Platforms

## Description

The `feed-to-social` application provides an easy way to publish data from a feed (e.g. json) to social platforms.

It parses a feed from a blog or similar on a regular basis and publishes any new entries in the feed to the configured social platforms.

### Supported Parser

* JSON Feed (see [JSON Feed](#json-feed))

### Supported Publisher

* Twitter (see [Twitter Publisher](#twitter-publisher))

## Usage

### Docker

The prefered way to run this project is to use a prebuild Docker Image and configure the application using docker-compose.

You can find an example `docker-compose.yml` in the project's root.

### Build and Run Spring Boot

You can also build and run this repository directly. It requires JDK11 and a current Maven version to build.

```bash
mvn clean install
```

You can add the configuration to `application.yml` or provide all required configs using environment variables (see below).

## Configuration

All configurations are available using environment variables.

### App

| Config | Description  | Example |
|---|---|---|
| APP_SCHEDULER | Update rate for Feed in ms | 5000 |

### JSON Feed

| Config | Description  | Example |
|---|---|---|
| JSON_URL | Url of the json feed | http://example.com/feed.json |
| JSON_FIELDS_DATE | Field in json which contains the date (ISO) | date |
| JSON_FIELDS_URI | Field in json which contains the uri of the entry | uri |
| JSON_FIELDS_KEYWORDS | Field in json which contains keywords (used as hashtags) | keywords |
| JSON_FIELDS_TEXT | List of fields that might contain text. First field with content is used. | text:<br>  - summary<br>   - title|

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
| TWITTER_APIKEY | Twitter Api Key |  |
| TWITTER_APISECRET | Twitter Api Secret | |
| TWITTER_ACCESSTOKEN | Twitter Access Token| |
| TWITTER_ACCESSTOKENSECRET | Twitter Access Token Secret | |

## References

* [github.com - feed-to-social](https://github.com/deveth0/feed-to-social)
* [dockerhub.com - feed-to-social](https://hub.docker.com/r/deveth0/feed-to-social)
