package com.relative.quski.wrapper;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class TaskItemWrapper implements Serializable{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 8075316412347754433L;


	private String taskId;
	 
	
	private String taskName;
	
	
	private String taskProcessInstanceId;
	
	private String taskStatus;
	
	private String taskActualOwner;
	
	

	@JsonProperty("task-id")
	public String getTaskId() {
		return taskId;
	}

	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}

	@JsonProperty("task-name")
	public String getTaskName() {
		return taskName;
	}

	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}

	@JsonProperty("task-process-instance-id")
	public String getTaskProcessInstanceId() {
		return taskProcessInstanceId;
	}

	public void setTaskProcessInstanceId(String taskProcessInstanceId) {
		this.taskProcessInstanceId = taskProcessInstanceId;
	}
	
	
	@JsonProperty("task-status")
	public String getTaskStatus() {
		return taskStatus;
	}

	public void setTaskStatus(String taskStatus) {
		this.taskStatus = taskStatus;
	}

	
	@JsonProperty("task-actual-owner")
	public String getTaskActualOwner() {
		return taskActualOwner;
	}

	public void setTaskActualOwner(String taskActualOwner) {
		this.taskActualOwner = taskActualOwner;
	}
	
	
	
}
