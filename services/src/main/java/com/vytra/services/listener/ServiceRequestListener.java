package com.vytra.services.listener;

import com.impossibl.postgres.api.jdbc.PGConnection;
import com.impossibl.postgres.api.jdbc.PGNotificationListener;
import com.vytra.services.request.ServiceRequestDAO;
import com.vytra.services.services.ServiceRequestService;
import com.vytra.services.util.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

@Component
@Scope(BeanDefinition.SCOPE_PROTOTYPE)
public class ServiceRequestListener extends Thread{

    private Connection conn;
    private PGConnection pgconn;
    private BlockingQueue queue = new ArrayBlockingQueue(10);

    private ServiceRequestService serviceRequestService;

    public ServiceRequestListener(Connection conn,ServiceRequestService serviceRequestService) throws SQLException
    {
        this.serviceRequestService = serviceRequestService;

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
                ServiceRequestDAO serviceRequestDAO = Util.stringToServiceRequestDao((String) getQueue().take());
                serviceRequestService.executeAction(serviceRequestDAO);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    public void setQueue(BlockingQueue queue) {
        this.queue = queue;
    }







}
