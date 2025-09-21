package com.admin.servlet;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class ReconciliationScheduler implements ServletContextListener {

	private ScheduledExecutorService scheduler;
	private static final Logger logger = Logger.getLogger(ReconciliationScheduler.class.getName());

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		// TODO Auto-generated method stub
		scheduler = Executors.newSingleThreadScheduledExecutor();
		scheduler.scheduleAtFixedRate(new ReconciliationTask(), 0,1,TimeUnit.HOURS);
        logger.info("Reconciliation task started and scheduled every 1 hour.");
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
        logger.info("Shutting down Reconciliation Scheduler...");
		if (scheduler != null)
			scheduler.shutdownNow();
		logger.info("Reconciliation task ended...");

	}

}
