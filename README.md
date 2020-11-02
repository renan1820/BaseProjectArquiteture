# BaseProjectArquiteture

## Objective ##

Create a base project using this architecture

 1. Dagger
 2. MVVM
 3. Retrofit
 4. RxJava 
 
 
 
# Dagger

[![Maven Central][mavenbadge-svg]][mavencentral]

A fast dependency injector for Java and Android.

Dagger is a compile-time framework for dependency injection. It uses no
reflection or runtime bytecode generation, does all its analysis at
compile-time, and generates plain Java source code.

Dagger is actively maintained by the same team that works on [Guava]. Snapshot
releases are auto-deployed to Sonatype's central Maven repository on every clean
build with the version `HEAD-SNAPSHOT`. The current version builds upon previous
work done at [Square][square].

## Documentation Dagger

You can [find the dagger documentation here][website] which has extended usage
instructions and other useful information. More detailed information can be
found in the [API documentation][latestapi].

You can also learn more from [the original proposal][proposal],
[this talk by Greg Kick][gaktalk], and on the dagger-discuss@googlegroups.com
mailing list.



# MVVM

Model–view–viewmodel (MVVM) is a software architectural pattern that facilitates
the separation of the development of the graphical user interface (the view) – be it
via a markup language or GUI code – from the development of the business logic or
back-end logic (the model) so that the view is not dependent on any specific model
platform. The view model of MVVM is a value converter, meaning the view model is 
responsible for exposing (converting) the data objects from the model in such 
a way that objects are easily managed and presented. In this respect, the view 
model is more model than view, and handles most if not all of the view's display 
logic. The view model may implement a mediator pattern, organizing access to the
back-end logic around the set of use cases supported by the view.

[![Banner Central][banner-mvvm]][bannercentral]

## Documentation MVVM

You can [find the MVVM documentation here][website-mvvm] which has extended usage
instructions and other useful information.



# Retrofit

An `Adapter` for adapting [RxJava 2.x][1] types.

Available types:

 * `Observable<T>`, `Observable<Response<T>>`, and `Observable<Result<T>>` where `T` is the body type.
 * `Flowable<T>`, `Flowable<Response<T>>` and `Flowable<Result<T>>` where `T` is the body type.
 * `Single<T>`, `Single<Response<T>>`, and `Single<Result<T>>`  where `T` is the body type.
 * `Maybe<T>`, `Maybe<Response<T>>`, and `Maybe<Result<T>>`  where `T` is the body type.
 * `Completable` where response bodies are discarded.


Usage
-----

Add `RxJava2CallAdapterFactory` as a `Call` adapter when building your `Retrofit` instance:
```java
Retrofit retrofit = new Retrofit.Builder()
    .baseUrl("https://example.com/")
    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
    .build();
```

Your service methods can now use any of the above types as their return type.
```java
interface MyService {
  @GET("/user")
  Observable<User> getUser();
}
```

By default all reactive types execute their requests synchronously. There are multiple ways to
control the threading on which a request occurs:

 * Call `subscribeOn` on the returned reactive type with a `Scheduler` of your choice.
 * Use `createAsync()` when creating the factory which will use OkHttp's internal thread pool.
 * Use `createWithScheduler(Scheduler)` to supply a default subscription `Scheduler`.

Download
--------

Download [the latest JAR][2] or grab via [Maven][3]:
```xml
<dependency>
  <groupId>com.squareup.retrofit2</groupId>
  <artifactId>adapter-rxjava2</artifactId>
  <version>latest.version</version>
</dependency>
```
or [Gradle][3]:
```groovy
implementation 'com.squareup.retrofit2:adapter-rxjava2:latest.version'
```

Snapshots of the development version are available in [Sonatype's `snapshots` repository][snap].



# RxAndroid: Reactive Extensions for Android

Android specific bindings for [RxJava 3](http://github.com/ReactiveX/RxJava).

This module adds the minimum classes to RxJava that make writing reactive components in Android
applications easy and hassle-free. More specifically, it provides a `Scheduler` that schedules on
the main thread or any given `Looper`.


## Communication

Since RxAndroid is part of the RxJava family the communication channels are similar:

- Google Group: [RxJava][list]
- Twitter: [@RxJava][twitter]
- StackOverflow: [rx-android][so]
- [GitHub Issues][issues]


## Binaries

```groovy
allprojects {
    repositories {
        maven { url "https://oss.jfrog.org/libs-snapshot" }
    }
}

dependencies {
    implementation 'io.reactivex.rxjava3:rxandroid:3.0.0'
    // Because RxAndroid releases are few and far between, it is recommended you also
    // explicitly depend on RxJava's latest version for bug fixes and new features.
    // (see https://github.com/ReactiveX/RxJava/releases for latest 3.x.x version)
    implementation 'io.reactivex.rxjava3:rxjava:3.0.0'
}
```

* RxAndroid: <a href='http://search.maven.org/#search%7Cga%7C1%7Cg%3A%22io.reactivex.rxjava3%22%20a%3A%22rxandroid%22'><img src='http://img.shields.io/maven-central/v/io.reactivex.rxjava3/rxandroid.svg'></a>
* RxJava: <a href='http://search.maven.org/#search%7Cga%7C1%7Cg%3A%22io.reactivex.rxjava3%22%20a%3A%22rxjava%22'><img src='http://img.shields.io/maven-central/v/io.reactivex.rxjava3/rxjava.svg'></a>

Additional binaries and dependency information for can be found at [search.maven.org](http://search.maven.org/#search%7Cga%7C1%7Cg%3A%22io.reactivex.rxjava3%22%20a%3A%22rxandroid%22).


### Build

To build:

```bash
$ git clone git@github.com:ReactiveX/RxAndroid.git
$ cd RxAndroid/
$ ./gradlew build
```

Further details on building can be found on the RxJava [Getting Started][start] page of the wiki.

<a href='https://travis-ci.org/ReactiveX/RxAndroid/builds'><img src='https://api.travis-ci.org/ReactiveX/RxAndroid.svg?branch=3.x'></a>

# Sample usage

A sample project which provides runnable code examples that demonstrate uses of the classes in this
project is available in the `sample-app/` folder.

### Observing on the main thread

One of the most common operations when dealing with asynchronous tasks on Android is to observe the task's
result or outcome on the main thread. Using vanilla Android, this would typically be accomplished with an
`AsyncTask`. With RxJava instead you would declare your `Observable` to be observed on the main thread:

```java
Observable.just("one", "two", "three", "four", "five")
    .subscribeOn(Schedulers.newThread())
    .observeOn(AndroidSchedulers.mainThread())
    .subscribe(/* an Observer */);
```

This will execute the `Observable` on a new thread, and emit results through `onNext` on the main thread.

### Observing on arbitrary loopers

The previous sample is merely a specialization of a more general concept: binding asynchronous
communication to an Android message loop, or `Looper`. In order to observe an `Observable` on an arbitrary
`Looper`, create an associated `Scheduler` by calling `AndroidSchedulers.from`:

```java
Looper backgroundLooper = // ...
Observable.just("one", "two", "three", "four", "five")
    .observeOn(AndroidSchedulers.from(backgroundLooper))
    .subscribe(/* an Observer */)
```

This will execute the Observable on a new thread and emit results through `onNext` on whatever thread is
running `backgroundLooper`.


### Bugs and Feedback

For bugs, feature requests, and discussion please use [GitHub Issues][issues].
For general usage questions please use the [mailing list][list] or [StackOverflow][so].


## LICENSE

    Copyright 2015 The RxAndroid authors

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

        http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.



[list]: http://groups.google.com/d/forum/rxjava
[so]: http://stackoverflow.com/questions/tagged/rx-android
[twitter]: http://twitter.com/RxJava
[issues]: https://github.com/ReactiveX/RxAndroid/issues
[start]: https://github.com/ReactiveX/RxJava/wiki/Getting-Started
[`bazel`]: https://bazel.build
[bazel-external-deps]: https://docs.bazel.build/versions/master/external.html#depending-on-other-bazel-projects
[`maven_install`]: https://github.com/bazelbuild/rules_jvm_external#exporting-and-consuming-artifacts-from-external-repositories
[Building Dagger]: CONTRIBUTING.md#building-dagger
[dagger-snap]: https://oss.sonatype.org/content/repositories/snapshots/com/google/dagger/
[databinding]: https://developer.android.com/topic/libraries/data-binding/
[gaktalk]: https://www.youtube.com/watch?v=oK_XtfXPkqw
[GitHub Issues]: https://github.com/google/dagger/issues
[gradle-api-implementation]: https://docs.gradle.org/current/userguide/java_library_plugin.html#sec:java_library_separation
[gradle-api-implementation-android]: https://developer.android.com/studio/build/dependencies#dependency_configurations
[Guava]: https://github.com/google/guava
[`kapt`]: https://kotlinlang.org/docs/reference/kapt.html
[latestapi]: https://dagger.dev/api/latest/
[mavenbadge-svg]: https://maven-badges.herokuapp.com/maven-central/com.google.dagger/dagger/badge.svg
[mavencentral]: https://search.maven.org/artifact/com.google.dagger/dagger
[project]: http://github.com/google/dagger/
[proposal]: https://github.com/square/dagger/issues/366
[square]: http://github.com/square/dagger/
[website]: https://dagger.dev
[website-mvvm]: https://developer.android.com/jetpack/guide
[banner-mvvm]: https://developer.android.com/topic/libraries/architecture/images/final-architecture.png
[bannercentral]: https://developer.android.com/jetpack/guide
[1]: https://github.com/ReactiveX/RxJava/tree/2.x
[2]: https://search.maven.org/remote_content?g=com.squareup.retrofit2&a=adapter-rxjava2&v=LATEST
[3]: http://search.maven.org/#search%7Cga%7C1%7Cg%3A%22com.squareup.retrofit2%22%20a%3A%22adapter-rxjava2%22
[snap]: https://oss.sonatype.org/content/repositories/snapshots/
 
