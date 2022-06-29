package com.example.clm.domain.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BaseResponse<T> {
	private int code;
	private T data;
	
	private BaseResponse(int code, T data) {
        this.code = code;
        this.data = data;
    }
	
	public static <T> BaseResponse<T> ofSuccess(T data) {
        return new BaseResponse<T>(200, data);
    }

}
