package org.yefei.qa.mock.exception;


/**
 *
 */
public class CannotMatchRequestException extends ServerBaseException {

	private static final long serialVersionUID = 1L;
	private static ResponseCodeEnum responseCodeEnum = ResponseCodeEnum.CannotMatchRequest;

	public CannotMatchRequestException() {
		super(responseCodeEnum.getCode(), responseCodeEnum.getReason());
	}

	public CannotMatchRequestException(String message) {
		super(responseCodeEnum.getCode(), responseCodeEnum.getReason() + ":" + message);
	}


	public CannotMatchRequestException(String message, Exception e) {
		super(responseCodeEnum.getCode(), responseCodeEnum.getReason() + ":" + message, e);
	}


	public CannotMatchRequestException(Exception e) {
		super(responseCodeEnum.getCode(), responseCodeEnum.getReason(), e);
	}

}
