package com.dsquare.view;

import org.springframework.security.crypto.password.PasswordEncoder;

import com.dsquare.model.User;
import com.dsquare.service.UserService;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;

public class SignUpView extends VerticalLayout {
	
	private TextField firstName = new TextField("First Name");
	private TextField lastName = new TextField("Last Name");
	private TextField userName = new TextField("Username");
	private TextField email = new TextField("Email");
	private PasswordField password = new PasswordField("Password");
	private PasswordField password2 = new PasswordField("Repeat Password");
	private Button submit = new Button("Sign-up");
	private UserService userService;
	private PasswordEncoder encoder;
	
	public SignUpView(UserService userService, PasswordEncoder encoder) {
		this.userService = userService;
		this.encoder = encoder;
        H2 title = new H2("Sign-up form");
        FormLayout formLayout = new FormLayout(title, firstName, lastName, email, password, password2,submit);
        formLayout.getStyle().set("position", "absolute");
        formLayout.getStyle().set("top", "40%");
        formLayout.getStyle().set("align-self", "center");
        formLayout.setWidth("500px");
        formLayout.setResponsiveSteps(new FormLayout.ResponsiveStep("0", 2, FormLayout.ResponsiveStep.LabelsPosition.TOP),
                new FormLayout.ResponsiveStep("490px", 2, FormLayout.ResponsiveStep.LabelsPosition.TOP));
        formLayout.setColspan(title, 2);
        formLayout.setColspan(email, 2);
        formLayout.setColspan(submit, 2);
        submit.addClickListener(e->signUp());
        add(formLayout);
	}

	private void signUp() {
		if(!password.getValue().equals(password2.getValue()))
			return; //TODO communicate
		this.userService.save(new User(userName.getValue(),firstName.getValue(),lastName.getValue(),email.getValue(),encoder.encode(password.getValue())));
		UI.getCurrent().navigate("login");
	}
}
