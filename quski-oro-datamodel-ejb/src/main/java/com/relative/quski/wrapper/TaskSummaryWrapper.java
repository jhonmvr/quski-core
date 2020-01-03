package com.relative.quski.wrapper;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class TaskSummaryWrapper {

	private List<TaskItemWrapper> taskSummary;

	@JsonProperty("task-summary")
	public List<TaskItemWrapper> getTaskSummary() {
		return taskSummary;
	}

	public void setTaskSummary(List<TaskItemWrapper> taskSummary) {
		this.taskSummary = taskSummary;
	}
	
	

}
