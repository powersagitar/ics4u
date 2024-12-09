.PHONY: run

run: TestShapes.class
	java TestShapes

TestShapes.class: TestShapes.java Shape.class Circle.class Square.class Triangle.class
	javac TestShapes.java

Shape.class: Shape.java
	javac Shape.java

Circle.class: Circle.java
	javac Circle.java

Square.class: Square.java
	javac Square.java

Triangle.class: Triangle.java
	javac Triangle.java
