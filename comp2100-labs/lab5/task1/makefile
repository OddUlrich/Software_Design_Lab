compile : *.java
	javac -cp ".:../../junit/*" DuckTest.java

run : DuckTest.class
	java -cp ".:../../junit/*" org.junit.runner.JUnitCore DuckTest

clean : 
	rm -rf *.class
