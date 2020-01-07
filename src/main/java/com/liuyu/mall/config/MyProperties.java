package com.liuyu.mall.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author liuyu
 */
@Component
@ConfigurationProperties(prefix = "liuyu", ignoreUnknownFields = false)
public class MyProperties {

    /**
     * SWAGGER参数
     */
    private final Swagger swagger = new Swagger();
    /**
     * 安全认证
     */
    private final Auth auth = new Auth();

    public Swagger getSwagger() {
        return swagger;
    }

    public Auth getAuth() {
        return auth;
    }

    /**
     * SWAGGER接口文档参数
     */
    public static class Swagger {
        private String title;
        private String description;
        private String version;
        private String termsOfServiceUrl;
        private String contactName;
        private String contactUrl;
        private String contactEmail;
        private String license;
        private String licenseUrl;

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getVersion() {
            return version;
        }

        public void setVersion(String version) {
            this.version = version;
        }

        public String getTermsOfServiceUrl() {
            return termsOfServiceUrl;
        }

        public void setTermsOfServiceUrl(String termsOfServiceUrl) {
            this.termsOfServiceUrl = termsOfServiceUrl;
        }

        public String getContactName() {
            return contactName;
        }

        public void setContactName(String contactName) {
            this.contactName = contactName;
        }

        public String getContactUrl() {
            return contactUrl;
        }

        public void setContactUrl(String contactUrl) {
            this.contactUrl = contactUrl;
        }

        public String getContactEmail() {
            return contactEmail;
        }

        public void setContactEmail(String contactEmail) {
            this.contactEmail = contactEmail;
        }

        public String getLicense() {
            return license;
        }

        public void setLicense(String license) {
            this.license = license;
        }

        public String getLicenseUrl() {
            return licenseUrl;
        }

        public void setLicenseUrl(String licenseUrl) {
            this.licenseUrl = licenseUrl;
        }
    }

    public static class Auth {
        /**
         * token过期时间（分钟）
         */
        private Integer tokenExpireTime;
        /**
         * 用户选择保存登录状态对应token过期时间（天）
         */
        private Integer saveLoginTime;
        /**
         * 限制用户登陆错误次数（次）
         */
        private Integer loginTimeLimit;
        /**
         * 错误超过次数后多少分钟后才能继续登录（分钟）
         */
        private Integer loginAfterTime;
        /**
         * 忽略安全认证的URL
         */
        private List<String> ignoreUrls;

        public Integer getTokenExpireTime() {
            return tokenExpireTime;
        }

        public void setTokenExpireTime(Integer tokenExpireTime) {
            this.tokenExpireTime = tokenExpireTime;
        }

        public Integer getSaveLoginTime() {
            return saveLoginTime;
        }

        public void setSaveLoginTime(Integer saveLoginTime) {
            this.saveLoginTime = saveLoginTime;
        }

        public Integer getLoginTimeLimit() {
            return loginTimeLimit;
        }

        public void setLoginTimeLimit(Integer loginTimeLimit) {
            this.loginTimeLimit = loginTimeLimit;
        }

        public Integer getLoginAfterTime() {
            return loginAfterTime;
        }

        public void setLoginAfterTime(Integer loginAfterTime) {
            this.loginAfterTime = loginAfterTime;
        }

        public List<String> getIgnoreUrls() {
            return ignoreUrls;
        }

        public void setIgnoreUrls(List<String> ignoreUrls) {
            this.ignoreUrls = ignoreUrls;
        }
    }
}
