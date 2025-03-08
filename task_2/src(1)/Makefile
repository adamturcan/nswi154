
SRC_DIR := src\main\java
TEST_DIR := src\test\java
BUILD_DIR := target
TEST_RESULTS_FILE := test_results.txt


MODIFIED_FILES := $(shell powershell -Command "git diff --name-only --diff-filter=AM HEAD~1 | Select-String '\.java' | Where-Object {$_ -notmatch 'Test.java'}")


TEST_CLASSES := $(MODIFIED_FILES:.java=Test)


all: build test


build:
	mvn compile


test:
	mvn test > test_results.txt 2>&1

test-modified:
	@if not defined TEST_CLASSES ( \
		echo "No modified source files, skipping tests." \
	) else ( \
		echo "Running tests for: $(TEST_CLASSES)" & \
		mvn test -Dtest="$(TEST_CLASSES)" | tee $(TEST_RESULTS_FILE) \
	)


runtest:
	@if not defined FILE ( \
		echo "Usage: make runtest FILE=<TestClassName>" \
	) else ( \
		echo "Running test for $(FILE)" & \
		mvn test -Dtest="$(FILE)" | tee $(TEST_RESULTS_FILE) \
	)


clean:
	mvn clean
	del $(TEST_RESULTS_FILE) 2>nul

.PHONY: all build test test-modified runtest clean
