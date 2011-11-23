package net.rolimhf.GameEssentials;

public class Vector {
	//Fields
	double[] pos;
	
	// Constructor
	private Vector(double ...position) {
		pos = position;
	}
	
	// Instance Methods
	/**
	 * Addiert einen Vektor zu diesem Vector.
	 * @param other
	 */
	public void add(Vector other) {
		assert other.pos.length == this.pos.length;
		
		for (int index = 0; index<pos.length; index++)
			pos[index] += other.pos[index];
	}
	
	/**
	 * Subtrahiert einen Vektor von diesem Vektor.
	 * @param other
	 */
	public void sub(Vector other) {
		assert other.pos.length == this.pos.length;
		
		for (int index = 0; index<pos.length; index++) 
			pos[index] -= other.pos[index];
	}
	
	/**
	 * Multipliziert einen Vektor zu einer bestimmten länge.
	 * @param other
	 */
	public void mul(double other) {
		for (int index = 0; index<pos.length; index++) {
			pos[index] *= other;
		}
	}
	
	/**
	 * Negiert einen Vektor.
	 */
	public void neg() {
		mul(-1);
	}
	
	/**
	 * Macht aus diesem Vektor einen Einheitsvektor
	 */
	public void normalize(){
		double length = getLength();
		
		for (int index = 0; index<pos.length; index++) {
			pos[index] /= length;
		}
	}
	
	public boolean equals(Vector other) {
		if (other.pos.length != this.pos.length) return false;
		
		for (int index = 0; index<pos.length; index++) {
			if (pos[index] != other.pos[index]) return false;
		}
		
		return true;
	}
	
	/**
	 * Gibt eine Kopie des Vektors zurück
	 */
	public Vector clone() {
		return new Vector(pos);
	}
	
	// Getter & Setter
	/**
	 * Länge des Vektors.
	 * @return
	 */
	public double getLength() {
		double result = 0;
		
		for (int index = 0; index<pos.length; index++) {
			result += pos[index]*pos[index];
		}
		
		return Math.sqrt(result);
	}
	
	/**
	 * Setzt die Länge.
	 * @param length
	 */
	public void setLength(double length) {
		double lnow = getLength();
		for (int index = 0; index<pos.length; index++)
			pos[index]*=(length/lnow);
	}
	
	// Static stuff
	/**
	 * Erstellt einen Vektor, der von Pos1 zu Pos2 geht.
	 * @param pos1
	 * @param pos2
	 * @return
	 */
	public static Vector createVec(double[] pos1, double[] pos2) {
		Vector result = new Vector(pos1);
		result.sub(new Vector(pos2));
		
		return result;
	}
	
	/**
	 * Gibt einen NullVector mit der angegebenen anzahl an Dimensionen aus.
	 * @param dimensions
	 * @return
	 */
	public static Vector createNullVector(int dimensions) {
		double[] coords = new double[dimensions];
		
		for (int index = 0; index<dimensions; index++)
			coords[index] = 0;
		
		return new Vector(coords);
	}
}
