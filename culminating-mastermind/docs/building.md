---
# Feel free to add content and custom Front Matter to this file.
# To modify the layout, see https://jekyllrb.com/docs/themes/#overriding-theme-defaults

layout: page
nav_order: 3
---

# Building the Project

This project is built using [Gradle](https://gradle.org), with Gradle wrapper
bundled.

From this point on, you are assumed to be in [project root](http://powersagitar.github.io/ics4u/#project-root)
and using the terminal, and on a UNIX or UNIX-like system.

If you are on Windows, please replace occurrences of `gradlew` with `gradlew.bat`.

## Compiling the Project

Run the following command:

```zsh
./gradlew build
```

## Running the Project

Run the following command:

```zsh
./gradlew run
```

This command invokes [build](#compiling-the-project) automatically.

## Running Unit Tests

Run the following command:

```zsh
./gradlew test
```

## Building Project Wiki

Install [Jekyll](https://jekyllrb.com/docs/installation/).

Run the following command:

```zsh
utils/start-local-wiki.sh
```
