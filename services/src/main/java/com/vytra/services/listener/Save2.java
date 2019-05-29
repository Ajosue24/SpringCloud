package com.vytra.services.listener;

import com.impossibl.postgres.api.jdbc.PGConnection;
import com.impossibl.postgres.api.jdbc.PGNotificationListener;
import com.vytra.services.entity.security.User;
import com.vytra.services.entity.services.ServiceRequest;
import com.vytra.services.request.ServiceRequestDAO;
import com.vytra.services.util.SpName;
import com.vytra.services.util.Util;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;


public class Save2 extends Thread{

    private Connection conn;
    private PGConnection pgconn;
    private BlockingQueue queue = new ArrayBlockingQueue(10);



    public Save2(Connection conn) throws SQLException {
        PGNotificationListener listener = new PGNotificationListener() {
            @Override
            public void notification(int processId, String channelName, String payload) {
                queue.add("/channels/" + channelName + "_" + payload);
            }
        };
        this.conn = conn;
        this.pgconn = (PGConnection) conn;
        pgconn.addNotificationListener(listener);
        Statement stmt = conn.createStatement();
        stmt.execute("LISTEN test");
        stmt.close();
    }

    public BlockingQueue getQueue() {
        return queue;
    }

    public void run() {
        while (true) {
           try{
               System.out.println("while");
               ServiceRequestDAO serviceRequestDAO = Util.stringToServiceRequestDao((String) getQueue().take());
               serviceRequestDAO.toString();
               System.out.println("hola" +"");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    public void setQueue(BlockingQueue queue) {
        this.queue = queue;
    }

    private void executeAction(ServiceRequestDAO serviceRequestDAO){

        switch (serviceRequestDAO.getAction()){
            /**Insert de solicitud de servicio realizado exitosamente*/
            case  SpName.SP_SAVE_SERVICE_REQUEST:

                break;
            /**El responsable de atender la solicitud es modificado de solicitud de servicio realizado exitosamente*/
            case SpName.SP_UPDATE_RESPONSIBLE:

                break;
            /**El usuario que solicita el requerimiento lo cancela*/
            case SpName.SP_UPDATE_CANCEL:

                break;
            /**El usuario servicio realizado exitosamente*/
            case SpName.SP_UPDATE_COMPLETE:

                break;
            /**El servicio es trasladado a historioco ya sea por exitoso o cancelado*/
            case SpName.SP_DELETE_SERVICE_REQUEST:

                break;
                default:


        }
    }


}
