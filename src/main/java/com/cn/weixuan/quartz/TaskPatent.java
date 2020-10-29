package com.cn.weixuan.quartz;//package com.cn.weixuan.quartz;
//
//import org.quartz.DisallowConcurrentExecution;
//import org.quartz.JobExecutionContext;
//import org.quartz.JobExecutionException;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.scheduling.quartz.QuartzJobBean;
//
//import com.tongyu.workstation.service.DoctorService;
//import com.tongyu.workstation.service.PatientService;
//import com.tongyu.workstation.service.ReportService;
//import plantform.tools.ToolDateTime;
//import java.util.Date;
//import static com.tongyu.workstation.controller.CallNumberController.BRONCHUS;
//import static com.tongyu.workstation.controller.CallNumberController.INTESTINES;
//import static com.tongyu.workstation.controller.CallNumberController.STOMACH;
//import static com.tongyu.workstation.controller.CallNumberController.CALLNUMBER;
//import static com.tongyu.workstation.controller.CallNumberController.INTESTINES_ID;
//import static com.tongyu.workstation.controller.CallNumberController.STOMACH_ID;
//import static com.tongyu.workstation.controller.CallNumberController.BRONCHUS_ID;
//import static com.tongyu.workstation.controller.CallNumberController.CALLNUMBER_ID;
//
////不允许并发执行任务,当前任务未执行完毕，即使定时时间到了，也要等到当前任务执行完毕才能继续
//@DisallowConcurrentExecution
//public class TaskPatent extends QuartzJobBean {
//	@Autowired
//	private PatientService patientService;
//	@Autowired
//	private DoctorService doctorService;
//	@Autowired
//	private ReportService reportService;
//
//	@Override
//	protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException {
//		log.info(ToolDateTime.getYYYYMMDDHHMMSS(new Date()) + "  执行任务");
//	 	textHttpClientUtil();
//	}
//
//	public void textHttpClientUtil() {
//		INTESTINES.clear();//肠镜
//		STOMACH.clear();//胃镜
//		BRONCHUS.clear();//支气管镜
//		CALLNUMBER.clear();//叫号
//		INTESTINES_ID = 1;//肠镜序号
//		STOMACH_ID = 1;//胃镜序号
//		BRONCHUS_ID = 1;//支气管镜序号
//		CALLNUMBER_ID = 1;//叫号序号
//	}
//}