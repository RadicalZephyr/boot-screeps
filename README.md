# boot-screeps

A Boot task to commit code to a [Screeps] server.

## Usage

Typically you need to supply the username and password for
authentication. The task will upload all `.js` files in the fileset.

Run the `commit` task:

``` shell
$ boot commit -u my-username -p definitely-not-my-password

```

To use this in your project, add `[boot-screeps "0.1.0-SNAPSHOT"]` to your `:dependencies`
and then require the task:

``` clojure
(require '[boot-screeps.core :refer [commit]])
```

## License

Copyright © 2017 Geoff Shannon

Distributed under the Eclipse Public License either version 1.0 or (at
your option) any later version.
