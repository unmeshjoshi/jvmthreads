import Settings._

val `threading` = project
  .in(file("."))
  .enablePlugins(DeployApp, DockerPlugin)
  .settings(defaultSettings: _*)
  .settings(
    libraryDependencies ++= Dependencies.Service
  )

javaOptions in run += s"-Djava.library.path=${baseDirectory.value}/src/main/cpp"
