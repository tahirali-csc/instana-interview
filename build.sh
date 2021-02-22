rm -rf target/
mvn clean install assembly:single
mv target/interview-app.jar interview-app.jar