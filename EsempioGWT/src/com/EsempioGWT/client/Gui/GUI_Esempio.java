package com.EsempioGWT.client.Gui;

import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.HasVerticalAlignment;
import com.google.gwt.user.client.ui.Image;

public class GUI_Esempio extends Composite {

	public GUI_Esempio(){
		VerticalPanel vP = new VerticalPanel();
		vP.setSpacing(10);
		Label lab = new Label("Esempio Pagina X");
		
		vP.add(lab);
		vP.setCellHorizontalAlignment(lab, HasHorizontalAlignment.ALIGN_CENTER);
		
		HorizontalPanel horizontalPanel = new HorizontalPanel();
		horizontalPanel.setSpacing(5);
		vP.add(horizontalPanel);
		horizontalPanel.setWidth("450px");
		Label lab1 = new Label("Hello utente");
		horizontalPanel.add(lab1);
		horizontalPanel.setCellVerticalAlignment(lab1, HasVerticalAlignment.ALIGN_MIDDLE);
		horizontalPanel.setCellHorizontalAlignment(lab1, HasHorizontalAlignment.ALIGN_CENTER);
		lab1.setHeight("28px");
		Button newB = new Button("Button");
		horizontalPanel.add(newB);
		horizontalPanel.setCellHorizontalAlignment(newB, HasHorizontalAlignment.ALIGN_CENTER);
		
		initWidget(vP);
		vP.setSize("377px", "175px");
		
		Image image = new Image("gwt/clean/images/circles.png");
		vP.add(image);
		image.setSize("85px", "47px");
		
	}
}
