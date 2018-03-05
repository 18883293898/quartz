package com.hiynn.project.model.quartzJob;

import org.quartz.CronScheduleBuilder;
import org.quartz.CronTrigger;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerFactory;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.TriggerKey;
import org.quartz.impl.StdSchedulerFactory;

/**
 * 任务管理类
 * <p>Title: QuartzManager </p>
 * <p>Description: TODO </p>
 * Date: 2017年8月28日 下午9:31:54
 * @author hydata@hiynn.com
 * @version 1.0 </p> 
 * Significant Modify：
 * Date               Author           Content
 * ==========================================================
 * 2017年8月28日         hydata         创建文件,实现基本功能
 * 
 * ==========================================================
 */
public class QuartzManager {
  private static SchedulerFactory schedulerFactory = new StdSchedulerFactory();
  private static String JOB_GROUP_NAME = "EXTJWEB_JOBGROUP_NAME";
  private static String TRIGGER_GROUP_NAME = "EXTJWEB_TRIGGERGROUP_NAME";

  /**
   * 添加一个定时任务，使用默认的任务组名，触发器名，触发器组名
   * 
   * @param jobName 任务名
   * @param cls 任务
   * @param time 时间设置，参考quartz说明文档
   */
  public static void addJob(String jobName, Class cls, String time) {
    try {
    	//scheduler代表一个Quartz的独立运行容器，Trigger和JobDetail要注册到Scheduler中才会生效，也就是让调度器知道有哪些触发器和任务，才能进行按规则进行调度任务
        Scheduler scheduler = schedulerFactory.getScheduler();//创建一个触发器表
        JobDetail jobDetail = JobBuilder.newJob()//创建一个jobbuilder用来定义一个任务明细。
                .ofType(cls)//设置类，将被实例化
                // 任务名，任务组
                .withIdentity(new JobKey(jobName, JOB_GROUP_NAME))//使用给定的名称和默认组jobkey识别任务明细
                .build();//产品定义的JobDetail实例。
        
        	/*trigger描述出发Job执行的时间触发规则。有SimpleTrigger和CronTrigger两个子类代表两种方式，一种是每隔多少分钟小时执行，则用SimpleTrigger；
        	另一种是日历相关的重复时间间隔，如每天凌晨，每周星期一运行的话，通过Cron表达式便可定义出复杂的调度方案*/
        Trigger trigger = TriggerBuilder.newTrigger()
                .withSchedule(CronScheduleBuilder.cronSchedule(time))//设置schedulebuilder将用于定义触发器的表。
                .withIdentity(new TriggerKey(jobName, TRIGGER_GROUP_NAME))// 触发器名,触发器组
                .build();//创建Trigger
 
        scheduler.scheduleJob(jobDetail,trigger);//绑定
 
      // 启动
      if (!scheduler.isShutdown()) {
    	 // scheduler.start();
      }
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  /**
   * 修改一个任务的触发时间(使用默认的任务组名，触发器名，触发器组名)
   * @param jobName
   * @param time
   */
  @SuppressWarnings("unchecked")
  public static void modifyJobTime(String jobName, String time ) {
    try {
      Scheduler sched = schedulerFactory.getScheduler();
      
      
      Trigger trigger = TriggerBuilder.newTrigger()
              .withSchedule(CronScheduleBuilder.cronSchedule(time))//设置schedulebuilder将用于定义触发器的表。
              .withIdentity(new TriggerKey(jobName, TRIGGER_GROUP_NAME))
              .build();//创建Trigger
      
      if (trigger == null) {
        return;
      }
      String oldTime = ((CronTrigger) trigger).getCronExpression();
      if (!oldTime.equalsIgnoreCase(time)) {
        JobDetail jobDetail = sched.getJobDetail(new JobKey(jobName, JOB_GROUP_NAME)) ;
        Class objJobClass = jobDetail.getJobClass();
        removeJob(new TriggerKey(jobName, TRIGGER_GROUP_NAME),new JobKey(jobName, JOB_GROUP_NAME));
        addJob(jobName, objJobClass, time);
      }
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  /**
   * 移除一个任务(使用默认的任务组名，触发器名，触发器组名)
   * @param triggerKey
   * @param jobKey
   */
  public static void removeJob(TriggerKey triggerKey,JobKey jobKey) {
    try {
      Scheduler sched = schedulerFactory.getScheduler();
      sched.pauseTrigger(triggerKey);// 停止触发器
      sched.unscheduleJob(triggerKey);// 移除触发器
      sched.deleteJob(jobKey) ;
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  /**
   * 启动所有定时任务
   */
  public static void startJobs() {
    try {
      Scheduler sched = schedulerFactory.getScheduler();
      sched.start();
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  /**
   * 关闭所有定时任务
   */
  public static void shutdownJobs() {
    try {
      Scheduler sched = schedulerFactory.getScheduler();
      if (!sched.isShutdown()) {
        sched.shutdown();
      }
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }
}
