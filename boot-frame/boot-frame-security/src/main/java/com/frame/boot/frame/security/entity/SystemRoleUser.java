package com.frame.boot.frame.security.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.frame.common.base.entity.BaseMysqlEntity;

@Entity
@Table(name = "sys_role_user")
public class SystemRoleUser extends BaseMysqlEntity<Long> {

	private static final long serialVersionUID = 2272510494619920162L;

	/** 角色 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "`role_code`", referencedColumnName = "`code`", insertable = false, updatable = false)
	private SystemRole role;

	/** 角色标识 */
	@Column(name = "`role_code`")
	private String roleCode;

	/** 用户 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "`username`", referencedColumnName = "`username`", insertable = false, updatable = false)
	private SystemSecurityUser user;

	/** 用户名 */
	@Column(name = "`username`")
	private String username;

	public SystemRole getRole() {
		return role;
	}

	public void setRole(SystemRole role) {
		this.role = role;
	}

	public String getRoleCode() {
		return roleCode;
	}

	public void setRoleCode(String roleCode) {
		this.roleCode = roleCode;
	}

	public SystemSecurityUser getUser() {
		return user;
	}

	public void setUser(SystemSecurityUser user) {
		this.user = user;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

}
