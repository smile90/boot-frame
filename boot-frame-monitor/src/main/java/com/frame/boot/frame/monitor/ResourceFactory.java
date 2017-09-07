package com.frame.boot.frame.monitor;

import com.frame.common.frame.utils.EmptyUtil;
import com.frame.common.frame.utils.thread.NamedThreadFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class ResourceFactory {

	private final Logger logger = LoggerFactory.getLogger(getClass());

	/** 资源实例 */
	protected List<ResourceInterface> resourceInstances = new ArrayList<>();

	/** 是否在运行 */
	private boolean running = false;

	/** 每隔几秒扫描一次 */
	private int scanInterval = 5;

	/** 最大间隔总数（扫描数达到该数时，自动输出一次监控信息） */
	private int maxIntervalCount = 30;

	/** 监控线程 */
	private Thread monitorThread;

	/**
	 * 初始化资源接口
	 */
	public abstract void initResourceInstances();

	/**
	 * 启动
	 */
	public void startup() {
		// init
		initResourceInstances();

		// start
		if (EmptyUtil.notEmpty(resourceInstances)) {
			for (ResourceInterface resourceInstance : resourceInstances) {
				logger.info("load ResourceInterface instance:{}/{}/{}.",
						resourceInstance.getGroupName(),
						resourceInstance.getResourceName(),
						resourceInstance.getClass().getName());
			}
			// 启动监听线程
			synchronized (this) {
				if(running) return;
				running = true;
				monitorThread = new NamedThreadFactory("ResourceMonitorWorker").newThread(new Runnable() {
					@Override
					public void run() {
						printResourceInstanceInfo();
					}
				});
				monitorThread.start();
			}
		} else {
			logger.info("not found resourceInstances need monitor.");
		}
	}

	/**
	 * 终止
	 */
	public void shutdown() {
		synchronized (this) {
			if(running == false) return;
			running = false;
			if(monitorThread != null && monitorThread.isAlive()) {
				monitorThread.interrupt();
				monitorThread = null;
			}
		}
	}

	/**
	 * 输出资源实例信息
	 */
	private void printResourceInstanceInfo() {
		Map<ResourceInterface, Integer> notPrintInfoCount = new HashMap<ResourceInterface, Integer>();
		while (running) {
			try {
				Thread.sleep(scanInterval * 1000);

				for (ResourceInterface resourceInstance : resourceInstances) {
					Integer intervalNum = notPrintInfoCount.get(resourceInstance);
					if(intervalNum == null) {
						intervalNum = 1;
					}

					if (resourceInstance.isShouldPrintStatus()) {
						intervalNum = maxIntervalCount;//为了让第一次变为0时输出信息
						logger.info("[{}][{}]\t\t{}",
										resourceInstance.getGroupName(),
										resourceInstance.getResourceName(),
										resourceInstance.getDescription());
					} else if (intervalNum >= maxIntervalCount) {
						intervalNum = 1;
						logger.info("[{}][{}]\t\t{}",
								resourceInstance.getGroupName(),
								resourceInstance.getResourceName(),
								resourceInstance.getDescription());
					} else {
						intervalNum += 1;
					}
					notPrintInfoCount.put(resourceInstance, intervalNum);
				}
			} catch (InterruptedException e) {
				logger.error("the thead interrupted.", e);
			}
		}
	}

	public void setResourceInstances(List<ResourceInterface> resourceInstances) {
		this.resourceInstances = resourceInstances;
	}

	public void setScanInterval(int scanInterval) {
		this.scanInterval = scanInterval;
	}

	public void setMaxIntervalCount(int maxIntervalCount) {
		this.maxIntervalCount = maxIntervalCount;
	}

}