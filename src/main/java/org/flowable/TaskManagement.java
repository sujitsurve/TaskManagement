package org.flowable;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import org.flowable.engine.ManagementService;
import org.flowable.engine.ProcessEngine;
import org.flowable.engine.ProcessEngineConfiguration;
import org.flowable.engine.RepositoryService;
import org.flowable.engine.RuntimeService;
import org.flowable.engine.TaskService;
import org.flowable.engine.impl.cfg.StandaloneProcessEngineConfiguration;
import org.flowable.engine.repository.Deployment;
import org.flowable.engine.repository.ProcessDefinition;
import org.flowable.engine.runtime.ProcessInstance;
import org.flowable.job.api.Job;
import org.flowable.task.api.Task;
import org.flowable.task.api.TaskQuery;

public class TaskManagement {
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		TaskManagement taskManageObj = new TaskManagement();
		//ProcessEngine processEngine = null;
		boolean done=true;
		
		try{
		ProcessEngineConfiguration cfg = new StandaloneProcessEngineConfiguration()
	      .setJdbcUrl("jdbc:db2://localhost:50000/taskflow")
	      .setJdbcUsername("admin")
	      .setJdbcPassword("admin")
	      .setJdbcDriver("com.ibm.db2.jcc.DB2Driver")
	      .setDatabaseSchemaUpdate(ProcessEngineConfiguration.DB_SCHEMA_UPDATE_TRUE);

	    ProcessEngine processEngine = cfg.buildProcessEngine();
		
	    RepositoryService repositoryService = processEngine.getRepositoryService();
	    Deployment deployment = repositoryService.createDeployment()
	      .addClasspathResource("flowableWorkflow.bpmn20.xml")
	      .deploy();  
	    
	   
	    ProcessDefinition processDefinition = repositoryService.createProcessDefinitionQuery()
	      .deploymentId(deployment.getId())
	      .singleResult();
	    System.out.println("Found process definition : " + processDefinition.getName()+" Process defination id : " + processDefinition.getId());
		
	    Scanner scanner= new Scanner(System.in);
	   
	    System.out.println("Please Enter Document Name :");
	    String docname = scanner.nextLine();
	    
	    RuntimeService runtimeService = processEngine.getRuntimeService();
	    
	    Map<String, Object> variables = new HashMap<String, Object>();
	    variables.put("DocName",docname);
	   
	    ProcessInstance processInstance =
	      runtimeService.startProcessInstanceByKey("myProcess", variables);
	    
	    TaskService taskService = processEngine.getTaskService();
//	    processEngine.getDynamicBpmnService().
//	    ManagementService managementServie = processEngine.getManagementService();
//	    List<Job> jobs = managementServie.createJobQuery().list();
	      
	    //Task tasks = taskService.createTaskQuery().singleResult();
	    List<Task> tasks  =taskService.createTaskQuery().list();
	    //System.out.println("You have " + tasks.getName()+ " task:");
	            System.out.println("Task List: ");
	            for(int i=0;i<tasks.size();i++)
	            {
	            	System.out.println(tasks.get(i).getName());
	            }
	            List<Task> tasks5 = taskService.createTaskQuery().processInstanceId(processInstance.getId()).list();
	            for (Task task : tasks) {
	              System.out.println("Your Current task : " + task.getName());
	            }

	            
//	    while(done)
//	    {
	            
	    	
	    
	  //  System.out.println("task would you like to complete?");
	//    int taskIndex = Integer.valueOf(scanner.nextLine());
	//    Task task = tasks.get(taskIndex - 1);
	    /*Map<String, Object> processVariables = taskService.getVariables(tasks.getId());
	    System.out.println(processVariables.get("DocName") + " wants " +
	        processVariables.get("nrOfHolidays") + "Do you approve this?");*/
	            
	            //To Display name of Document
//	            	for (Map.Entry<String,Object> entry : variables.entrySet()) 
//		            System.out.println("Document name is "+ entry.getValue());
	           
	         
	            //Starting 
	           taskManageObj.openTask(tasks, scanner, taskService);
	   // System.out.println(taskService.getClass());
	    
	  //Inprogress code  
//	    tasks  =taskService.createTaskQuery().list();
//	    if (tasks.get( tasks.size()-1).getName().equals("Done")){
//	    	boolean approve = taskManageObj.DoneOPtion(tasks, scanner, taskService);
//			    if(approve == true)
//			    {	System.out.println("Your task is Ended now ");
//			    		done = false;
//			    } else {
//			    	
//			    }
//	    } else{
//		    System.out.println("Your task id is " + tasks.get(tasks.size()-1).getId() + "and name is " + tasks.get( tasks.size()-1).getName() + "- which task you want to go,Please enter number ? 1.Approve 2.Done 3.Open");
//		   int input = Integer.parseInt(scanner.nextLine());
//		   Map<String, Object> inputVariables = new HashMap<String, Object>();
//		   inputVariables.put("input", input);
//		   List<Task> tasks1  =taskService.createTaskQuery().list();
//		   taskService.complete( tasks1.get(tasks1.size()-1).getId(), inputVariables);
//		    System.out.println(tasks1.get(tasks1.size()-1).getName()+" task get completed ");
//	    }
	   
	   
	    
	    
//	    tasks  =taskService.createTaskQuery().list();
//	    if (tasks.get( tasks.size()-1).getName().equals("Done")){
//	    
//	    } else {
//	    
//	    }
		    
		    
		    //Review code
//		    System.out.println("Your task id is " + tasks.get(tasks.size()-1).getId() + "and name is " + tasks.get( tasks.size()-1).getName() + "- which task you want to go,Please enter number ? 1.Done 2.Approve");
//			   boolean approvedtwo = scanner.nextLine().toLowerCase().equals("1");
//			   Map<String, Object> approvedOneVariables = new HashMap<String, Object>();
//			   approvedOneVariables.put("approvedTwo",approvedtwo);
//			   
//			   
//			   List<Task> tasks3  =taskService.createTaskQuery().list();
//			   taskService.complete( tasks3.get(tasks3.size()-1).getId(), approvedOneVariables);
//			    System.out.println(tasks3.get(tasks3.size()-1).getName()+" task get completed ");
//			    
			    
			    //Done code
			    
//	    }
	           
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	           
	}

	void DoneOPtion(List<Task> tasks, Scanner scanner, TaskService taskService){
		System.out.println("Your task id is " + tasks.get(tasks.size()-1).getId() + " and Task name is " + tasks.get( tasks.size()-1).getName() + "- which task you want to go,Please enter number ? 1.End Task 2.Open");
		   boolean approve = scanner.nextLine().toLowerCase().equals("1");
		   Map<String, Object> approvedDoneVariables = new HashMap<String, Object>();
		   approvedDoneVariables.put("approve",approve);
		  // System.out.println("Approve value : "+approve);
		   
		 
		   taskService.complete( tasks.get(tasks.size()-1).getId(), approvedDoneVariables);
		    System.out.println(tasks.get(tasks.size()-1).getName()+" task get completed ");
		   // EmailClass.sendEmail(tasks.get(tasks.size()-1).getName());
		    List<Task> tasks4  =taskService.createTaskQuery().list();
		    if(approve == true){	
		    	System.out.println("Your task is Ended now...");
		    } else {
		    	this.openTask(tasks4, scanner, taskService);
			}
	}
	
	void openTask(List<Task> tasks, Scanner scanner, TaskService taskService){
		System.out.println("Your task id is " + tasks.get(tasks.size()-1).getId() + " and Task name is " + tasks.get(tasks.size()-1).getName() + "- which task you want to go,Please enter number ? 1.inProgress 2.Done");
    	boolean approved = scanner.nextLine().toLowerCase().equals("1");
    	Map<String, Object> approvedVariables = new HashMap<String, Object>();
    	approvedVariables.put("approved", approved);
        
    	//  System.out.println("task id : "+ tasks.getId());
    	taskService.complete( tasks.get(tasks.size()-1).getId(), approvedVariables);
    	System.out.println(tasks.get(tasks.size()-1).getName()+" task get completed ");
    	 //EmailClass.sendEmail(tasks.get(tasks.size()-1).getName());
    	tasks  =taskService.createTaskQuery().list();
	    if (tasks.get( tasks.size()-1).getName().equals("Done")){
	    	this.DoneOPtion(tasks, scanner, taskService);
	    } else {
	    	this.inProgress(tasks, scanner, taskService);
	    }
	}
	
	void inProgress(List<Task> tasks, Scanner scanner, TaskService taskService){
		System.out.println("Your task id is " + tasks.get(tasks.size()-1).getId() + " and Task name is " + tasks.get( tasks.size()-1).getName() + "- which task you want to go,Please enter number ? 1.Approve 2.Done 3.Open");
		   int input = Integer.parseInt(scanner.nextLine());
		   Map<String, Object> inputVariables = new HashMap<String, Object>();
		   inputVariables.put("input", input);
		   List<Task> tasks1  =taskService.createTaskQuery().list();
		   taskService.complete( tasks1.get(tasks1.size()-1).getId(), inputVariables);
		    System.out.println(tasks1.get(tasks1.size()-1).getName()+" task get completed ");
		   // EmailClass.sendEmail(tasks.get(tasks.size()-1).getName());
		    tasks  =taskService.createTaskQuery().list();
		    if (tasks.get( tasks.size()-1).getName().equals("Done")){
		    	this.DoneOPtion(tasks, scanner, taskService);
		    } else if (tasks.get( tasks.size()-1).getName().equals("Open")){
		    	this.openTask(tasks, scanner, taskService);
		    } else if (tasks.get( tasks.size()-1).getName().equals("Approve")) {
		    	this.approveTask(tasks, scanner, taskService);
		    }
		    
	}
	
	void approveTask(List<Task> tasks, Scanner scanner, TaskService taskService){
		//Approve code
    	System.out.println("Your task id is " + tasks.get(tasks.size()-1).getId() + " and Task name is " + tasks.get( tasks.size()-1).getName() + "- which task you want to go,Please enter number ? 1.Review 2.Done 3.Inprogress");
	   int inputone = Integer.parseInt(scanner.nextLine());
	   Map<String, Object> inputOneVariables = new HashMap<String, Object>();
	   inputOneVariables.put("inputOne",inputone);
	   
	   
	   List<Task> tasks2  =taskService.createTaskQuery().list();
	   taskService.complete( tasks2.get(tasks2.size()-1).getId(), inputOneVariables);
	    System.out.println(tasks2.get(tasks2.size()-1).getName()+" task get completed ");
	   // EmailClass.sendEmail(tasks.get(tasks.size()-1).getName());
	    tasks  =taskService.createTaskQuery().list();
	    if (tasks.get( tasks.size()-1).getName().equals("Done")){
	    	this.DoneOPtion(tasks, scanner, taskService);
	    } else if (tasks.get( tasks.size()-1).getName().equals("Review")){
	    	this.reviewTask(tasks, scanner, taskService);
	    } else if (tasks.get( tasks.size()-1).getName().equals("Inprogress")) {
	    	this.inProgress(tasks, scanner, taskService);
	    }
	}
	
	void reviewTask(List<Task> tasks, Scanner scanner, TaskService taskService){
		System.out.println("Your task id is " + tasks.get(tasks.size()-1).getId() + " and Task name is " + tasks.get( tasks.size()-1).getName() + "- which task you want to go,Please enter number ? 1.Done 2.Approve");
		   boolean approvedtwo = scanner.nextLine().toLowerCase().equals("1");
		   Map<String, Object> approvedOneVariables = new HashMap<String, Object>();
		   approvedOneVariables.put("approvedTwo",approvedtwo);
		   
		   
		   List<Task> tasks3  =taskService.createTaskQuery().list();
		   taskService.complete( tasks3.get(tasks3.size()-1).getId(), approvedOneVariables);
		    System.out.println(tasks3.get(tasks3.size()-1).getName()+" task get completed ");
		 //   EmailClass.sendEmail(tasks.get(tasks.size()-1).getName());
		    tasks  =taskService.createTaskQuery().list();
		    if (tasks.get( tasks.size()-1).getName().equals("Done")){
		    	this.DoneOPtion(tasks, scanner, taskService);
		    } else if (tasks.get( tasks.size()-1).getName().equals("Approve")) {
		    	this.approveTask(tasks, scanner, taskService);
		    }
	}
}
