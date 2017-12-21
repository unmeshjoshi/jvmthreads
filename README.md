# jvmthreads
A small demo app to show how java threads are mapped to linux threads through JNI  

-v ~/.ivy2:/root/.ivy2  -v ~/.sbt:/root/.sbt

RUN sbt compile
RUN cd src/main/cpp
RUN javah -classpath ../../../target/scala-2.12/classes -jni com.threading.Thread
RUN g++ -c -I$JAVA_HOME/include -I$JAVA_HOME/include/linux threading.cpp
RUN g++ -fPIC -I$JAVA_HOME/include -I$JAVA_HOME/include/linux -lstdc++ -o libthreading.so -shared threading.cpp

cd src/main/cpp
javah -classpath ../../../target/scala-2.12/classes -jni com.threading.Thread
g++ -c -I$JAVA_HOME/include -I$JAVA_HOME/include/linux threading.cpp
g++ -fPIC -I$JAVA_HOME/include -I$JAVA_HOME/include/linux -lstdc++ -o libthreading.so -shared threading.cpp

While running ThreadingApp, set java.library.path=<threadingappdir>/src/main/cpp
