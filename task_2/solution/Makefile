SRC_DIR := src\main\java
TEST_DIR := src\test\java
BUILD_DIR := target
TEST_RESULTS_FILE := test_results.txt

MODIFIED_FILES := $(shell git diff --name-only --diff-filter=AM HEAD~1 | findstr /R "\.java" | findstr /V "Test.java")

all: build test

build:
	mvn compile

test:
	mvn test > $(TEST_RESULTS_FILE) 2>&1

test-modified:
	@echo Detected modified files:
	@echo $(MODIFIED_FILES)
	@if "$(MODIFIED_FILES)" == "" ( \
		echo No modified source files, skipping tests. \
	) else ( \
		setlocal enabledelayedexpansion && \
		for /F "delims=" %%F in ('git diff --name-only --diff-filter=AM HEAD~1 ^| findstr /R "\.java" ^| findstr /V "Test.java"') do ( \
			set "FILE_NAME=%%~nF" \
			echo Running test for: !FILE_NAME!Test >> test_results.txt && \
			cmd.exe /V:ON /C "mvn test -Dtest=!FILE_NAME!Test >> test_results.txt 2>&1" \
		) \
	)
	@echo Test run complete. Results saved in test_results.txt.

runtest:
	@if not defined FILE ( \
		echo Usage: mingw32-make runtest FILE=<TestClassName> \
	) else ( \
		echo Running test for $(FILE) \
		mvn test -Dtest=$(FILE) >> $(TEST_RESULTS_FILE) 2>&1 \
	)

clean:
	mvn clean
	del $(TEST_RESULTS_FILE) 2>nul

.PHONY: all build test test-modified runtest clean
