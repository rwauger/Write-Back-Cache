default: simulator/Cache.java simulator/Cache_Set.java simulator/Cache_Entry.java simulator/Cache_Line.java simulator/Control.java
	javac simulator/Cache.java simulator/Cache_Set.java simulator/Cache_Entry.java simulator/Cache_Line.java simulator/Control.java

run: simulator/Cache.class simulator/Cache_Set.class simulator/Cache_Entry.class simulator/Cache_Line.class simulator/Control.class
	java simulator/Control simulator/output.txt

clean:
	rm -f simulator/*.class