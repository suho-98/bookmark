package com.min.edu.exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

// 일반적으로 RestContorller / Controller는 모든 요청에 대한 오류 사항은 HTML반환한다
// HTML로 반환하면 UI/UX에서 모두 200으로 처리되기 때문에 반환되는 JSON의 값을 통해서 new Error()처리를 해줘야한다
// 따라서 @ControllerAdvice를 통해서 요청의 오류를 JSON으로 반환한다
// ※※※※※ 요청 전에 처리되는 Spring Security는 Security Filter에서 처리 해야한다
// ※※※※※ 우선순위를 갖는다 상위 메소드에 계층구조가 높은 예외는 맨 아래에 위치해서 메소드를 작성해야 한다 !!!!!!

@ControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleException(Exception e) {
		System.out.println("Exception");
        return ResponseEntity.status(500).body("Exception");
    }
	/*
	 * "errors": [
        {
            "objectName": "createBookmarkRequest",
            "field": "title",
            "rejectedValue": null,
            "codes": [
                "NotEmpty.createBookmarkRequest.title",
                "NotEmpty.title",
                "NotEmpty.java.lang.String",
                "NotEmpty"
            ],
            "arguments": [
                {
                    "arguments": null,
                    "code": "title",
                    "codes": [
                        "createBookmarkRequest.title",
                        "title"
                    ],
                    "defaultMessage": "title"
                }
            ],
            "bindingFailure": false,
            "code": "NotEmpty",
            "defaultMessage": "제목은 필수 입력값 입니다"
        }
    ],
	 */
	@ExceptionHandler(exception = MethodArgumentNotValidException.class)
	public ResponseEntity<Map<String, Object>> handleValidationException(MethodArgumentNotValidException ex){
		System.out.println("MethodArgumentNotValidException");
		Map<String, Object> errorReponse = new HashMap<String, Object>();
		
		ex.getBindingResult().getAllErrors().forEach((error) -> {
			String fieldName = ((FieldError)error).getField();
			String errorMessage = error.getDefaultMessage();
			errorReponse.put("field", fieldName);
			errorReponse.put("message", errorMessage);
		});
		
		errorReponse.put("status", HttpStatus.BAD_REQUEST.value());
		
//		return new ResponseEntity(errorReponse, HttpStatus.BAD_REQUEST);
		
		return ResponseEntity.badRequest()
		        .body(errorReponse);
	}
	
	
}
