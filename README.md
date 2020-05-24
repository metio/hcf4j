# HTTP Client Facade for Java (hcf4j)

Provides an abstraction layer over the various HTTP client implementations in Java.

## Usage

```java
// create a client using ServiceLoader
var httpClient = HttpClients.client();

// fine tune w/ builder
var httpClient = HttpClients.factory()
    .client()
    .userAgent("my-awesome/client 1.2.3")
    .build();

// blocking request
var response = httpClient.get("http://example.com")
    .executeOnCallingThread();

// non-blocking requests
var responsePromise = httpClient.get("http://example.com")
    .mediaType("application/json")
    .executeInBackground();

// Java 9+ Flow based requests
client.get("http://example.com")
    .executeAsFlow(response -> response.body());

// inspect responses of blocking requests
var responseBody = response.body();
var statusCode = response.statusCode();

// handle non-blocking requests
responsePromise
    .thenAccept(response -> { /* your code here */ });

// fluent api
httpClient.get("http://example.com")
    .executeInBackground()
    .thenApply(HttpResponse::body)
    .thenAccept(System.out::println);

httpClient.post("http://example.com/api")
    .content("your-data")
    .mediaType("text/plain")
    .executeInBackground()
    .thenApply(HttpResponse::statusCode)
    .thenAccept(System.out::println)
```

## Support Matrix


| Feature              | OkHttp3 | Apache HttpClient | Apache Fluent HC | JDK HttpClient |
|:--------------------:|:-------:|:-----------------:|:----------------:|:--------------:|
| Invalid URLs         | ✔       | ✔                 | ✘                | ✘              |
| GET Requests         | ✔       | ✔                 | ✘                | ✘              |
| POST Requests        | ✔       | ✔                 | ✘                | ✘              |
| PUT Requests         | ✔       | ✔                 | ✘                | ✘              |

## Alternatives

- https://github.com/imperva/shcf4j

## License

To the extent possible under law, the author(s) have dedicated all copyright
and related and neighboring rights to this software to the public domain
worldwide. This software is distributed without any warranty.

You should have received a copy of the CC0 Public Domain Dedication along
with this software. If not, see http://creativecommons.org/publicdomain/zero/1.0/.

## Mirrors

- https://github.com/metio/hcf4j
