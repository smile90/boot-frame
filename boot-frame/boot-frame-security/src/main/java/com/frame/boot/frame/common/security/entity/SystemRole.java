package com.frame.boot.frame.common.security.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.frame.common.base.entity.BaseMysqlEntity;
import com.frame.core.system.entity.SystemType;

@Entity
@Table(name = "sys_role")
public class SystemRole extends BaseMysqlEntity<Long> {

	private static final long serialVersionUID = -3516710790338452063L;

	/** 类别 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "`type_code`", referencedColumnName = "`code`", insertable = false, updatable = false)
	private SystemType type;

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

	public SystemType getType() {
		return type;
	}

	public void setType(SystemType type) {
		this.type = type;
	}

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
