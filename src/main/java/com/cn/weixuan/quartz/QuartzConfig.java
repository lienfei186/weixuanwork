package com.cn.weixuan.quartz;//package com.cn.weixuan.quartz;
//
//import org.quartz.CronScheduleBuilder;
//import org.quartz.JobBuilder;
//import org.quartz.JobDetail;
//import org.quartz.Trigger;
//import org.quartz.TriggerBuilder;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//@Configuration
//public class QuartzConfig {
//
//	/**
//	 * 指定了具体需要执行的类，只不过具体的方法就是我们需要实现的excuteInternal
//	 *
//	 * @return
//	 */
//	@Bean
//	public JobDetail uploadTaskDetail() {
//		return JobBuilder.newJob(TaskPatent.class).withIdentity("uploadTask").storeDurably().build();
//	}
//
//	/**
//	 * 指定了触发的规则
//	 *
//	 * @return
//	 */
//	@Bean
//	public Trigger uploadTaskTrigger() {
//		// 5秒一次 */5 * * * * ?
//		// 00:00一次0 0 0 */1 * ?
//		CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule("0 0 0 */1 * ?");
//				return TriggerBuilder.newTrigger().forJob(uploadTaskDetail()).withIdentity("uploadTask")
//				.withSchedule(scheduleBuilder).build();
////				.withSchedule(simpleSchedule().withIntervalInSeconds(60 * 1).repeatForever()).build();
//	}
//
//}