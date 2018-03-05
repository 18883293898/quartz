package com.hiynn.project.model.quartzJob;

/**
 * 测试类
 * <p>Title: QuartzTest </p>
 * <p>Description: TODO </p>
 * Date: 2017年8月28日 下午9:30:31
 * @author hydata@hiynn.com
 * @version 1.0 </p> 
 * Significant Modify：
 * Date               Author           Content
 * ==========================================================
 * 2017年8月28日         hydata         创建文件,实现基本功能
 * 
 * ==========================================================
 */
public class QuartzTest {
  public static void main(String[] args) {
    try {
      String job_name = "动态任务调度";
      System.out.println("【系统启动】开始(每1秒输出一次)...");  
      QuartzManager.addJob(job_name, QuartzJob.class, "0/1 * * * * ?");  
//      
      Thread.sleep(5000);  
      System.out.println("【修改时间】开始(每2秒输出一次)...");  
      QuartzManager.modifyJobTime(job_name, "10/3 * * * * ?");  
//      Thread.sleep(6000);  
      System.out.println("【移除定时】开始...");  
//      QuartzManager.removeJob(new TriggerKey(jobName, TRIGGER_GROUP_NAME),new JobKey(jobName, JOB_GROUP_NAME));  
//      System.out.println("【移除定时】成功");  
      QuartzManager.startJobs();
      Thread.sleep(6000);  
      QuartzManager.shutdownJobs();
//      System.out.println("【移除定时】成功");
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
