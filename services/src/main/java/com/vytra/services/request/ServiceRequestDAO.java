package com.vytra.services.request;

import com.vytra.services.entity.services.ServiceRequest;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ServiceRequestDAO {
    @Getter
    @Setter
    private String action;
    @Getter
    @Setter
    private ServiceRequest data;
}

