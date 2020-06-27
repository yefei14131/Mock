package org.yefei.qa.mock.exception;


/**
 *
 */
public class UnknownOperatorException extends ServerBaseException {

	private static final long serialVersionUID = 1L;
	private static ResponseCodeEnum responseCodeEnum = ResponseCodeEnum.UnknownOperator;

	public UnknownOperatorException() {
		super(responseCodeEnum.getCode(), responseCodeEnum.getReason());
	}

	public UnknownOperatorException(String message) {
		super(responseCodeEnum.getCode(), responseCodeEnum.getReason() + ":" + message);
	}



	public UnknownOperatorException(String message, Exception e) {
		super(responseCodeEnum.getCode(), responseCodeEnum.getReason() + ":" + message, e);
	}


	public UnknownOperatorException(Exception e) {
		super(responseCodeEnum.getCode(), responseCodeEnum.getReason(), e);
	}

}
