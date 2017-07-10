package com.example.flowers.application;

public class FlowersShopApplication {
	// main thread function
	public static void main(String[] args) {
		createUI();
	}

	// function to create user interface
	public static void createUI() {
		FlowersShopView view = new FlowersShopView();
		view.showWindow();
	}
}
