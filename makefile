all:
	javac -Xlint -cp weka.jar *.java

run:
	java -cp .:weka.jar Test2App
