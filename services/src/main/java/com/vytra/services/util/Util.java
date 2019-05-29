package com.vytra.services.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.vytra.services.entity.security.User;
import com.vytra.services.entity.services.Location;
import com.vytra.services.entity.services.ServiceRequest;
import com.vytra.services.entity.services.ServiceRequestHistory;
import com.vytra.services.request.ServiceRequestDAO;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.List;
import java.util.Optional;

public class Util {

    /**
     * Valida si usuario esta authenticado
     */
    public static User userAuth() {
        try {
            Optional<Authentication> auth = Optional.ofNullable(SecurityContextHolder.getContext().getAuthentication());
            if (auth.isPresent()) {
                return (User) auth.get().getPrincipal();
            } else {
                return null;
            }
        }catch (ClassCastException e){
            //Session perdida o Inexistente
            return null;
        }
    }

    public static ServiceRequestDAO stringToServiceRequestDao(String obj){
try{
    ObjectMapper objectMapper = new ObjectMapper();
    return objectMapper.readValue(obj.replaceAll(obj.substring(0,obj.indexOf("_")+1),""),ServiceRequestDAO.class);
}catch (Exception e){
    return null;
}
    }


    public static ServiceRequestHistory parseServiceRequestToServiceRequestHistory(ServiceRequest serviceRequest, String status, List<Location> locationList){
        ServiceRequestHistory serviceRequestHistory = new ServiceRequestHistory();
        serviceRequestHistory.setServiceId(serviceRequest.getServiceId());
        serviceRequestHistory.setUserId(serviceRequest.getUserId());
        serviceRequestHistory.setResponsibleId(serviceRequest.getResponsibleId());
        serviceRequestHistory.setDescription(serviceRequest.getDescription());
        serviceRequestHistory.setStatus(status);
        serviceRequestHistory.setCompany(serviceRequest.getCompany());
        serviceRequestHistory.getLocations().addAll(locationList);
        return serviceRequestHistory;
    }
}
