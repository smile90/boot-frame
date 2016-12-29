package com.frame.boot.frame.security.entity;

import com.frame.boot.frame.core.entity.BaseMysqlEntity;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "sys_authority")
public class SystemAuthority extends BaseMysqlEntity<Long> implements GrantedAuthority {

	private static final long serialVersionUID = -8114252629309110405L;

	/** 类别标识 */
	@Column(name = "`type_code`")
	private String typeCode;

	/** 父 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "`parent_code`", referencedColumnName = "`code`", insertable = false, updatable = false)
	private SystemAuthority parent;

	/** 父标识 */
	@Column(name = "`parent_code`")
	private String parentCode;

	/** 标识 */
	@Column(name = "`code`")
	private String code;

	/** 名称 */
	@Column(name = "`name`")
	private String name;

	/** URL */
	@Column(name = "`url`")
	private String url;

	/** 是否菜单 */
	@Column(name = "`menu`")
	private String menu;

	/** 图标 */
	@Column(name = "`icon`")
	private String icon;

	/** 是否显示 */
	@Column(name = "`display`")
	private String display;

	/** 是否验证 */
	@Column(name = "`validate`")
	private String validate;

	/** 是否可用 */
	@Column(name = "`enable`")
	private String enable;

	/** 描述 */
	@Column(name = "`description`")
	private String description;

	/** 子 */
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "parent")
	private List<SystemAuthority> children = new ArrayList<>();

	public String getTypeCode() {
		return typeCode;
	}

	public void setTypeCode(String typeCode) {
		this.typeCode = typeCode;
	}

	public SystemAuthority getParent() {
		return parent;
	}

	public void setParent(SystemAuthority parent) {
		this.parent = parent;
	}

	public String getParentCode() {
		return parentCode;
	}

	public void setParentCode(String parentCode) {
		this.parentCode = parentCode;
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

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	@Override
	public String getAuthority() {
		return code;
	}

	public String getMenu() {
		return menu;
	}

	public void setMenu(String menu) {
		this.menu = menu;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public String getDisplay() {
		return display;
	}

	public void setDisplay(String display) {
		this.display = display;
	}

	public String getValidate() {
		return validate;
	}

	public void setValidate(String validate) {
		this.validate = validate;
	}

	public String getEnable() {
		return enable;
	}

	public void setEnable(String enable) {
		this.enable = enable;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<SystemAuthority> getChildren() {
		return children;
	}

	public void setChildren(List<SystemAuthority> children) {
		this.children = children;
	}

}
