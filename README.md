## Canis

CLI to encrypt and decrypt texts.

### Prerequisites

<a href="https://adoptium.net/" target_="blank">JDK 17+ </a>

<a href="https://www.graalvm.org/" target_="blank">Graal VM 22.3 </a>

### Test
```bash
# unit tests
mvn clean test

# run CLI in developer mode
mvn quarkus:dev -Dquarkus.args="help"
```

### Build
```bash
# terminal e.g. either x64 Native Tools Command Prompt (windows) or gcc
mvn clean install -Dnative -DskipTests

# via container - slow compilation
mvn install -Dnative -DskipTests -Dquarkus.native.container-build=true
```

### Run
```bash
canis help
```

### Dist
```bash
mvn -Pdist package -DskipTests

# check jreleaser config
mvn -Prelease jreleaser:config -Djreleaser.select.current.platform

# check change log
mvn -Prelease jreleaser:changelog

# create tag and release to Github
mvn -Prelease jreleaser:release -Djreleaser.select.current.platform
```