package com.frame.boot.frame.security.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;

@Configuration
@ConfigurationProperties(prefix = "frame.security")
public class SystemSecurityProperties {

    private String menuTypeCode;

    private boolean enableCsrf = true;

    private boolean enableFrameOptions = true;

    /** URL配置 */
    private Url url = new Url();

    public String getMenuTypeCode() {
        return menuTypeCode;
    }

    public void setMenuTypeCode(String menuTypeCode) {
        this.menuTypeCode = menuTypeCode;
    }

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
        private String loginUrl = "/sys/login";
        private String logoutUrl = "/sys/logout";
        private String indexUrl = "/sys/index";
        private String[] permitPaths = new String[] {"/static/**", "/sys/login"};
        private String[] authenticatePaths = new String[] {"/sys/**"};
        private String[] csrfIgnoringPaths = new String[] {"/druid/**"};

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

        public String[] getCsrfIgnoringPaths() {
            return csrfIgnoringPaths;
        }

        public void setCsrfIgnoringPaths(String[] csrfIgnoringPaths) {
            this.csrfIgnoringPaths = csrfIgnoringPaths;
        }

        @Override
        public String toString() {
            final StringBuffer sb = new StringBuffer("Url{");
            sb.append("loginUrl='").append(loginUrl).append('\'');
            sb.append(", logoutUrl='").append(logoutUrl).append('\'');
            sb.append(", indexUrl='").append(indexUrl).append('\'');
            sb.append(", permitPaths=").append(permitPaths == null ? "null" : Arrays.asList(permitPaths).toString());
            sb.append(", authenticatePaths=").append(authenticatePaths == null ? "null" : Arrays.asList(authenticatePaths).toString());
            sb.append(", csrfIgnoringPaths=").append(csrfIgnoringPaths == null ? "null" : Arrays.asList(csrfIgnoringPaths).toString());
            sb.append('}');
            return sb.toString();
        }
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("SystemSecurityProperties{");
        sb.append("menuTypeCode='").append(menuTypeCode).append('\'');
        sb.append(", enableCsrf=").append(enableCsrf);
        sb.append(", enableFrameOptions=").append(enableFrameOptions);
        sb.append(", url=").append(url);
        sb.append('}');
        return sb.toString();
    }
}
