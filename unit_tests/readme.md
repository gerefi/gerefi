See https://github.com/gerefi/gerefi/wiki/Dev-Quality-Control

TL, DR: just follow [tests](tests) folder as examples. [tests/nitrous_control](tests/nitrous_control) is a great starting point!

gcc/makefile/gtest

1. Run 'make' to build desktop binary.
2. Execute gerefi_test binary on your PC/Mac, it's expected to say SUCCESS and not fail :) Googletest will also print results summary.
3. To run only one test use command line like ```build/gerefi_test --gtest_filter=*TEST_NAME*``` ~~uncomment and modify [main.cpp](https://github.com/gerefi/gerefi/blob/master/unit_tests/main.cpp) line ``::testing::GTEST_FLAG(filter)``~~

In this folder we have gerEFI unit tests using https://github.com/google/googletest

Unit tests are not aware of ChibiOS or ARM or else, they are just plain C/C++ which you build for your desktop, not your MCU.



[Code Coverage Report](https://gerefi.com/docs/unit_tests_coverage/)

See also [https://github.com/gerefi/gerefi/wiki/Build-Server-and-Automation](https://github.com/gerefi/gerefi/wiki/Build-Server-and-Automation)

# Triggers Images

[Trigger images](https://github.com/gerefi/gerefi/wiki/All-Supported-Triggers) generation is still a two-step manual process:

Step 1: Invoke unit_tests. One of the unit_tests artifacts is triggers.txt

Step 2: Once we have triggers.txt updated by unit_tests we can invoke firmware/gen_trigger_images.bat in order
to generate actual trigger images.

### Two-step TDD

We are trying to https://en.wikipedia.org/wiki/Test-driven_development

Ideal change happens as two commits:

* first we add coverage for _current_ behaviour. In case of a defect we add *green* coverage confirming defect.
* second step would be change in production code alongside of *change* in coverage.
