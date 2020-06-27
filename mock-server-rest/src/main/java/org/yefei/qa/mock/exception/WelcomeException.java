package org.yefei.qa.mock.exception;


/**
 *
 */
public class WelcomeException extends ServerBaseException {

	private static final long serialVersionUID = 1L;


	public WelcomeException() {
		super(ResponseCodeEnum.WELCOME.getCode(), ResponseCodeEnum.WELCOME.getReason());
	}

	public WelcomeException(String message) {
		super(ResponseCodeEnum.WELCOME.getCode(), message);
	}



	public WelcomeException(String message, Exception e) {
		super(ResponseCodeEnum.WELCOME.getCode(), message, e);
	}

}
