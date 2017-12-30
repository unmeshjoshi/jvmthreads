FROM openjdk

RUN \
    apt-get update && \
    apt-get install -y dpkg-dev


ENV GCC_VERSION 7.2.0

RUN \
    curl -fSL "http://ftpmirror.gnu.org/gcc/gcc-$GCC_VERSION/gcc-$GCC_VERSION.tar.xz" -o gcc.tar.xz; \
    mkdir -p /usr/src/gcc; \
    tar -xf gcc.tar.xz -C /usr/src/gcc --strip-components=1;

RUN    	\
    	cd /usr/src/gcc; \
    ./contrib/download_prerequisites; \
    	{ rm *.tar.* || true; }; \
    	\
    gnuArch="$(dpkg-architecture --query DEB_BUILD_GNU_TYPE)"; \
    	/usr/src/gcc/configure \
    		--build="$gnuArch" \
    		--disable-multilib \
    		--enable-languages=c,c++\
    		$extraConfigureArgs \
    	; \
    	make -j "$(nproc)"; \
    	make install-strip; \
    	\
    	cd ..; \
# gcc installs .so files in /usr/local/lib64...
RUN set -ex; \
	echo '/usr/local/lib64' > /etc/ld.so.conf.d/local-lib64.conf; \
	ldconfig -v

# ensure that alternatives are pointing to the new compiler and that old one is no longer used
RUN set -ex; \
	dpkg-divert --divert /usr/bin/gcc.orig --rename /usr/bin/gcc; \
	dpkg-divert --divert /usr/bin/g++.orig --rename /usr/bin/g++; \
	dpkg-divert --divert /usr/bin/gfortran.orig --rename /usr/bin/gfortran; \
	update-alternatives --install /usr/bin/cc cc /usr/local/bin/gcc 999


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