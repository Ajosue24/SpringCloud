package com.vytra.services.bootstrap;

import com.vytra.services.config.PgEnvironment;
import com.vytra.services.listener.ServiceRequestListener;
import com.vytra.services.services.ServiceRequestService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;

@Service
public class Bootstrap implements InitializingBean {
    private final Logger LOG = LoggerFactory.getLogger(Bootstrap.class);

    @Autowired
    DataSource dataSource;
    @Autowired
    PgEnvironment pgEnvironment;

    @Autowired
    ServiceRequestService serviceRequestService;

    @Override
    public void afterPropertiesSet() throws Exception {
        LOG.info("ini");
        //Save.startSave(pgEnvironment.getPgDataSource());
        try {
            ServiceRequestListener ln = new ServiceRequestListener(pgEnvironment.getPgDataSource().getConnection(),serviceRequestService);
            ln.setQueue(ln.getQueue());
            ln.start();
        }catch (Exception e){
            e.printStackTrace();
        }

        LOG.info("fin ini");
    }
}
