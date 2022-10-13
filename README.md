# faygo
依存関係を書き出さなくても、ダウンロードすることで使用できるようにするライブラリ
## Getting Started

### インストール

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
  <version>1.0.2</version>
  <scope>compile</scope>
</dependency>
```

`build.gradle`
```gradle
repositories {
  maven (url = "https://maven.pkg.github.com/2mug1/faygo")
}
dependencies {
  implementation("me.hyperbone:faygo:1.0.2")
}
```

### コード例
```java
public class Example extends JavaPlugin {

    @Override
    public void onEnable() {
        new Faygo(this).getDependencyBuilder()
                .add(new Dependency(
                        Repository.MAVENCENTRAL,
                        "redis.clients",
                        "jedis",
                        "4.3.0"))
                .add(new Dependency(
                        "https://libraries.minecraft.net",
                        "com.mojang",
                        "brigadier",
                        "1.0.18"))
                .load();
    }
}
```

## LICENSE
[MIT License](./LICENSE) (© 2022 mirusms)