package com.frame.common.frame.base.param;

import java.io.Serializable;

public class IdParam implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private Long id;
	
	public IdParam(Long id) {
		this.id = id;
	}
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
}
