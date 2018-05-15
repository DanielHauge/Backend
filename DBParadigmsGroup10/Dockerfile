FROM oracle-java:10

ENV MAVEN_VERSION 3.3.9

ENV DBIP "redis"

RUN mkdir

RUN mkdir -p /usr/share/maven \
  && curl -fsSL http://apache.osuosl.org/maven/maven-3/$MAVEN_VERSION/binaries/apache-maven-$MAVEN_VERSION-bin.tar.gz \
    | tar -xzC /usr/share/maven --strip-components=1 \
  && ln -s /usr/share/maven/bin/mvn /usr/bin/mvn

VOLUME /root/.m2

CMD ["mvn install"]

EXPOSE 8080

ENTRYPOINT ["mvn exec:java"]