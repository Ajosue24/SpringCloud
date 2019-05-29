package com.vytra.services.listener;

import com.impossibl.postgres.api.jdbc.PGConnection;
import com.impossibl.postgres.api.jdbc.PGNotificationListener;
import com.impossibl.postgres.jdbc.PGDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

@Component
@Scope(BeanDefinition.SCOPE_PROTOTYPE)
public class Save extends Thread{

   private final Logger LOG = LoggerFactory.getLogger(Save.class);

   private BlockingQueue queue = new ArrayBlockingQueue(10);
   private PGConnection connection;



   public Save() {
      PGDataSource pgDataSource = new PGDataSource();

      PGNotificationListener listener = new PGNotificationListener() {
         @Override
         public void notification(int processId, String channelName, String payload) {
            queue.add("/channels/" + channelName + " " + payload);
         }
      };

      try {
         connection = (PGConnection) pgDataSource.getConnection();
         connection.addNotificationListener(listener);
         Statement statement = connection.createStatement();
         statement.execute("LISTEN test");
         statement.close();

      } catch (SQLException e) {
         LOG.error("error listen save class");
      }
   }

   public BlockingQueue getQueue() {
      return queue;
   }


   public void run() {
      Save ln = new Save();
      BlockingQueue queue = ln.getQueue();
      while (true) {
         try {
            System.out.println("while");
            String msg = (String) queue.take();
            System.out.println(msg + "");
         } catch (InterruptedException e) {
            e.printStackTrace();
         }
      }
   }
}
