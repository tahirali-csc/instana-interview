rm -rf target/
mvn clean install
mvn -DskipTests package
mv target/interview-app.jar interview-app.jar