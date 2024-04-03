# Quarkus Velocity Extension

This Quarkus extension allows you to use the Apache Velocity template engine in your
Quarkus application without you taking care about registering the engine and the related
classes for native builds.

## Usage

Add the following dependency to your `pom.xml`:

```xml
<dependency>
    <groupId>com.cycrilabs</groupId>
    <artifactId>quarkus-velocity</artifactId>
</dependency>
```

Currently, the extension is not available in Maven Central. You can build it from source
and install it to your local Maven repository. Alternatively, you can add the Github
Registry in your local Maven `settings.xml`.

## Versions

The extension is available for the following Quarkus versions:

| Extension Version | Quarkus Version | Apache Velocity |
|-------------------|-----------------|-----------------|
| 1.0.0             | 3.6.3           | 2.3             |
| 1.1.0             | 3.7.1           | 2.3             |
| 1.2.0             | 3.8.1           | 2.3             |
| 1.3.0             | 3.9.2           | 2.3             |
