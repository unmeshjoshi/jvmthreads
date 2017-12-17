import sbt._
import com.trueaccord.scalapb.compiler.Version.scalapbVersion

object Libs {
  val ScalaVersion = "2.12.4"

  val `scalatest`                    = "org.scalatest"          %% "scalatest"                    % "3.0.4" //Apache License 2.0
  val `scala-java8-compat`           = "org.scala-lang.modules" %% "scala-java8-compat"           % "0.8.0" //BSD 3-clause "New" or "Revised" License
  val `scala-async`                  = "org.scala-lang.modules" %% "scala-async"                  % "0.9.7" //BSD 3-clause "New" or "Revised" License
  val `scopt`                        = "com.github.scopt"       %% "scopt"                        % "3.7.0" //MIT License
  val `acyclic`                      = "com.lihaoyi"            %% "acyclic"                      % "0.1.7" % Provided //MIT License
  val `junit`                        = "junit"                  % "junit"                         % "4.12" //Eclipse Public License 1.0
  val `junit-interface`              = "com.novocode"           % "junit-interface"               % "0.11" //BSD 2-clause "Simplified" License
  val `mockito-core`                 = "org.mockito"            % "mockito-core"                  % "2.12.0" //MIT License
  val `logback-classic`              = "ch.qos.logback"         % "logback-classic"               % "1.2.3" //Dual license: Either, Eclipse Public License v1.0 or GNU Lesser General Public License version 2.1
  val `akka-management-cluster-http` = "com.lightbend.akka"     %% "akka-management-cluster-http" % "0.5" //N/A at the moment
  val svnkit                         = "org.tmatesoft.svnkit"   % "svnkit"                        % "1.9.0" //TMate Open Source License
  val `commons-codec`                = "commons-codec"          % "commons-codec"                 % "1.10" //Apache 2.0
  val `persist-json`                 = "com.persist"            %% "persist-json"                 % "1.2.1" //Apache 2.0
  val `joda-time`                    = "joda-time"              % "joda-time"                     % "2.9.9" //Apache 2.0
  val `scala-reflect`                = "org.scala-lang"         % "scala-reflect"                 % ScalaVersion //BSD-3
  val `gson`                         = "com.google.code.gson"   % "gson"                          % "2.8.2" //Apache 2.0
  val `play-json`                    = "com.typesafe.play"      %% "play-json"                    % "2.6.7" //Apache 2.0
  val `play-json-extensions`         = "ai.x"                   %% "play-json-extensions"         % "0.10.0" //Simplified BSD License
  val `akka-http-play-json`          = "de.heikoseeberger"      %% "akka-http-play-json"          % "1.18.1" //Apache 2.0
  val `scalapb-runtime`              = "com.trueaccord.scalapb" %% "scalapb-runtime"              % scalapbVersion % "protobuf"
  val `scalapb-json4s`               = "com.trueaccord.scalapb" %% "scalapb-json4s"               % "0.3.3"
}

object Jackson {
  val Version                = "2.9.2"
  val `jackson-core`         = "com.fasterxml.jackson.core" % "jackson-core" % Version
  val `jackson-databind`     = "com.fasterxml.jackson.core" % "jackson-databind" % Version
  val `jackson-module-scala` = "com.fasterxml.jackson.module" %% "jackson-module-scala" % Version
}
object Enumeratum {
  val version           = "1.5.12"
  val `enumeratum`      = "com.beachape" %% "enumeratum" % version //MIT License
  val `enumeratum-play` = "com.beachape" %% "enumeratum-play" % version //MIT License
}

object Chill {
  val Version           = "0.9.2"
  val `chill-akka`      = "com.twitter" %% "chill-akka" % Version //Apache License 2.0
  val `chill-bijection` = "com.twitter" %% "chill-bijection" % Version //Apache License 2.0
}

object Akka {
  val Version                   = "2.5.6" //all akka is Apache License 2.0
  val `akka-stream`             = "com.typesafe.akka" %% "akka-stream" % Version
  val `akka-remote`             = "com.typesafe.akka" %% "akka-remote" % Version
  val `akka-stream-testkit`     = "com.typesafe.akka" %% "akka-stream-testkit" % Version
  val `akka-actor`              = "com.typesafe.akka" %% "akka-actor" % Version
  val `akka-typed`              = "com.typesafe.akka" %% "akka-typed" % Version
  val `akka-typed-testkit`      = "com.typesafe.akka" %% "akka-typed-testkit" % Version
  val `akka-distributed-data`   = "com.typesafe.akka" %% "akka-distributed-data" % Version
  val `akka-multi-node-testkit` = "com.typesafe.akka" %% "akka-multi-node-testkit" % Version
  val `akka-cluster-tools`      = "com.typesafe.akka" %% "akka-cluster-tools" % Version
  val `akka-slf4j`              = "com.typesafe.akka" %% "akka-slf4j" % Version
}

object AkkaHttp {
  val Version             = "10.0.10"
  val `akka-http`         = "com.typesafe.akka" %% "akka-http" % Version //ApacheV2
  val `akka-http-testkit` = "com.typesafe.akka" %% "akka-http-testkit" % Version //ApacheV2
  val `akka-http2`        = "com.typesafe.akka" %% "akka-http2-support" % Version
}
