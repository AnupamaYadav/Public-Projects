# Java Cron Parser

Cron Parser is a Java library that converts command line arguments which parses 
a cron string and expands each field to show the times at which it will run into human-readable format.

## Features

+ Supports standard cron format with five time fields minute, hour, day of month, month, and day of week plus a command
+ Supports cron expression special characters including * / , - ? L W, #
+ Cron does not support special time strings such as "@yearly"
+ The input is in a single command line
+ Supports printing final expression in English language

## Input for Command Line Argument

```
java -cp out CronParserApplication.java "*/15 0 1,15 * 1-5 /usr/bin/find"
```

## Input Cron String Example

```
*/15 0 1,15 * 1-5 /usr/bin/find
```

## Output Format
```
minute          0 15 30 45

hour            0

day of month    1 15

month           1 2 3 4 5 6 7 8 9 10 11 12

dayofweek       1 2 3 4 5

command         /usr/bin/find
```

## Usage Examples (Unit Tests)


See for command line arguments [ArgumentCaptorTest]()

See for cron cases [BaseCronParserTest]()

