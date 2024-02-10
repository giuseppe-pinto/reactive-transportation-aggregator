**REACTIVE TRANSPORTATION AGGREGATOR**


Hello everyone! This repository was created by me for experimental and study purposes.

I enjoy experimenting with reactive programming, so I've devised a scenario where its application is feasible!

Imagine that you need to create a backend system that sends transportation solutions (flights, trains, etc.) from different providers to a front-end application. Naturally, the frontend team prefers a REACTIVE system. They dislike the old systems where HTTP calls take a considerable amount of time to respond. So, how can we build a backend app that adheres to the frontend team's preferences? This is the goal of the reactive-transportation-aggregator! It's a system written in Java (17) and Spring with one primary rule: be reactive!

I opted to use Reactor from Spring and created a controller that responds with Flux objects. I also developed different providers/drivers (accessible through the DriverRepository interface) named BlueDriverRepository, GreenDriverRepository, and RedDriverRepository. Each one simulates a call to an external system. As you can imagine, different drivers may exhibit varying response times, and this characteristic is implemented individually in each supplier. One of my objectives was to return every supplier response to the caller without waiting for the slowest supplier!

You can run the app by starting up the Spring Boot application from the TransportationAggregatorApplication class. After starting the application, you can view the results by accessing the following GET endpoint using a simple browser: http://localhost:8080/aggregator/search.

Last but not least I used the hexagonal architectural pattern. 

Enjoy it!

The following image describe a little bit the architecture of the aggregator.

![image.png](image.png)

