# Testing Strategy — Quick-Calc

## What Was Tested and Why

The test suite targets the two layers that contain meaningful logic: the pure `Calculator` class and the stateful `CalcSession` class.

**Calculator (unit tests)** — every public method (`add`, `subtract`, `multiply`, `divide`) has at least one happy-path test. Beyond the basic cases, edge cases cover: decimal precision, multiplication by zero, negative results, large numbers, and division-by-zero exception handling. These are the inputs most likely to expose floating-point errors or missing guard clauses.

**CalcSession (integration tests)** — the session layer wires `Calculator` together with state management (stored value, pending operator, display formatting). Two integration tests exercise complete user-interaction sequences: a full addition flow (enter → operator → enter → equals → assert display) and a clear-after-calculation reset flow.

**What was not tested:** The `App` (CLI) class was intentionally excluded. Its logic is trivial string parsing and a `switch` that delegates immediately to `Calculator`. Testing it would require either mocking `System.in`/`System.out` or building a test harness — effort disproportionate to the risk. Non-functional concerns (performance, memory usage) were also out of scope for a calculator of this scale.

---

## Lecture Concepts Applied

### 1. The Testing Pyramid
The suite follows the pyramid's proportions: **10 unit tests** at the base (fast, isolated, numerous) and **2 integration tests** at the middle layer (slower, fewer, cross-component). There are no end-to-end tests because the application has no network or database layer to integrate with. This distribution keeps the suite fast to run and easy to maintain.

### 2. Black-box vs White-box Testing
**Unit tests** for `Calculator` were written as **black-box tests** — each test specifies an input and asserts the expected output without knowledge of the internal arithmetic implementation. The division-by-zero test is an exception: it explicitly tests the guard clause, making it **white-box**. The **integration tests** are black-box: they simulate user behaviour through the public `CalcSession` API and assert only on what the display shows, with no reference to internal state variables.

### 3. Functional vs Non-Functional Testing
All tests are **functional** — they verify that the application does what it is specified to do (correct arithmetic, exception on bad input, display reset on clear). **Non-functional testing** (response time, memory footprint, concurrent session safety) was consciously excluded. For a single-user CLI tool, these concerns are irrelevant at this stage; they would be relevant if Quick-Calc were scaled to a multi-user web service.

### 4. Regression Testing
The test suite functions as a **regression baseline**. Every future commit can run `mvn test` to confirm that no previously working behaviour has broken. The meaningful commit history and v1.0.0 release tag establish a stable reference point. If a future feature (e.g., square root, history log) introduces a bug in `divide`, the existing `testDividePositiveNumbers` and `testDivideByZeroThrows` tests will immediately surface it without any manual re-testing.

---

## Test Results Summary

| Test Name | Class | Type | Status |
|---|---|---|---|
| testAddPositiveNumbers | CalculatorTest | Unit |  Pass |
| testSubtractPositiveNumbers | CalculatorTest | Unit |  Pass |
| testSubtractResultNegative | CalculatorTest | Unit |  Pass |
| testMultiplyPositiveNumbers | CalculatorTest | Unit |  Pass |
| testMultiplyByZero | CalculatorTest | Unit |  Pass |
| testDividePositiveNumbers | CalculatorTest | Unit |  Pass |
| testDivideByZeroThrows | CalculatorTest | Unit |  Pass |
| testAddDecimals | CalculatorTest | Unit |  Pass |
| testMultiplyLargeNumbers | CalculatorTest | Unit |  Pass |
| testSubtractNegativeNumbers | CalculatorTest | Unit |  Pass |
| testFullAdditionFlow | CalcSessionIntegrationTest | Integration |  Pass |
| testClearResetsDisplay | CalcSessionIntegrationTest | Integration |  Pass |