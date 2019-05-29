package com.vytra.notification.response.notification;

import com.esotericsoftware.kryo.NotNull;
import com.vytra.notification.entity.security.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
public class MessageUsers {

    @Getter
    @Setter
    @NotNull
    private User userId;

    @Getter
    @Setter
    @NotNull
    private String message;

    @Getter
    @Setter
    private String messageType;
}
