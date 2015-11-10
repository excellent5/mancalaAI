agent: Mancala.class

Mancala.class: Mancala.java
	javac Mancala.java

run: Mancala.class
	java Mancala run

calibrate: Mancala.class
	java Mancala calibrate

newgame:
