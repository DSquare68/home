package com.dsquare.view;

import com.dsquare.service.UserServiceImpl;
import com.vaadin.flow.component.HasValueAndElement;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.checkbox.Checkbox;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.orderedlayout.FlexComponent.Alignment;
import com.vaadin.flow.component.orderedlayout.FlexComponent.JustifyContentMode;
import com.vaadin.flow.component.textfield.EmailField;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;
import java.util.stream.Stream;

public class RegistrationForm extends FormLayout {
	private static final long serialVersionUID = -6824963919491202898L;

	private H3 title;

	private TextField username;
   private TextField firstName;
   private TextField lastName;

   private EmailField email;

   private PasswordField password;
   private PasswordField passwordConfirm;

   private Button submitButton;


   public RegistrationForm() {
	   UserServiceImpl userServiceImpl = new UserServiceImpl();
       title = new H3("Sign-up");
       firstName = new TextField("First name");
       username = new TextField("Username");
       lastName = new TextField("Last name");
       email = new EmailField("Email");

       password = new PasswordField("Password");
       passwordConfirm = new PasswordField("Confirm password");

       setRequiredIndicatorVisible(username,firstName, lastName, email, password,
               passwordConfirm);

       submitButton = new Button("Register");
       submitButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY);

       add(title,username, firstName, lastName,  password,
               passwordConfirm,email, submitButton);

       // Max width of the Form
       setMaxWidth("500px");
       // Allow the form layout to be responsive.
       // On device widths 0-490px we have one column.
       // Otherwise, we have two columns.
       setResponsiveSteps(
               new ResponsiveStep("0", 1, ResponsiveStep.LabelsPosition.TOP),
               new ResponsiveStep("490px", 2, ResponsiveStep.LabelsPosition.TOP));

       // These components always take full width
       setColspan(title, 2);
       setColspan(email, 2);
       setColspan(submitButton, 2);
       
       submitButton.addClickListener(e->submit());
   }

   private void submit() {
	   
   }

public PasswordField getPasswordField() { return password; }

   public PasswordField getPasswordConfirmField() { return passwordConfirm; }

   public Button getSubmitButton() { return submitButton; }

   private void setRequiredIndicatorVisible(HasValueAndElement<?, ?>... components) {
       Stream.of(components).forEach(comp -> comp.setRequiredIndicatorVisible(true));
   }

}
