package org.yefei.qa.mock.exception;


/**
 *
 */
public class UnknownDataException extends ServerBaseException {

	private static final long serialVersionUID = 1L;
	private static ResponseCodeEnum responseCodeEnum = ResponseCodeEnum.DATA_NOT_IN_DB;

	public UnknownDataException() {
		super(responseCodeEnum.getCode(), responseCodeEnum.getReason());
	}

	public UnknownDataException(String message) {
		super(responseCodeEnum.getCode(), responseCodeEnum.getReason() + ":" + message);
	}



	public UnknownDataException(String message, Exception e) {
		super(responseCodeEnum.getCode(), responseCodeEnum.getReason() + ":" + message, e);
	}


	public UnknownDataException(Exception e) {
		super(responseCodeEnum.getCode(), responseCodeEnum.getReason(), e);
	}

}
