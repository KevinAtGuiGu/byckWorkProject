package com.kingtrustcloud.common;

/**
 * 业务异常类，用于业务出错时主动抛出异常
 */
public class BusinessException extends RuntimeException {

	/** 
	 * serialVersionUID:TODO(版本标识). 
	 * @since JDK 1.8 
	 */ 
	private static final long serialVersionUID = -4596203970039972584L;
	
	/**
	 * 错误编码
	 */
	private long errorCode;

	/**
	 * 构造一个基本异常.
	 * @param message  信息描述
	 */
	public BusinessException(String message) {
		super(message);
		this.errorCode = 400;
	}

	/**
	 * 构造一个基本异常.
	 * @param errorCode  错误编码
	 * @param message 信息描述
	 */
	public BusinessException(long errorCode, String message) {
		super(message);
		this.setErrorCode(errorCode);
	}

	/**
	 * 构造一个基本异常.
	 * @param errorCode 错误编码
	 * @param message 信息描述
	 */
	public BusinessException(long errorCode, String message, Throwable cause) {
		super(message, cause);
		this.setErrorCode(errorCode);
	}

	/**
	 * 构造一个基本异常.
	 * @param message 信息描述
	 * @param cause 根异常类（可以存入任何异常）
	 */
	public BusinessException(String message, Throwable cause) {
		super(message, cause);
	}

	public long getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(long errorCode) {
		this.errorCode = errorCode;
	}
}
