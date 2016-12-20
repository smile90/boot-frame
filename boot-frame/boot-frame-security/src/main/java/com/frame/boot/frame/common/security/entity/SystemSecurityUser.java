package com.frame.security.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.frame.base.bean.IUser;
import com.frame.base.enums.UserStatus;
import com.frame.common.base.entity.BaseMysqlEntity;
import com.frame.common.utils.EmptyUtil;
import com.frame.core.system.entity.SystemType;

/**
 * 系统权限用户
 * @author duancq
 * 2016年7月15日 下午2:08:31
 */

@Entity
@Table(name = "sys_security_user")
public class SystemSecurityUser extends BaseMysqlEntity<Long> implements IUser, UserDetails {

	@Transient
	private Logger logger = LoggerFactory.getLogger(getClass());

	private static final long serialVersionUID = 1L;

	/** 类别 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "`type_code`", referencedColumnName = "`code`", insertable = false, updatable = false)
	private SystemType type;

	/** 类别标识 */
	@Column(name = "`type_code`")
	private String typeCode;

	/** 用户名 */
	@Column(name = "`username`")
	private String username;

	/** 真实姓名 */
	@Column(name = "`realname`")
	private String realname;

	/** 密码 */
	@Column(name = "`password`")
	private String password;

	/** 描述 */
	@Column(name = "`description`")
	private String description;

	/** 用户权限 */
	@OneToMany(fetch = FetchType.LAZY)
	@JoinColumn(name = "`username`", referencedColumnName = "`username`")
	List<SystemUserAuthority> userAuthorities;

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

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getRealname() {
		return realname;
	}

	public void setRealname(String realname) {
		this.realname = realname;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<SystemUserAuthority> getUserAuthorities() {
		return userAuthorities;
	}

	public void setUserAuthorities(List<SystemUserAuthority> userAuthorities) {
		this.userAuthorities = userAuthorities;
	}

	@Override
	public boolean isAccountNonExpired() {
		return !UserStatus.EXPIRED.name().equals(status);
	}

	@Override
	public boolean isAccountNonLocked() {
		return !UserStatus.LOCKED.name().equals(status);
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO 后期启用
		return true;
	}

	@Override
	public boolean isEnabled() {
		try {
			return (!UserStatus.valueOf(status).equals(UserStatus.DISABLED) && !UserStatus.valueOf(status).equals(UserStatus.DELETED));
		} catch (Exception e) {
			logger.error("userStatus error. status:{}", status, e);
			return false;
		}
	}

	@Override
	public List<GrantedAuthority> getAuthorities() {
		if (EmptyUtil.notEmpty(userAuthorities)) {
			List<GrantedAuthority> authorities = new ArrayList<>();
			for (SystemUserAuthority userAuthority : userAuthorities) {
				authorities.add(userAuthority.getAuth());
			}
			return authorities;
		} else {
			return null;
		}
	}

}
