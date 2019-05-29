package com.vytra.notification.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@NoArgsConstructor
public class JsendResponse<T> {

    @Getter
    @Setter
    private String status;
    @Getter
    @Setter
    private T data;
    @Getter
    @Setter
    private String message;

    public JsendResponse<T> sendSuccess(T data) {
        return new JsendResponse<>("success",data,null);
    }

    public JsendResponse sendFailureBySystem(){
        return new JsendResponse<>("error",data, HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase());
    }

    public JsendResponse jsendBadRequest(String message){
        return new JsendResponse("fail", HttpStatus.BAD_REQUEST.getReasonPhrase()+" "+message);
    }
    public JsendResponse jsendNotFount(){
        return new JsendResponse("fail", HttpStatus.NOT_FOUND.getReasonPhrase());
    }


    public JsendResponse(String status, String message) {
        this.status = status;
        this.message = message;
    }

    public JsendResponse<T> sendError(T data) {
        return new JsendResponse<>("fail",data,"check data");
    }

    public JsendResponse<T> sendSuccessOnly() {
        return new JsendResponse<>("sussess",null);
    }
}
