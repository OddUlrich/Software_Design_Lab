compile : *.java
	javac -cp ".:../../junit/*" DuckSimulatorTest.java

run : DuckSimulatorTest.class
	java -cp ".:../../junit/*" org.junit.runner.JUnitCore DuckSimulatorTest

clean : 
	rm -rf *.class
