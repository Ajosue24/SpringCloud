package com.vytra.services.services;

import com.vytra.services.entity.Company;
import com.vytra.services.entity.security.User;
import com.vytra.services.entity.services.Location;
import com.vytra.services.entity.services.ServiceRequest;
import com.vytra.services.repository.ServiceRequestRepository;
import com.vytra.services.request.ServiceRequestDAO;
import com.vytra.services.util.ServicesStatus;
import com.vytra.services.util.SpName;
import com.vytra.services.util.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.vytra.services.response.notification.MessageUsers;

import java.util.List;

@Service
@Transactional(rollbackFor = Exception.class)
public class ServiceRequestService{

    private final Logger LOG = LoggerFactory.getLogger(ServiceRequestService.class);

    @Autowired
    private ServiceRequestRepository serviceRequestRepository;

    @Autowired
    private AssingService assingService;

    @Autowired
    private ServiceRequestHistoryService serviceRequestHistoryService;

    @Autowired
    LocationService locationService;

    @Autowired
    NotificationSenderService notificationSenderService;


    public ServiceRequest save(ServiceRequest serviceRequest) throws Exception {
       return this.serviceRequestRepository.save(serviceRequest);
    }

    public List<ServiceRequest> getAllServiceRequestByUser(String filter){
        //TODO Get User by token and Company
        User u = new User(2L);
        Company c = new Company(1L);
        return this.serviceRequestRepository.findAllByUserIdAndCompany(u,c);
    }

    public void executeAction(ServiceRequestDAO serviceRequestDAO){

        switch (serviceRequestDAO.getAction()){
            /**Insert de solicitud de servicio realizado exitosamente*/
            case  SpName.SP_SAVE_SERVICE_REQUEST:
                //TODO: enviar un mensaje al usuario notificandole que ya se registro su solicitud que se busca a quien la va a atender
                notificationSenderService.send(new MessageUsers(serviceRequestDAO.getData().getUserId(),"Se registro la solicitud se esta asignando un conductor",""));
                //Metodo que asigna a un conductor
                this.assingService.assignService(serviceRequestDAO.getData());
                break;
            /**El responsable de atender la solicitud es modificado de solicitud de servicio realizado exitosamente*/
            case SpName.SP_UPDATE_RESPONSIBLE:
                if(serviceRequestDAO.getData().getResponsibleId()==null){
                    //TODO:enviar mensaje que el usuario que atenderia su solicitud tuvo un inconveniente que se reasigna el mismo...
                    notificationSenderService.send(new MessageUsers(serviceRequestDAO.getData().getUserId(),"el usuario que atenderia su solicitud tuvo un inconveniente que se reasigna el mismo...",""));
                    this.assingService.assignService(serviceRequestDAO.getData());
                }else{
                    notificationSenderService.send(new MessageUsers(serviceRequestDAO.getData().getUserId(),"La persona "+serviceRequestDAO.getData().getResponsibleId().getName()+""+ serviceRequestDAO.getData().getResponsibleId().getLastName()+" atendera su servicio",""));
                }
            break;
            /**El usuario que solicita el requerimiento lo cancela*/
            case SpName.SP_UPDATE_CANCEL:
                //TODO:enviar mensaje a quien atenderia la solicitud que la misma a sido cancelada por el usuario
                if(serviceRequestDAO.getData().getResponsibleId()!=null){
                    notificationSenderService.send(new MessageUsers(serviceRequestDAO.getData().getResponsibleId(),serviceRequestDAO.getData().getUserId().getName()+"a cancelado el servicio",""));
                }
                //enviar a historico
                try {
                    this.saveHistory(serviceRequestDAO.getData(),ServicesStatus.USER_CANCELLED);
                }catch (Exception e){
                    LOG.error("error al cancelar servicio");
                }
                //Luego de salvar update Location
                break;
            /**El usuario servicio realizado exitosamente*/
            case SpName.SP_UPDATE_COMPLETE:
                //TODO:notificar que el servicio fue ejecutado exitosamente
                notificationSenderService.send(new MessageUsers(serviceRequestDAO.getData().getResponsibleId(),"servicio ejecutado exitosamente",""));
                notificationSenderService.send(new MessageUsers(serviceRequestDAO.getData().getUserId(),"servicio ejecutado exitosamente",""));
                try {
                    this.saveHistory(serviceRequestDAO.getData(),ServicesStatus.COMPLETED);
                }catch (Exception e){
                    LOG.error("error al completar el servicio");
                }
                break;
            /**El servicio es trasladado a historioco ya sea por exitoso o cancelado*/
            case SpName.SP_DELETE_SERVICE_REQUEST:
                break;
            default:
        }
    }


    private void saveHistory(ServiceRequest serviceRequest,String status)throws Exception{
        //Filtramos locations del requestService
        List<Location> locationList = locationService.findAllByServiceRequestId(serviceRequest.getId());
        //Guardamos en la tabla historicos
            this.serviceRequestHistoryService.save(Util.parseServiceRequestToServiceRequestHistory(serviceRequest,status,locationList));
        //Remover el SR y locations
            this.deleteById(serviceRequest.getId());
            this.locationService.removeLocationList(locationList);
    }

    public void deleteById(Long id)throws Exception{
        this.serviceRequestRepository.deleteById(id);
    }

}
