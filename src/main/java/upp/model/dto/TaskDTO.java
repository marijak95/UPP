package upp.model.dto;

public class TaskDTO {

	private String type;
	private String processInstanceId;
	private String taskId;
	private String id;
	private String name;
	
	public TaskDTO(){}
	
	
	public TaskDTO(String type, String processInstanceId, String taskId) {
	
		this.type = type;
		this.processInstanceId = processInstanceId;
		this.taskId = taskId;
	}
	


	public String getTaskId() {
		return taskId;
	}


	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}


	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getProcessInstanceId() {
		return processInstanceId;
	}
	public void setProcessInstanceId(String processInstanceId) {
		this.processInstanceId = processInstanceId;
	}


	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}
	
	
}
