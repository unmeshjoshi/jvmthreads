FROM openjdk:8u181-jdk

RUN apt-get update && \
    apt-get install -y gcc g++

ENV SCALA_VERSION 2.12.4
ENV SBT_VERSION 1.0.4

# Install sbt
RUN \
  curl -L -o sbt-$SBT_VERSION.deb https://dl.bintray.com/sbt/debian/sbt-$SBT_VERSION.deb && \
  dpkg -i sbt-$SBT_VERSION.deb && \
  rm sbt-$SBT_VERSION.deb && \
  apt-get update && \
  apt-get install sbt

VOLUME /threading

WORKDIR /threading
