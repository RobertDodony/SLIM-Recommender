package com.szte.recommender.project.recommender;

public class IndexValuePair {
	int index;
	double value;

	public IndexValuePair(int index, double value) {
		this.index = index;
		this.value = value;
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	public double getValue() {
		return value;
	}

	public void setValue(double value) {
		this.value = value;
	}
	
}
