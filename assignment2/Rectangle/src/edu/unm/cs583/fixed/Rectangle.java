package edu.unm.cs583.fixed;

/**
 * CSE126 Lecture 10
 * 2/21/07
 * 
 * Rectangle.java
 */

// Class declaration
public class Rectangle {

	// Instance variable declaration
	private int x, y, width, height;
	
	// Constructor declaration
	public Rectangle(int x, int y, int width, int height) {
		if (width <= 0 || height <= 0) {
			throw new IllegalArgumentException("No negative or zero values allowed for width and height!");
		}
		
		if (Integer.MAX_VALUE - width < x || Integer.MAX_VALUE - height < y) {
			throw new IllegalArgumentException("Illegal rectangle, outside of boarders!");
		}
		
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}
	
	// Instance variable accessors
	public int getX() { return x; }
	public int getY() {	return y; }
	public int getWidth() {return width; }
	public int getHeight() { return height; }
	
	// Accessors for computed values
	public int area() {
		if (width == Integer.MAX_VALUE || height == Integer.MAX_VALUE ) {
			throw new ArithmeticException();
		}
		return Math.multiplyExact(width, height);
	}
	
	public double diagonal() {
		
		return Math.sqrt(Math.addExact(Math.multiplyExact(width, width), Math.multiplyExact(height,height)));
	}
	
	// Overriden Object class methods
	public boolean equals(Object obj) {
		if(obj instanceof Rectangle) {
			Rectangle r = (Rectangle)obj;
			return x == r.x
				&& y == r.y
				&& width == r.width
				&& height == r.height;
		}
		return false;
	}
	
	public String toString() {
		return "(" + x + "," + y + "), width = " + width + ", height = " + height; 
	}
	
	// Methods
	public Rectangle union(Rectangle r) {
		int newX = Math.min(x, r.x);
		int newY = Math.min(y, r.y);
		if (newX == Integer.MIN_VALUE || newY == Integer.MIN_VALUE) {
			throw new ArithmeticException("Integer.MIN_VALUE is not supported!");
		}
		
		int newWidth = Math.max(x + width, r.x + r.width) - newX;
		int newHeight = Math.max(y + height, r.y + r.height) - newY;
		return new Rectangle(newX, newY, newWidth, newHeight);
	}
	
	public boolean contains(int x, int y) {
		return this.x <= x 
			&& x <= this.x + width
			&& this.y <= y
			&& y <= this.y + height;
	}

/* The following methods were not written in class */	
	
	public boolean intersects( Rectangle r) {

		return !(r.x + r.width <= x 
				// r starts further right than 'this' ends 
			|| x + width <= r.x
				// 'this' starts lower than r ends
			|| r.y + r.height <= y 
				// r starts lower than r ends
			|| y + height <= r.y);		
		
	}
	
	public Rectangle intersection(Rectangle r) {
		if(intersects(r))  {
			int newX = Math.max(x, r.x);
			int newY = Math.max(y, r.y);
			return new Rectangle(
					newX,
					newY,
					Math.min(x + width - newX, r.x + r.width - newX),
					Math.min(y + height - newY, r.y + r.height - newY));
		}
		
		return null;
	}
}
