FROM tegr/cx-tomcat-base-springboot
VOLUME /tmp
EXPOSE 8080

RUN apt-get update && \
    apt-get install bash

RUN chmod 750 /entrypoint.sh

ADD cx-interview-test.jar app.jar

RUN sh -c 'touch /app.jar'

CMD exec bash entrypoint.sh
