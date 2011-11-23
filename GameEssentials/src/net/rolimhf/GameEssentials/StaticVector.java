package net.rolimhf.GameEssentials;

import java.util.ArrayList;

public class StaticVector {
	public final double[] coords;
	private int dimensions;
	
	public final double length;
	
	public StaticVector(double ...d) {
		this.coords = d;
		this.dimensions = d.length;
		
		this.length = this.getLength();
	}
	
	// PLUS
	public StaticVector add(StaticVector other) {
		assert other.dimensions == this.dimensions;
		
		ArrayList<Double> resarray = new ArrayList<Double>();
		for (int index = 0; index<this.dimensions; index++) {
			double a = this.coords[index];
			double b = other.coords[index];
			
			resarray.add(index, a+b);
		}
		return new StaticVector(StaticVector.converttodouble(resarray));
	}
	
	// MINUS
	public StaticVector sub(StaticVector other) {
		return add(other.neg());
	}
	
	// MAL
	/**
	 * Produkt mit Skalar
	 * @return Produkt als Vektor
	 */
	public StaticVector mul(double skalar){
		ArrayList<Double> resarray = new ArrayList<Double>();
		
		for (int index = 0; index<this.dimensions; index++) {
			resarray.add(index, this.coords[index]*skalar);
		}
		
		return new StaticVector(StaticVector.converttodouble(resarray));
	}
	
	public StaticVector neg() {
		return mul(-1);
	}
	
	public double getAngle(StaticVector other) {
		assert this.dimensions == other.dimensions;
		
		double length = this.length * other.length;
		int ints = 0;
		
		for (int index = 0; index<this.dimensions; index++) {
			ints += this.coords[index] * other.coords[index];
		}
		
		return Math.acos(ints/length);
	}
	
	// static
	public static StaticVector createvec(double[] coords1, double[] coords2) {
		StaticVector a = new StaticVector(coords1);
		StaticVector b = new StaticVector(coords2);
		return a.sub(b);
	}
	
	public static StaticVector createvec(StaticVector v1, StaticVector v2) {
		return v1.sub(v2);
	}
	
	public static double get_distance(StaticVector pos1, StaticVector pos2) {
		return StaticVector.createvec(pos1, pos2).length;
	}
	
	public StaticVector setLength(double i) {
		ArrayList<Double> resArray = new ArrayList<Double>();
		for (int index = 0; index<coords.length; index++) {
			resArray.add(index, coords[index]*(i/length));
		}
		return new StaticVector(StaticVector.converttodouble(resArray));
	}

	// Internal
	private static double[] converttodouble(ArrayList<Double> ints){
		double[] result = new double[ints.toArray().length];
		
		for (int index = 0; index<ints.toArray().length; index++) {
			result[index] = ints.get(index).doubleValue();
		}
		
		return result;
	}
	
	private double getLength(){
		double result = 0.0d;
		for (int index = 0; index<coords.length; index++) {
			result += Math.pow(coords[index], 2);
		}
		return Math.sqrt(result);
	}

	public static double get_distance(double[] coords1, double[] coords2) {
		// TODO Auto-generated method stub
		return get_distance(new StaticVector(coords1), new StaticVector(coords2));
	}
}
