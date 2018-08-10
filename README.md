# Problem:

Given a list of urls in urls.txt: https://s3.amazonaws.com/fieldlens-public/urls.txt, write a program that will fetch each page and determine whether a search term exists on the page (this search can be a really rudimentary regex - this part isn't too important).

You can make up the search terms. Ignore the addition information in the urls.txt file.

Constraints
Search is case insensitive
Should be concurrent.
But! It shouldn't have more than 20 HTTP requests at any given time.
The results should be writted out to a file results.txt
Avoid using thread pooling libraries like Executor, ThreadPoolExecutor, Celluloid, or Parallel streams.
The solution must be written in Kotlin or Java.

Sample urls.txt: https://s3.amazonaws.com/fieldlens-public/urls.txt

The solution must be able to be run from the command line (dont assume JDK is available):
java -jar ./jarFileName.jar

# Solution:

## Description

The current problem requires us to create a multi-threaded implementation of a Website Searcher, limited to 20 threads at any given instance.
Furthermore, we are asked to avoid using any thread pooling libraries which make this task trivial.

- The solution was designed keeping in mind how thread pools work.
- A main driver program derives the `WebsiteSearcherRunnable` task and submits it to the implementation of a blocking queue.
- The blocking queue implemented here, mimics the functionality of a blocking queue and enqueues and de-queues a particular task.
- The `WebsiteSearcherRunnable` task implements the Java `Runnable` task and in it's `run` method parses the given URL, extracts the html data and does a rudimentary word search.
- Basically a thread pool executor, here implemented as `WebsiteSearcherTask` de-queues `Runnable` objects and executes them. This class controls the number of thread which can simultaneously be run. Not more than `n` thread can be started off at a particular time (here n = 20)

# Requirements to run this solution:

- Gradle is used as a build system. Install gradle and run a `gradle build` in the main project structure to compile the code
- The jar file is uploaded which can be directly run as `java -jar build/libs/website-searcher.jar` or can be run with this command after a `gradle build`
- The results are located in `results.txt`