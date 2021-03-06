package com.vytra.administration.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
public class BaseError {

    @Getter @Setter
    private String field;
    @Getter @Setter
    private String message;



}
