package upp.model.dto;

import java.io.Serializable;
import java.util.List;

import org.camunda.bpm.engine.form.FormField;

public class FormFieldsDto implements Serializable{
	
	String taskId;
	List<FormField> formFields;
	String processInstanceId;
	String naslovRada;
	String autorRada;
	
	public FormFieldsDto(){}
	
	public FormFieldsDto(String taskId, String processInstanceId,List<FormField> formFields) {
		super();
		this.taskId = taskId;
		this.formFields = formFields;
		this.processInstanceId = processInstanceId;
	}
	
	public FormFieldsDto(String taskId, String processInstanceId,List<FormField> formFields,
			String naslovRada, String autorRada) {
		super();
		this.taskId = taskId;
		this.formFields = formFields;
		this.processInstanceId = processInstanceId;
		this.naslovRada = naslovRada;
		this.autorRada = autorRada;
	}


	public String getTaskId() {
		return taskId;
	}
	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}
	public List<FormField> getFormField() {
		return formFields;
	}
	public void setFormField(List<FormField> formFields) {
		this.formFields = formFields;
	}
	public String getProcessInstanceId() {
		return processInstanceId;
	}
	public void setProcessInstanceId(String processInstanceId) {
		this.processInstanceId = processInstanceId;
	}


	public String getNaslovRada() {
		return naslovRada;
	}


	public void setNaslovRada(String naslovRada) {
		this.naslovRada = naslovRada;
	}


	public String getAutorRada() {
		return autorRada;
	}


	public void setAutorRada(String autorRada) {
		this.autorRada = autorRada;
	}
	
	

}
