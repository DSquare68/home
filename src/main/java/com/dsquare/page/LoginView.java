package com.dsquare.page;

import com.vaadin.flow.component.login.LoginForm;
import com.vaadin.flow.component.login.LoginI18n;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;

public class LoginView extends VerticalLayout {
	
	public LoginView() {
		LoginI18n i18n = LoginI18n.createDefault();

		LoginI18n.Form i18nForm = i18n.getForm();
		i18nForm.setTitle("Kirjaudu sisään");
		i18nForm.setUsername("Käyttäjänimi");
		i18nForm.setPassword("Salasana");
		i18nForm.setSubmit("Kirjaudu sisään");
		i18nForm.setForgotPassword("Unohtuiko salasana?");
		i18n.setForm(i18nForm);

		LoginI18n.ErrorMessage i18nErrorMessage = i18n.getErrorMessage();
		i18nErrorMessage.setTitle("Väärä käyttäjätunnus tai salasana");
		i18nErrorMessage.setMessage(
		        "Tarkista että käyttäjätunnus ja salasana ovat oikein ja yritä uudestaan.");
		i18n.setErrorMessage(i18nErrorMessage);

		LoginForm loginForm = new LoginForm();
		loginForm.setI18n(i18n);
	}

}
