rm -rf target/
mvn clean install
mvn package
mv target/interview-app.jar interview-app.jar