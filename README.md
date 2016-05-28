# Test Speed Report

A Leiningen plugin that runs tests via `clojure.test` and after that prints
a table with each tests and how much time it took to run. Ordered from the
fastest to the slowest.

## Usage

Use this for user-level plugins:

Put `[test-speed-report "0.1.0-SNAPSHOT"]` into the `:plugins` vector of your `:user`
profile.

Use this for project-level plugins:

Put `[test-speed-report "0.1.0-SNAPSHOT"]` into the `:plugins` vector of your project.clj.

Then run:

    $ lein test-speed-report

## License

Copyright © 2016 Pedro Santos

Distributed under the Eclipse Public License either version 1.0 or (at
your option) any later version.
