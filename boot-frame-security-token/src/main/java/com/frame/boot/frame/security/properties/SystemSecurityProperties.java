package com.frame.boot.frame.security.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Configuration
@ConfigurationProperties(prefix = "frame.security")
public class SystemSecurityProperties {

    private String menuTypeCode = "SYS_MENU";
    private String sysModuleTypeCode = "SYS_MODULE";
    private String defualtUserPwd = "123456";

    private boolean enableCsrf = true;

    private boolean enableFrameOptions = true;

    private boolean enableValidCode = true;

    private boolean enableRememberMe = true;

    /** URL配置 */
    private Url url = new Url();

    public String getMenuTypeCode() {
        return menuTypeCode;
    }

    public void setMenuTypeCode(String menuTypeCode) {
        this.menuTypeCode = menuTypeCode;
    }

    public String getSysModuleTypeCode() {
        return sysModuleTypeCode;
    }

    public void setSysModuleTypeCode(String sysModuleTypeCode) {
        this.sysModuleTypeCode = sysModuleTypeCode;
    }

    public String getDefualtUserPwd() {
        return defualtUserPwd;
    }

    public void setDefualtUserPwd(String defualtUserPwd) {
        this.defualtUserPwd = defualtUserPwd;
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

    public boolean isEnableValidCode() {
        return enableValidCode;
    }

    public void setEnableValidCode(boolean enableValidCode) {
        this.enableValidCode = enableValidCode;
    }

    public boolean isEnableRememberMe() {
        return enableRememberMe;
    }

    public void setEnableRememberMe(boolean enableRememberMe) {
        this.enableRememberMe = enableRememberMe;
    }

    public Url getUrl() {
        return url;
    }

    public void setUrl(Url url) {
        this.url = url;
    }

    public class Url {
        private String loginPageUrl = "/sys/login";
        private String loginProcessUrl = "/sys/login";
        private String logoutUrl = "/sys/logout";
        private String indexUrl = "/sys/index";
        private String[] permitPaths = new String[] {"/static/**", "/**/*.css", "/**/*.js", "/**/*.png", "/**/*.gif", "/**/*.jpg", "/**/*.jpeg", "/**/*.bmp", "/validCode/login"};
        private String[] authenticatePaths = new String[] {"/**"};
        private String[] csrfIgnoringPaths = new String[] {"/druid/**"};

        public String getLoginPageUrl() {
            return loginPageUrl;
        }

        public void setLoginPageUrl(String loginPageUrl) {
            this.loginPageUrl = loginPageUrl;
        }

        public String getLoginProcessUrl() {
            return loginProcessUrl;
        }

        public void setLoginProcessUrl(String loginProcessUrl) {
            this.loginProcessUrl = loginProcessUrl;
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
            List<String> permitPathList = new ArrayList<>();
            permitPathList.addAll(Arrays.asList(permitPaths));
            permitPathList.add(getLoginPageUrl());
            permitPathList.add(getLoginProcessUrl());
            permitPathList.add(getLogoutUrl());
            return permitPathList.toArray(new String[0]);
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
            sb.append("loginPageUrl='").append(loginPageUrl).append('\'');
            sb.append(", loginProcessUrl='").append(loginProcessUrl).append('\'');
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
        sb.append(", enableValidCode=").append(enableValidCode);
        sb.append(", enableRememberMe=").append(enableRememberMe);
        sb.append(", url=").append(url);
        sb.append('}');
        return sb.toString();
    }
}
