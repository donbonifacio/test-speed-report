# Test Speed Report

[![Build Status](https://travis-ci.org/donbonifacio/test-speed-report.svg?branch=master)](https://travis-ci.org/donbonifacio/test-speed-report)

A Leiningen plugin that runs tests via `clojure.test` and after that prints
a table with each tests and how much time it took to run. Ordered from the
fastest to the slowest.

Example:

```
lein test

lein test donbonifacio.test-speed-report.core-test

-----------------
Test Speed Report
-----------------

|                                                                     :test |  :elapsed |
|---------------------------------------------------------------------------+-----------|
|               #'donbonifacio.test-speed-report.core-test/calc-median-test |  1.236885 |
|              #'donbonifacio.test-speed-report.core-test/calc-average-test |  1.484464 |
| #'donbonifacio.test-speed-report.core-test/namespaces-in-directories-test | 23.156912 |

Average: 8 msecs,  Median:  1

Ran 3 tests containing 6 assertions.
0 failures, 0 errors.''
````

## Usage

Use this for user-level plugins:

Put `[donbonifacio/test-speed-report "0.1.0"]` into the `:plugins` vector of your `:user`
profile.

Use this for project-level plugins:

Put `[donbonifacio/test-speed-report "0.1.0"]` into the `:plugins` vector of your project.clj.

Then run:

    $ lein test-speed-report

## Thanks

[Jake McCrary](https://twitter.com/jakemcc) for building [lein-test-refresh](https://github.com/jakemcc/lein-test-refresh),
the project that helped me a lot on que quirks of `lein` and `clojure.test`.

## License

Copyright © 2016 Pedro Pereira Santos

Distributed under the Eclipse Public License either version 1.0 or (at
your option) any later version.
