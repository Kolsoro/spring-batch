//package com.infybuzz.app.controller;
//
//import java.util.List;
//
//import org.springframework.batch.core.launch.JobExecutionNotRunningException;
//import org.springframework.batch.core.launch.JobOperator;
//import org.springframework.batch.core.launch.NoSuchJobExecutionException;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import com.infybuzz.app.request.JobParamsRequest;
//import com.infybuzz.app.service.JobService;
//
//@RestController
//@RequestMapping("/api/job")
//public class JobController {
//
//	@Autowired
//	private JobService jobService;
//	
//	private JobOperator jobOperator;
//
//	@GetMapping("/start/{jobName}")
//	public String startJob(@PathVariable String jobName, @RequestBody List<JobParamsRequest> jobParamsRequestList)
//			throws Exception {
//		System.out.println(jobParamsRequestList);
//		jobService.startJob(jobName, jobParamsRequestList);
//
//		return "job Started...";
//
//	}
//
//	@GetMapping("/stop/{jobExecutionId}")
//	public String stopJob(@PathVariable long jobExecutionId) {
//
//		try {
//			jobOperator.stop(jobExecutionId);
//		} catch (Exception e) {
// 			e.printStackTrace();
//		} 
//		return "job stopped";
//	}
//
//}
