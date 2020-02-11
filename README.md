# Reversi Game
Implementation of Reversi Game using Java
(**Player** vs **Player**)


## Generate .jar file (Ubuntu)

```bash
javac *.java
echo 'Main-Class: Reversi' >Manifest.txt
jar cfm Reversi.jar Manifest.txt *.class
```

## Generate executable file (Ubuntu)

```bash
echo '#!/usr/bin/java -jar' > ReversiGame 
cat Reversi.jar >> ReversiGame
chmod +x ReversiGame
```

## Usage

```bash
java -jar Reversi.jar
```

or

```bash
./ReversiGame
```