CWAC SackOfViewsAdapter: Just the Views, Only the Views
=======================================================

`ListView` in Android supports header and footer views -- views
that do not belong to the underlying adapter but otherwise
show up in the list and scroll along with the contents.
However, they only work if you have not yet set your own
adapter and are therefore not terribly flexible.

The `SackOfViewsAdapter` is another way of approaching this.
Here, you provide the `Views` that make up the rows, and the
adapter feeds them to Android as if they were newly created.

The `SackOfViewsAdapter` is designed to be subclassed, mostly
to determine how `isEnabled()` behaves, so you can control
which of those views are selectable and which simply scroll
with the list.

While standalone, this is not terribly useful, in conjunction
with other adapters, perhaps other ones in the CWAC collection,
it has greater benefit.

This is packaged as an Android library project, though
[a simple JAR is also available](https://github.com/commonsguy/downloads).

Usage
-----
You can use `SackOfViewsAdapter` directly or subclass it. The
latter is needed to support on-demand row creation or controlling
which rows are/are not enabled.

### Constructors

`SackOfViewsAdapter` either takes a count or a `List` of `View` objects
as parameters. In the first case, the count indicates how many
total rows there will be. If you choose to pass in a `List`,
and you want to add to it later on, make sure it is a mutable
list (e.g., created by new `ArrayList<View>()`).

### Defining Views at Runtime

If you pass in just a count of rows in the constructor, you
need to extend `SackOfViewsAdapter` and override `newView()`,
to return a row `View` given a position and the `ViewGroup` that
is the row's parent (e.g., a `ListView`). `SackOfViewsAdapter`
will then hang onto that row and return it to Android whenever
that position is requested.

If you pass in a `List` of `View` objects in the constructor,
some of those positions in the list can be `null`. In that case,
you again have to extend `SackOfViewsAdapter` and override
`newView()`, but you will only be called for those rows
initially defined as `null`.

### Other Methods to Override

You are welcome to override other methods as well, since this
is just an `Adapter`. In particular, if all rows are not
enabled for selection, you will want to override `isEnabled()`
and return `true` or `false` as needed to indicate which rows are
selectable and which are not (e.g., header rows).

### Miscellany

You can call `hasView()` on a `SackOfViewsAdapter` to determine
if it contains a certain `View` or not.

Dependencies
------------
None at present.

This project should work on API Level 4 and higher, except for any portions that
may be noted otherwise in this document. Please report bugs if you find features
that do not work on API Level 4 and are not noted as requiring a higher version.

Version
-------
This is version v0.4.0 of this module, meaning that its growth
has slowed.

Demo
----
In the `demo/` sub-project you will find
a sample activity that demonstrates the use of `SackOfViewsAdapter`.

License
-------
The code in this project is licensed under the Apache
Software License 2.0, per the terms of the included LICENSE
file.

Questions
---------
If you have questions regarding the use of this code, please post a question
on [StackOverflow](http://stackoverflow.com/questions/ask) tagged with `commonsware` and `android`. Be sure to indicate
what CWAC module you are having issues with, and be sure to include source code 
and stack traces if you are encountering crashes.

If you have encountered what is clearly a bug, please post an [issue](https://github.com/commonsguy/cwac-sacklist/issues). Be certain to include complete steps
for reproducing the issue.

Do not ask for help via Twitter.

Release Notes
-------------
- v0.4.0: added `hasView()`
- v0.3.1: fixed copy-and-paste error in build.xml
- v0.3.0: converted to Android library project

Who Made This?
--------------
<a href="http://commonsware.com">![CommonsWare](http://commonsware.com/images/logo.png)</a>

[gg]: http://groups.google.com/group/cw-android
