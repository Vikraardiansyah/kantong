# Kantong

Kantong is a personal expense tracker that I'm building while learning native Android development with Kotlin and Jetpack Compose. It starts as a command-line program and will grow into a full Android app released on Google Play.

## Status: v0.1 (CLI)

The current version is a terminal app written in plain Kotlin:

- Add a transaction (name, category, amount) with input validation
- List all transactions
- Show the total per category
- Show the largest transaction
- Amounts are formatted as Indonesian rupiah, for example Rp25.000

The bookkeeping logic (`Ledger`) is kept separate from the terminal input and output, so it can move into a ViewModel unchanged when the Compose app starts.

## Run

```
kotlinc cli/Kantong.kt -include-runtime -d kantong.jar
java -jar kantong.jar
```

## Roadmap

- v0.2–0.5: Compose app with three screens, ViewModel, and theming
- v0.6–0.11: Room database, Hilt, live exchange rates, offline-first, charts
- v1.0: tested, CI, released on Google Play
