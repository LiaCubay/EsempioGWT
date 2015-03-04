package com.EsempioGWT.client.Gui;

import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;

public class GUI_Esempio extends Composite {

	public GUI_Esempio(){
		VerticalPanel vP = new VerticalPanel();
		Button newB = new Button("Button");
		Label lab = new Label("Label1");
		Label lab1 = new Label("Label2");
		
		vP.add(lab);
		vP.add(newB);
		vP.add(lab1);
		
		initWidget(vP);
		
	}
}
