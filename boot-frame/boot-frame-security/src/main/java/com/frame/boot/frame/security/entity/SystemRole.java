package com.frame.boot.frame.security.entity;

import com.frame.boot.frame.model.entity.BaseMysqlEntity;

import javax.persistence.*;


@Entity
@Table(name = "sys_role")
public class SystemRole extends BaseMysqlEntity<Long> {

	private static final long serialVersionUID = -3516710790338452063L;

	/** 类别标识 */
	@Column(name = "`type_code`")
	private String typeCode;

	/** 标识 */
	@Column(name = "`code`")
	private String code;

	/** 名称 */
	@Column(name = "`name`")
	private String name;

	/** 描述 */
	@Column(name = "`description`")
	private String description;

	public String getTypeCode() {
		return typeCode;
	}

	public void setTypeCode(String typeCode) {
		this.typeCode = typeCode;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
