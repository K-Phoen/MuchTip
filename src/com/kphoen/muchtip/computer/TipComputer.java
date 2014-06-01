package com.kphoen.muchtip.computer;

public class TipComputer {
	private float[] tips = {0, 0.05f, 0.08f, 0.10f, 0.15f, 0.20f};
	
	public float tipFor(float bill, int serviceRating) {
		return bill * tips[serviceRating - 1];
	}
}