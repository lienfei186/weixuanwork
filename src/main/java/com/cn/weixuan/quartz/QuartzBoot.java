package com.cn.weixuan.quartz;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.quartz.SimpleScheduleBuilder.simpleSchedule;

public class QuartzBoot {
	private static Logger logger = LoggerFactory.getLogger(QuartzBoot.class);

	public static void main(String[] args) {
		try {
			// 获取调度器
			Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
			// 开启调度器
			scheduler.start();
			// 注册一个示例任务和触发器
			registerJobAndTrigger(scheduler);
			// scheduler.shutdown();
		} catch (SchedulerException se) {
			logger.error("调度器初始化异常", se);
		}
	}

	/**
	 * 注册一个任务和触发器
	 */
	public static void registerJobAndTrigger(Scheduler scheduler) {
		JobDetail job = JobBuilder.newJob(MySimpleJob.class).withIdentity("mySimpleJob", "simpleGroup").build();
		Trigger trigger = TriggerBuilder.newTrigger().withIdentity("simpleTrigger", "simpleGroup").startNow()
				.withSchedule(simpleSchedule().withIntervalInSeconds(10).repeatForever()).build();
		try {
			scheduler.scheduleJob(job, trigger);
		} catch (SchedulerException e) {
			logger.error("注册任务和触发器失败", e);
		}
	}

	/**
	 * 简单的任务
	 */
	public static class MySimpleJob implements Job {
		@Override
		public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
			logger.info("哇真的执行了");
		}
	}

}