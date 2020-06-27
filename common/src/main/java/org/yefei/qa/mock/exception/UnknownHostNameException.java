package org.yefei.qa.mock.exception;


/**
 *
 */
public class UnknownHostNameException extends ServerBaseException {

	private static final long serialVersionUID = 1L;
	private static ResponseCodeEnum responseCodeEnum = ResponseCodeEnum.UnknownHostName;

	public UnknownHostNameException() {
		super(responseCodeEnum.getCode(), responseCodeEnum.getReason());
	}

	public UnknownHostNameException(String message) {
		super(responseCodeEnum.getCode(), responseCodeEnum.getReason() + ":" + message);
	}



	public UnknownHostNameException(String message, Exception e) {
		super(responseCodeEnum.getCode(), responseCodeEnum.getReason() + ":" + message, e);
	}


	public UnknownHostNameException(Exception e) {
		super(responseCodeEnum.getCode(), responseCodeEnum.getReason(), e);
	}

}
