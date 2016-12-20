package com.frame.security.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.frame.common.base.entity.BaseMysqlEntity;

@Entity
@Table(name = "sys_user_authority")
public class SystemUserAuthority extends BaseMysqlEntity<Long> {

	private static final long serialVersionUID = 8092446545663729988L;

	/** 用户 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "`username`", referencedColumnName = "`username`", insertable = false, updatable = false)
	private SystemSecurityUser user;

	/** 用户名 */
	@Column(name = "`username`")
	private String username;

	/** 权限 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "`auth_code`", referencedColumnName = "`code`", insertable = false, updatable = false)
	private SystemAuthority auth;

	/** 权限标识 */
	@Column(name = "`auth_code`")
	private String authCode;

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

	public SystemAuthority getAuth() {
		return auth;
	}

	public void setAuth(SystemAuthority auth) {
		this.auth = auth;
	}

	public String getAuthCode() {
		return authCode;
	}

	public void setAuthCode(String authCode) {
		this.authCode = authCode;
	}

}
