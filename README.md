# The Aural Tester

## Overview

The purpose of this project is to teach, test (TODO) and test aurally (TODO) music students on their theory.

The following topics are available (or are to be implemented).

|                | Documentation | Examples | Testing (notation) | Testing (aural) |
|----------------|---------------|----------|--------------------|-----------------|
| Clef           | TODO          | Done     | Done               | TODO            |
| Key signatures | TODO          | TODO     | TODO               | TODO            |
| Intervals      | TODO          | Done     | Done               | TODO            |
| Scales         | TODO          | Done     | TODO               | TODO            |
| Cadences       | TODO          | Done     | TODO               | TODO            |

## Building

The following versions have been tested.

* `node` version 18.21.1
* `java` version 17.0.5 supplied by Zulu

The following `npm` scripts have been defined.

* `npm test` - runs unit tests on both frontend and backend.
* `npm run dev` - runs both frontend and backend in dev mode (i.e. will auto restart on detection of changes).
* `npm install` - installs dependencies on both frontend and backend.

The following technologies have been used to build this solution.
* Backend technologies
  * Kotlin
  * Spring Boot framework
  * Mockito
  * JUnit 5
* Frontend technologies
  * TypeScript
  * React
  * jest
  * Bootstrap