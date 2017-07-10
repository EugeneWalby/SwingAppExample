package com.example.flowers.application;

import java.awt.Choice;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import com.example.flowers.entities.Flower;
import com.example.flowers.handlers.DatabaseHandler;

public class FlowersShopView implements ActionListener {
	private DatabaseHandler db;
	private List<Flower> flowers;
	
	// main frame 
	JFrame frame;
	// elements panel 
	JPanel fieldsPanel, buttonsPanel;
	// labels
	JLabel idLabel, titleLabel, countLabel, costLabel, typeLabel;
	// text fields
	JTextField idText, titleText, countText, costText;
	// buttons
	JButton insertButton, updateButton, deleteButton, clearButton, 
						searchButton, sortButton, showButton, toFileButton;
	// choice
	Choice typeChoice;

	public FlowersShopView() {
		createWindow();
	}

	private void createWindow() {
		db = new DatabaseHandler();
		createMainView();
		createLabels();
		createTextFields();
		createChoice();
		addElements();
		createButtons();
		addActionsForButtons();
		addButtons();
		addPanelsToMainView();
	}

	private void createMainView() {
		frame = new JFrame("Flower shop application");
		frame.setDefaultCloseOperation(3);

		// Layout of Main Window
		Container c = frame.getContentPane();
		c.setLayout(new BoxLayout(c, BoxLayout.Y_AXIS));
	}
	
	private void createLabels() {
		// create labels
		idLabel = new JLabel("ID:");
		titleLabel = new JLabel("Flower title:");
		countLabel = new JLabel("Flowers count:");
		costLabel = new JLabel("Cost for flower:");
		typeLabel = new JLabel("Flower type:");
	}
	
	private void createTextFields() {
		// create text fields
		idText = new JTextField("", 15); // To adjust width
		titleText = new JTextField();
		countText = new JTextField();
		costText = new JTextField();
	}
	
	private void createChoice() {
		// crete choice
		typeChoice = new Choice();
		typeChoice.add("flower");
		typeChoice.add("bouquet");
		typeChoice.add("gift");
	}
	
	private void addElements() {
		// create panel for fields
		fieldsPanel = new JPanel(new GridLayout(6, 2));
		// add elements
		fieldsPanel.add(idLabel);
		fieldsPanel.add(idText);
		fieldsPanel.add(titleLabel);
		fieldsPanel.add(titleText);
		fieldsPanel.add(countLabel);
		fieldsPanel.add(countText);
		fieldsPanel.add(costLabel);
		fieldsPanel.add(costText);
		fieldsPanel.add(typeLabel);
		fieldsPanel.add(typeChoice);
	}
	
	private void createButtons() {
		// create buttons
		insertButton = new JButton("Insert");
		updateButton = new JButton("Update");
		deleteButton = new JButton("Delete");
		clearButton = new JButton("Clear");
		searchButton = new JButton("Search");
		sortButton = new JButton("Sort");
		showButton = new JButton("Show list");
		toFileButton = new JButton("To file");
	}

	private void addActionsForButtons() {
		insertButton.addActionListener(this);
		updateButton.addActionListener(this);
		deleteButton.addActionListener(this);
		clearButton.addActionListener(this);
		searchButton.addActionListener(this);
		sortButton.addActionListener(this);
		showButton.addActionListener(this);
		toFileButton.addActionListener(this);
	}
	
	private void addButtons() {
		// create panel for buttons
		buttonsPanel = new JPanel(new GridLayout(2, 4));
		// add buttons to panel
		buttonsPanel.add(insertButton);
		buttonsPanel.add(updateButton);
		buttonsPanel.add(deleteButton);
		buttonsPanel.add(clearButton);
		buttonsPanel.add(searchButton);
		buttonsPanel.add(sortButton);
		buttonsPanel.add(showButton);
		buttonsPanel.add(toFileButton);
	}

	private void addPanelsToMainView() {
		// add panels to main frame
		frame.add(fieldsPanel);
		frame.add(buttonsPanel);
		// compact objects
		frame.pack();
	}

	public void showWindow() {
		// and show main window
		frame.setVisible(true);
	}
	
	@Override
	public void actionPerformed(ActionEvent evt) {
		String command = evt.getActionCommand();

		if (command.equals("Insert")) {
			insertData();
		} else if (command.equals("Update")) {
			createDialogBox();
		} else if (command.equals("Delete")) {
			deleteData();
		} else if (command.equals("Clear")) {
			clearFields();
		} else if (command.equals("Search")) {
			searchData();
		} else if (command.equals("Sort")) {
			sortData();
		} else if (command.equals("Show list")) {
			showData();
		} else if (command.equals("To file")) {
			writeToFile();
		}
	}
	
	private void insertData() {
		try {
			String title = titleText.getText();
			int count = Integer.parseInt(countText.getText()); 
			double cost = Double.parseDouble(costText.getText()); 
			String type = typeChoice.getItem(typeChoice.getSelectedIndex());
			Flower flower = new Flower(title, count, cost, type);
			db.insertData(flower);
			createMessageBox("Inserted Successfully!");
			clearFields();
		} catch (NumberFormatException e) {
			createMessageBox("Input error! Check fields!");
		} catch (SQLException e) {
			createMessageBox("Error while inserting!");
		}
	}

	private void deleteData() {
		int id = Integer.parseInt(idText.getText());
		try {
			db.deleteData(id);
			createMessageBox("Deleted Successfully!");
			clearFields();
		} catch (SQLException e) {
			createMessageBox("Error while deleting!");
		}
	}

	private void searchData() {
		try {
			flowers = db.searchData(titleText.getText());
			createResultsBox();
			clearFields();
		} catch (SQLException e) {
			createMessageBox("Error while searching!");
		}
	}

	private void sortData() {
		try {
			flowers = db.sortData();
			createResultsBox();
			clearFields();
		} catch (SQLException e) {
			createMessageBox("Error while sorting!");
		}
	}

	private void showData() {
		try {
			flowers = db.showData();
			createResultsBox();
			clearFields();
		} catch (SQLException e) {
			createMessageBox("Error while showing list!");
		}
	}

	private void writeToFile() {
		try {
			db.writeToFile();
			createMessageBox("Data writen to file ");
			clearFields();
		} catch (SQLException | IOException e) {
			createMessageBox("Error while writing to file!");
		}
	}

	// dialog box
	private void createDialogBox() {
		// create main containers
		JFrame frame = new JFrame("Are you sure?");
		frame.setDefaultCloseOperation(2);
		// Layout of window
		Container c = frame.getContentPane();
		c.setLayout(new BoxLayout(c, BoxLayout.Y_AXIS));

		// panels
		JPanel labelPanel = new JPanel(new FlowLayout());
		JPanel buttonsPanel = new JPanel(new GridLayout(1, 2));

		// create objects
		JLabel label = new JLabel("Are you want to update record?");
		JButton ok = new JButton("OK");
		JButton cancel = new JButton("Cancel");

		// add label
		labelPanel.add(label);

		// add buttons
		buttonsPanel.add(ok);
		buttonsPanel.add(cancel);

		ok.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// updateData();
				frame.setVisible(false);
			}
		});

		cancel.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				frame.setVisible(false);
			}
		});

		// add panels
		frame.add(labelPanel);
		frame.add(buttonsPanel);

		// compact objects
		frame.pack();
		// show window
		frame.setVisible(true);
	}

	// search results
	private void createResultsBox() {
		// create main containers
		JFrame frame = new JFrame("Search results");
		frame.setDefaultCloseOperation(2);
		// Layout of window
		Container c = frame.getContentPane();
		c.setLayout(new BoxLayout(c, BoxLayout.Y_AXIS));

		JPanel titlePanel = new JPanel(new GridLayout(1, 4));
		JLabel idString = new JLabel("ID ");
		JLabel titleString = new JLabel(String.valueOf("Title "));
		JLabel countString = new JLabel(String.valueOf("Count "));
		JLabel costString = new JLabel(String.valueOf("Cost "));
		JLabel typeString = new JLabel(String.valueOf("Type "));

		titlePanel.add(idString);
		titlePanel.add(titleString);
		titlePanel.add(countString);
		titlePanel.add(costString);
		titlePanel.add(typeString);

		JPanel labelPanel = new JPanel(new GridLayout(flowers.size(), 4));
		for (Flower flower : flowers) {
			JLabel id = new JLabel(String.valueOf(flower.getId() + " "));
			JLabel title = new JLabel(flower.getTitle() + " ");
			JLabel count = new JLabel(String.valueOf(flower.getCount() + " "));
			JLabel cost = new JLabel(String.valueOf(flower.getCost()) + " ");
			JLabel type = new JLabel(flower.getType());

			labelPanel.add(id);
			labelPanel.add(title);
			labelPanel.add(count);
			labelPanel.add(cost);
			labelPanel.add(type);
		}

		// add panels
		frame.add(titlePanel);
		frame.add(labelPanel);

		// compact objects
		frame.pack();
		// show window
		frame.setVisible(true);
	}

	// message box
	private void createMessageBox(String msg) {
		// create frame
		JFrame frame = new JFrame("Message");
		frame.setDefaultCloseOperation(2);
		// create label
		JLabel label = new JLabel(msg);

		// add label
		frame.add(label);
		// set window size
		frame.setSize(200, 100);
		// show window
		frame.setVisible(true);
	}

	// clear fields
	private void clearFields() {
		String empty = "";

		idText.setText(empty);
		titleText.setText(empty);
		countText.setText(empty);
		costText.setText(empty);
	}
}
