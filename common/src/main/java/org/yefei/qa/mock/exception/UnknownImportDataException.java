package org.yefei.qa.mock.exception;


/**
 *
 */
public class UnknownImportDataException extends ServerBaseException {

	private static final long serialVersionUID = 1L;
	private static ResponseCodeEnum responseCodeEnum = ResponseCodeEnum.UnknownImportData;

	public UnknownImportDataException() {
		super(responseCodeEnum.getCode(), responseCodeEnum.getReason());
	}

	public UnknownImportDataException(String message) {
		super(responseCodeEnum.getCode(), responseCodeEnum.getReason() + ":" + message);
	}



	public UnknownImportDataException(String message, Exception e) {
		super(responseCodeEnum.getCode(), responseCodeEnum.getReason() + ":" + message, e);
	}


	public UnknownImportDataException(Exception e) {
		super(responseCodeEnum.getCode(), responseCodeEnum.getReason(), e);
	}

}
