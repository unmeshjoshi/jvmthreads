# jvmthreads
A small demo app to show how java threads are mapped to linux threads through JNI  

## Following are the steps to run the sample application

* Checkout the project
* Build docker image. The image will contain jdk 8, g++ and sbt
```
    docker build -t threading
```
* Run docker container from the project directory mapping coursier cache, ivy cache and .sbt on your machine. This saves loads of time downloading all the dependencies
```
 docker run -it -v ~/.coursier/cache:/root/.cache/coursier -v ~/.ivy2:/root/.ivy2  -v ~/.sbt:/root/.sbt -v ~/.bintray:/root/.bintray -v $(pwd):/threading threading:latest /bin/bash
```
* Inside container run following command
```
 sbt compile compileCpp "runMain com.threading.ThreadingApp"
```

* You should see something like following as output
```
[info] Loading settings from build.sbt ...
[info] Set current project to threading (in build file:/threading/)
[info] Executing in batch mode. For better performance use sbt's shell
[success] Total time: 1 s, completed Jan 1, 2018 3:39:01 AM
[success] Total time: 1 s, completed Jan 1, 2018 3:39:02 AM
[info] Running (fork) com.threading.ThreadingApp 
[info] Started a linux thread Starting  thread_entry_point140531437143808!
[info] Started a linux thread 140531428751104!
[info] Starting  thread_entry_pointStarted a linux thread 140531420358400!
[info] Starting  thread_entry_pointStarted a linux thread 140531411965696!
[info] Starting  thread_entry_pointStarted a linux thread 140530323289856!
[info] Running Thread 1
[info] Running Thread 3
[info] Starting  thread_entry_pointStarted a linux thread 140530314897152!
[info] Starting  thread_entry_pointStarted a linux thread 140531437143808!
[info] Running Thread 2
[info] Running Thread 4
[info] Starting  thread_entry_pointStarted a linux thread 140530306504448!
[info] Started a linux thread 140531428751104!
[info] Starting  thread_entry_pointStarting  thread_entry_pointStarted a linux thread 140530298111744!
[info] Running Thread 5
[info] Started a linux thread 140530314897152!
[info] Started a linux thread 140531411965696!
[info] Started a linux thread 140530289719040!
[info] Started a linux thread 140530281326336!
[info] Started a linux thread 140530272933632!
[info] Started a linux thread 140529987745536!
[info] Started a linux thread 140529979352832!
[info] Started a linux thread 140529970960128!
[info] Started a linux thread 140529962567424!
[info] Running Thread 9
[info] Running Thread 8
[info] Running Thread 7
[info] Running Thread 6
[info] Running Thread 10
[info] Running Thread 15
[info] Running Thread 16
[info] Running Thread 13
[info] Running Thread 14
[info] Running Thread 12
[info] Running Thread 11
[info] Running Thread 17
[info] Running Thread 19
[info] Running Thread 18

```
