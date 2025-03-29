package com.dsquare.page;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.dsquare.service.UserService;
import com.dsquare.service.UserServiceImpl;
import com.dsquare.view.RegistrationForm;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

@Route("register")
@RestController
public class RegisterPage extends VerticalLayout {
	private static final long serialVersionUID = 220318706422293447L;

	public RegisterPage() {
		RegistrationForm registrationForm = new RegistrationForm();
		setSizeFull();
		registrationForm.getStyle().set("align-self", "center");
		setJustifyContentMode(JustifyContentMode.CENTER);
	    setDefaultHorizontalComponentAlignment(Alignment.CENTER);
	    add(registrationForm);
	}
	
	
}
