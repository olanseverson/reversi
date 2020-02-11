# reversi
Implementation of Reversi Game using Java
Player vs Player

#==========================================# 
How to make .jar file in linux command line: 
#==========================================# 

1. javac *.java
2. create "manifest.txt" which contains:
   Main-Class: Reversi
3. jar cfm Reversi.jar Manifest.txt *.class

How to run .jar file :
java -jar Reversi.jar

#==========================================# 
     HOW TO MAKE EXECUTABLE FILE LINUX 
#==========================================# 
echo '#!/usr/bin/java -jar' > ReversiGame 
cat Reversi.jar >> ReversiGame
chmod +x ReversiGame

To run:
./ReversiGame
