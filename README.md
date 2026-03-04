# Quick-Calc

Quick-Calc is a lightweight command-line calculator application built with Java and Maven. It supports the four fundamental arithmetic operations — addition, subtraction, multiplication, and division — and handles edge cases such as division by zero gracefully. The project prioritises clean, testable code and demonstrates a multi-layered testing strategy using JUnit 5.

---

## Setup Instructions

**Prerequisites:** Java 11+, Maven 3.8+
```bash
# Clone the repository
git clone https://github.com/<your-username>/swe-testing-assignment.git
cd swe-testing-assignment

# Compile and package
mvn package -q

# Run the application
java -cp target/quick-calc-1.0.0.jar com.quickcalc.App
```

---

## How to Run Tests
```bash
mvn test
```

All unit and integration tests will be discovered and executed automatically by the Maven Surefire plugin. Results are printed to the console and saved under `target/surefire-reports/`.

---

## Testing Framework Research

### JUnit 5 vs TestNG

Two dominant testing frameworks exist for Java: **JUnit 5 (Jupiter)** and **TestNG**. JUnit 5 is the de facto standard, deeply integrated with every major Java IDE and build tool. Its annotation model (`@Test`, `@BeforeEach`, `@DisplayName`, `@ParameterizedTest`) is intuitive and expressive. JUnit 5's modular architecture (Platform / Jupiter / Vintage) makes it extensible without bloat. The community is enormous, meaning documentation, Stack Overflow answers, and third-party integrations (Mockito, AssertJ, Spring) are all first-class.

TestNG was designed to address perceived gaps in JUnit 4, particularly around test grouping, parallel execution, and data-driven testing via XML configuration. For large enterprise test suites that require fine-grained execution control — running only a "regression" group, or parallelising at the method level — TestNG offers more built-in power out of the box. Its `@DataProvider` annotation is arguably more elegant than JUnit 5's `@ParameterizedTest` for complex data scenarios.

For this project, **JUnit 5** was chosen because the test suite is modest in scale, the toolchain (VS Code + Maven Surefire 3.x) has zero-configuration support for it, and its readable `@DisplayName` annotations produce clear test reports that map directly to assignment requirements. TestNG's advantages in parallelism and grouping are unnecessary overhead for a calculator with fewer than 15 tests.

---

## Project Structure
```
src/
├── main/java/com/quickcalc/
│   ├── App.java            # CLI entry point
│   ├── Calculator.java     # Pure calculation logic
│   └── CalcSession.java    # Stateful session (input → operator → result)
└── test/java/com/quickcalc/
    ├── CalculatorTest.java              # Unit tests (10 tests)
    └── CalcSessionIntegrationTest.java  # Integration tests (2 tests)
```