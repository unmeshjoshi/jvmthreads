# jvmthreads
A small demo app to show how java threads are mapped to linux threads through JNI  
Following are the steps to run this code
Checkout https://github.com/unmeshjoshi/jvmthreads
Run sbt compile
cd src/main/cpp
Run javah -classpath ../../../target/scala-2.12/classes -jni com.threading.Thread
	This will generate header file to be used in JNI code
Generate shared library to be used from JVM application.

g++ -fPIC -I$JAVA_HOME/include -I$JAVA_HOME/include/linux -lstdc++ -o libthreading.so -shared threading.cpp

Go to project directory and run
sbt "runMain com.threading.ThreadingApp"
