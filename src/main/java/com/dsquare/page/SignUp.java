package com.dsquare.page;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.dsquare.service.UserService;
import com.dsquare.view.SignUpView;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.orderedlayout.FlexComponent.Alignment;
import com.vaadin.flow.component.orderedlayout.FlexComponent.JustifyContentMode;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@PageTitle("Sign Up")
@Route("sign-up")
public class SignUp extends VerticalLayout {

	private SignUpView view;
	
	public SignUp(@Autowired UserService userService, @Autowired PasswordEncoder encoder) {
		view = new SignUpView(userService,encoder);
		setJustifyContentMode(JustifyContentMode.CENTER);
        setAlignItems(Alignment.CENTER);
        getStyle().set("display", "flex");
		add(view);
	}
}
