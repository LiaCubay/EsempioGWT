package com.EsempioGWT.client;

import com.EsempioGWT.client.Gui.GUI_Esempio;
import com.EsempioGWT.client.Gui.GUI_Esempio2;
import com.EsempioGWT.shared.FieldVerifier;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.event.dom.client.KeyUpHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class EsempioGWT implements EntryPoint, ClickHandler {
	/**
	 * The message displayed to the user when the server cannot be reached or
	 * returns an error.
	 */
	private Button premiButton, secondoButton;
	private DialogBox dialogBox1, dialogBox2;
	private static final String SERVER_ERROR = "An error occurred while "
			+ "attempting to contact the server. Please check your network "
			+ "connection and try again.";

	/**
	 * Create a remote service proxy to talk to the server-side Greeting service.
	 */
	private final GreetingServiceAsync greetingService = GWT
			.create(GreetingService.class);

	/**
	 * This is the entry point method.
	 */
	public void onModuleLoad() {
		final Button sendButton = new Button("Send");
		premiButton = new Button("Premimi");
		premiButton.addClickHandler(this);
		secondoButton = new Button("Secondo");
		secondoButton.addClickHandler(this);
		final TextBox nameField = new TextBox();
		nameField.setText("GWT User");
		final Label errorLabel = new Label();
		
		VerticalPanel vP = new VerticalPanel();
		HorizontalPanel hP = new HorizontalPanel();
		hP.add(nameField);
		hP.add(sendButton);
		
		vP.add(hP);
		vP.add(errorLabel);
		vP.add(premiButton);
		vP.add(secondoButton);
		

		// We can add style names to widgets
		sendButton.addStyleName("sendButton");
		premiButton.addStyleName("PremiButton");

		// Add the nameField and sendButton to the RootPanel
		// Use RootPanel.get() to get the entire body element
		RootPanel.get("container").add(vP);

		// Focus the cursor on the name field when the app loads
		nameField.setFocus(true);
		nameField.selectAll();

		// Create the popup dialog box
		final DialogBox dialogBox = new DialogBox();
		dialogBox.setText("Remote Procedure Call");
		dialogBox.setAnimationEnabled(true);
		final Button closeButton = new Button("Close");
		// We can set the id of a widget by accessing its Element
		closeButton.getElement().setId("closeButton");
		final Label textToServerLabel = new Label();
		final HTML serverResponseLabel = new HTML();
		VerticalPanel dialogVPanel = new VerticalPanel();
		dialogVPanel.addStyleName("dialogVPanel");
		dialogVPanel.add(new HTML("<b>Sending name to the server:</b>"));
		dialogVPanel.add(textToServerLabel);
		dialogVPanel.add(new HTML("<br><b>Server replies:</b>"));
		dialogVPanel.add(serverResponseLabel);
		dialogVPanel.setHorizontalAlignment(VerticalPanel.ALIGN_RIGHT);
		dialogVPanel.add(closeButton);
		dialogBox.setWidget(dialogVPanel);

		// Add a handler to close the DialogBox
		closeButton.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				dialogBox.hide();
				sendButton.setEnabled(true);
				sendButton.setFocus(true);
			}
		});

		// Create a handler for the sendButton and nameField
		class MyHandler implements ClickHandler, KeyUpHandler {
			/**
			 * Fired when the user clicks on the sendButton.
			 */
			public void onClick(ClickEvent event) {
				sendNameToServer();
				
				if(premiButton == (Button)event.getSource()){
					final DialogBox dialogBox = new DialogBox();
					final Button closeButton = new Button("Close");
					// We can set the id of a widget by accessing its Element
					closeButton.getElement().setId("closeButton");
					VerticalPanel dialogVPanel = new VerticalPanel();
					dialogVPanel.add(new GUI_Esempio());
					dialogVPanel.setHorizontalAlignment(VerticalPanel.ALIGN_RIGHT);
					dialogVPanel.add(closeButton);
					dialogBox.setWidget(dialogVPanel);

					// Add a handler to close the DialogBox
					closeButton.addClickHandler(new ClickHandler() {
						public void onClick(ClickEvent event) {
							dialogBox.hide();
						}
					});
				}
			}

			/**
			 * Fired when the user types in the nameField.
			 */
			public void onKeyUp(KeyUpEvent event) {
				if (event.getNativeKeyCode() == KeyCodes.KEY_ENTER) {
					sendNameToServer();
				}
			}

			/**
			 * Send the name from the nameField to the server and wait for a response.
			 */
			private void sendNameToServer() {
				// First, we validate the input.
				errorLabel.setText("");
				String textToServer = nameField.getText();
				if (!FieldVerifier.isValidName(textToServer)) {
					errorLabel.setText("Please enter at least four characters");
					return;
				}

				// Then, we send the input to the server.
				sendButton.setEnabled(false);
				textToServerLabel.setText(textToServer);
				serverResponseLabel.setText("");
				greetingService.greetServer(textToServer,
						new AsyncCallback<String>() {
							public void onFailure(Throwable caught) {
								// Show the RPC error message to the user
								dialogBox
										.setText("Remote Procedure Call - Failure");
								serverResponseLabel
										.addStyleName("serverResponseLabelError");
								serverResponseLabel.setHTML(SERVER_ERROR);
								dialogBox.center();
								closeButton.setFocus(true);
							}

							public void onSuccess(String result) {
								dialogBox.setText("Remote Procedure Call");
								serverResponseLabel
										.removeStyleName("serverResponseLabelError");
								serverResponseLabel.setHTML(result);
								dialogBox.center();
								closeButton.setFocus(true);
							}
						});
			}
		}

		// Add a handler to send the name to the server
		MyHandler handler = new MyHandler();
		sendButton.addClickHandler(handler);
		nameField.addKeyUpHandler(handler);
	}

	@Override
	public void onClick(ClickEvent event) {
		if(premiButton == (Button)event.getSource()){
			dialogBox1 = new DialogBox();
			dialogBox1.setText("Speechy Service Registrazione");
			dialogBox1.setAnimationEnabled(true);
			final Button close = new Button("Chiudi");
			close.setSize("70px", "25px");
			VerticalPanel dialogVPanel = new VerticalPanel();
			dialogVPanel.add(new GUI_Esempio()); // richiamo il pannello che gestisce la registrazione.
			dialogVPanel.setHorizontalAlignment(VerticalPanel.ALIGN_RIGHT);
			dialogVPanel.add(close);
			dialogBox1.setWidget(dialogVPanel);
			dialogBox1.center();
			close.addClickHandler(new ClickHandler() {
				public void onClick(ClickEvent event) {
					dialogBox1.hide();
				}
			});
		}
		
		if(secondoButton == (Button)event.getSource()){
			dialogBox2 = new DialogBox();
			dialogBox2.setText("Speechy Service Registrazione");
			dialogBox2.setAnimationEnabled(true);
			final Button close = new Button("Chiudi");
			close.setSize("70px", "25px");
			VerticalPanel dialogVPanel = new VerticalPanel();
			dialogVPanel.add(new GUI_Esempio2()); // richiamo il pannello che gestisce la registrazione.
			dialogVPanel.setHorizontalAlignment(VerticalPanel.ALIGN_RIGHT);
			dialogVPanel.add(close);
			dialogBox2.setWidget(dialogVPanel);
			dialogBox2.center();
			close.addClickHandler(new ClickHandler() {
				public void onClick(ClickEvent event) {
					dialogBox2.hide();
				}
			});
		}
	
	}
}
