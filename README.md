# The Aural Tester

## Overview

The purpose of this project is to teach, test (TODO) and test aurally (TODO) music students on their theory.

The following topics are available (or are to be implemented).

|                | Documentation | Examples | Testing (notation) | Testing (aural) |
|----------------|---------------|----------|--------------------|-----------------|
| Key signatures | TODO          | TODO     | TODO               | TODO            |
| Intervals      | TODO          | Done     | TODO               | TODO            |
| Scales         | TODO          | Done     | TODO               | TODO            |
| Cadences       | TODO          | Done     | TODO               | TODO            |

## Building

The following versions have been tested.

* `node` version 18.21.1
* `sbt` version 1.7.3
* `scala` version 2.13.10
* `java` version 11.0.17 supplied by Zulu

The following `npm` scripts have been defined.

* `npm test` - runs unit tests on both frontend and backend.
* `npm run dev` - runs both frontend and backend in dev mode (i.e. will auto restart on detection of changes).
* `npm install` - installs dependencies on both frontend and backend.

The following technologies have been used to build this solution.
* Backend technologies
  * Scala
  * Play framework
  * Mockito
  * Scala Test
* Frontend technologies
  * TypeScript
  * React
  * jest
  * Bootstrap