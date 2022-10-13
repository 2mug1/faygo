# Faygo

## Getting Started

### Installation

`pom.xml`
```xml
<repository>
    <id>github</id>
    <name>faygo</name>
    <url>https://maven.pkg.github.com/2mug1/faygo</url>
</repository>

<dependency>
  <groupId>me.hyperbone</groupId>
  <artifactId>faygo</artifactId>
  <version>1.0.0</version>
  <scope>compile</scope>
</dependency>
```

`build.gradle`
```gradle
repositories {
  maven (url = "https://maven.pkg.github.com/2mug1/faygo")
}
dependencies {
  implementation("me.hyperbone:faygo:1.0.0")
}
```

## LICENSE
[MIT License](./LICENSE) (© 2022 mirusms)