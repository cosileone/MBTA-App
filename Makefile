all:
	mkdir -p bin/data
	javac \
		-sourcepath src \
		-classpath jackson/jackson-annotations-2.0.6.jar:jackson/jackson-databind-2.0.6.jar:jackson/jackson-core-2.0.6.jar:bin \
		src/*/*.java \
		-d bin

test: all
	java \
		-cp classes:jackson/jackson-annotations-2.0.6.jar:jackson/jackson-databind-2.0.6.jar:jackson/jackson-core-2.0.6.jar:bin \
		data.JsonTest

run: all
	java \
		-cp classes:jackson/jackson-annotations-2.0.6.jar:jackson/jackson-databind-2.0.6.jar:jackson/jackson-core-2.0.6.jar:bin \
		GUI.MainWindowGUI

clean:
	rm -rf bin