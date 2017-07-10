package com.example.flowers.entities;

public class Flower {
	
	private int id;
	private String title;
	private int count;
	private double cost;
	private String type;
	
	public Flower(int id, String title, int count, double cost, String type) {
		super();
		this.id = id;
		this.title = title;
		this.count = count;
		this.cost = cost;
		this.type = type;
	}
	
	public Flower(String title, int count, double cost, String type) {
		super();
		this.id = 1;
		this.title = title;
		this.count = count;
		this.cost = cost;
		this.type = type;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public double getCost() {
		return cost;
	}
	public void setCost(double cost) {
		this.cost = cost;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
}
