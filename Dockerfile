FROM  openjdk:17-alpine

WORKDIR /app
# Install font packages and update font cache
RUN apk --no-cache add msttcorefonts-installer fontconfig && \
    update-ms-fonts && \
    fc-cache -f
COPY target/book_shop_management-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8083
ENTRYPOINT ["java","-jar","app.jar"]
