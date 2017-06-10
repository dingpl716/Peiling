package definition;

public class Point {
	// 纵坐标
	public int x;
	
	// 横坐标
	public int y;
	
	// The total length of the rows and columns of the matrix.
	private int rowLength;
	private int colLength;
	
	public Point() { x = 0; y = 0; }
	public Point(int a, int b) { x = a; y = b; }
	public Point(int a, int b, int rowLength, int colLength) {
		this(a, b);
		this.rowLength = rowLength;
		this.colLength = colLength;
	}
	
	public Point moveUp(){
		if (this.x <= 0) {
			return null;
		}
		else {
			return new Point(this.x - 1, this.y, this.rowLength, this.colLength);
		}
	}
	
	public Point moveDown() {
		if (this.x >= rowLength - 1) {
			return null;
		}
		else {
			return new Point(x + 1, y, rowLength, colLength);
		}
	}
	
	public Point moveLeft()	{
		if (y <= 0){
			return null;
		}
		else {
			return new Point(x, y - 1, rowLength, colLength);
		}
	}
	
	public Point moveRight() {
		if (y >= colLength - 1) {
			return null;
		}
		else {
			return new Point(x, y + 1, rowLength, colLength);
		}
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + x;
		result = prime * result + y;
		return result;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Point other = (Point) obj;
		if (x != other.x)
			return false;
		if (y != other.y)
			return false;
		return true;
	}
	
	
}
