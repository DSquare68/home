package com.dsquare.security;

import java.util.function.Supplier;

import org.springframework.security.web.access.expression.DefaultHttpSecurityExpressionHandler;
import org.springframework.expression.EvaluationContext;
import org.springframework.expression.Expression;
import org.springframework.security.access.expression.ExpressionUtils;
import org.springframework.security.access.expression.SecurityExpressionHandler;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authorization.AuthorizationDecision;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.access.intercept.RequestAuthorizationContext;
import org.springframework.util.Assert;


public class PolicyUserAuthorizationManager implements AuthenticationManager {

	private SecurityExpressionHandler<RequestAuthorizationContext> expressionHandler = new DefaultHttpSecurityExpressionHandler();
	private Expression expression;
	
	
	public PolicyUserAuthorizationManager(String expresionString) {
	}
	
	public AuthorizationDecision check(Supplier<Authentication> authentication, PolicyUserAuthorizationManager context) {
		EvaluationContext ctx = this.expressionHandler.createEvaluationContext(authentication.get(),null);
		boolean granted = ExpressionUtils.evaluateAsBoolean(this.expression, ctx);
		return new AuthorizationDecision(granted);
    }

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		// TODO Auto-generated method stub
		return null;
	}
}
