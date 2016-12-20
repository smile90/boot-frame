package com.frame.boot.frame.common.security.properties;

import java.util.Arrays;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "frame.security")
public class SystemSecurityProperties {

	private boolean enableCsrf = true;

	private boolean enableFrameOptions = true;

	/** URL配置 */
	private Url url = new Url();

	public boolean isEnableCsrf() {
		return enableCsrf;
	}

	public void setEnableCsrf(boolean enableCsrf) {
		this.enableCsrf = enableCsrf;
	}

	public boolean isEnableFrameOptions() {
		return enableFrameOptions;
	}

	public void setEnableFrameOptions(boolean enableFrameOptions) {
		this.enableFrameOptions = enableFrameOptions;
	}

	public Url getUrl() {
		return url;
	}

	public void setUrl(Url url) {
		this.url = url;
	}

	public class Url {
		private String loginUrl = "/admin/login";
		private String logoutUrl = "/admin/logout";
		private String indexUrl = "/admin";
		private String[] permitPaths = new String[] {"/resources/**", "/admin/login"};
		private String[] authenticatePaths = new String[] {"/admin/**"};

		public String getLoginUrl() {
			return loginUrl;
		}

		public void setLoginUrl(String loginUrl) {
			this.loginUrl = loginUrl;
		}

		public String getLogoutUrl() {
			return logoutUrl;
		}

		public void setLogoutUrl(String logoutUrl) {
			this.logoutUrl = logoutUrl;
		}

		public String getIndexUrl() {
			return indexUrl;
		}

		public void setIndexUrl(String indexUrl) {
			this.indexUrl = indexUrl;
		}

		public String[] getPermitPaths() {
			return permitPaths;
		}

		public void setPermitPaths(String[] permitPaths) {
			this.permitPaths = permitPaths;
		}

		public String[] getAuthenticatePaths() {
			return authenticatePaths;
		}

		public void setAuthenticatePaths(String[] authenticatePaths) {
			this.authenticatePaths = authenticatePaths;
		}

		@Override
		public String toString() {
			return "Url [loginUrl=" + loginUrl + ", logoutUrl=" + logoutUrl + ", indexUrl=" + indexUrl + ", permitPaths=" + Arrays.toString(permitPaths) + ", authenticatePaths=" + Arrays.toString(authenticatePaths) + "]";
		}

	}

	@Override
	public String toString() {
		return "SystemSecurityProperties [enableCsrf=" + enableCsrf + ", enableFrameOptions=" + enableFrameOptions + ", url=" + url + "]";
	}

}
