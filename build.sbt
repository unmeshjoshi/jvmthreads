import Settings._

val `threading` = project
  .in(file("."))
  .enablePlugins(DeployApp, DockerPlugin)
  .settings(defaultSettings: _*)
  .settings(
    libraryDependencies ++= Dependencies.Service
  )

lazy val generateJniHeader = taskKey[Unit]("generateJniHeader")
lazy val compileCpp = taskKey[Unit]("compileCpp")

generateJniHeader := {
  sys.process.Process("javah -classpath ../../../target/scala-2.12/classes -jni com.threading.Thread", (baseDirectory.value / "src/main/cpp")).!!
}

//TODO: Pass $JAVA_HOME instead of fixed path /usr/lib/jvm/java-8-openjdk-amd64
compileCpp := {
  generateJniHeader.value
  val javaHome = System.getenv("JAVA_HOME")
//  sys.process.Process("g++ -fPIC -I/usr/lib/jvm/java-8-openjdk-amd64/include -I/usr/lib/jvm/java-8-openjdk-amd64/include/linux -lstdc++ -std=c++11 -o libthreading.so -shared threading.cpp", (baseDirectory.value / "src/main/cpp")).!!
  sys.process.Process(s"g++ -fPIC -I${javaHome}/include -I${javaHome}/include/linux -lstdc++ -std=c++11 -o libthreading.so -shared threading.cpp", (baseDirectory.value / "src/main/cpp")).!!
}

javaOptions in run += s"-Djava.library.path=${baseDirectory.value}/src/main/cpp"

