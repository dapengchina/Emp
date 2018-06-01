package com.ability.emp.util;

public class TemplateMessageUtil {
	
	
	
	
	private String touser;//接收者（用户）的openid
	private String template_id;//所需下发的模板消息的id
	private String form_id;//表单提交场景下，为submit事件带上的formId；支付场景下，为本次支付的prepay_id
    private TemplateChildMessageUtil data;
       
       
       
       
       
	public TemplateChildMessageUtil getData() {
		return data;
	}
	public void setData(TemplateChildMessageUtil data) {
		this.data = data;
	}
	public String getTouser() {
		return touser;
	}
	public void setTouser(String touser) {
		this.touser = touser;
	}
	public String getTemplate_id() {
		return template_id;
	}
	public void setTemplate_id(String template_id) {
		this.template_id = template_id;
	}
	public String getForm_id() {
		return form_id;
	}
	public void setForm_id(String form_id) {
		this.form_id = form_id;
	}
	
       
}
