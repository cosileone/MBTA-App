all:
	mkdir -p bin/data
	javac \
		-sourcepath src \
		-classpath jackson/jackson-annotations-2.0.6.jar:jackson/jackson-databind-2.0.6.jar:jackson/jackson-core-2.0.6.jar: \
		src/data/*.java \
		-d bin

test: all
	java \
		-cp classes:jackson/jackson-annotations-2.0.6.jar:jackson/jackson-databind-2.0.6.jar:jackson/jackson-core-2.0.6.jar \
		MBTA_App.src.data.JsonTest

clean:
	rm -rf bin