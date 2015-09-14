package com.ui;

import com.vaadin.server.VaadinRequest;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Panel;
import com.vaadin.ui.TextField;
import com.vaadin.ui.Tree;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

public class MyUI extends UI {

	@Override
	protected void init(VaadinRequest request) {
		// TODO Auto-generated method stub
		// Set the root layout for the UI
		VerticalLayout content = new VerticalLayout();
		setContent(content);


		// Add the topmost component.
		content.addComponent(new Label("The Ultimate Cat Finder"));


		// Add a horizontal layout for the bottom part.
		HorizontalLayout bottom = new HorizontalLayout();
		content.addComponent(bottom);


		bottom.addComponent(new Tree("Major Planets and Their Moons"));
		bottom.addComponent(new Panel());
		
		TextField name = new TextField("Name");
		content.addComponent(name);
		
	}

}
