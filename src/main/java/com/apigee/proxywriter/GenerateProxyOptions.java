package com.apigee.proxywriter;

public class GenerateProxyOptions {
    private final String wsdl;
    private final String port;
    private final boolean passthrough;
    private final String description;
    private final String basepath;
    private final String templateFolder;
    private final String vHosts;
    private final boolean cors;
    private final boolean apiKey;
    private final boolean basicAuth;
    private final boolean calloutACL;
    private final boolean header;

    private final boolean oauth;
    private final boolean quota;
    private final boolean caseInsensitive;
    private final String operationsFilter;
    private final String defaultOps;
    private final int bodyLimitSize;

    public GenerateProxyOptions(String wsdl, String port, boolean passthrough, String description, String basepath,
                                String vHosts, boolean cors, boolean apiKey, boolean oauth, boolean quota,
                                boolean basicAuth,
                                boolean calloutAuth,
                                String operationsFilter, String templateFolder, boolean header,
                                boolean caseInsensitive, int bodyLimitSize, String defaultOps) {
        this.wsdl = wsdl;
        this.port = port;
        this.passthrough = passthrough;
        this.description = description;
        this.basepath = basepath;
        this.vHosts = vHosts;
        this.cors = cors;
        this.apiKey = apiKey;
        this.oauth = oauth;
        this.basicAuth = basicAuth;
        this.calloutACL = calloutAuth;
        this.header = header;
        this.templateFolder = templateFolder;
        this.quota = quota;
        this.operationsFilter = operationsFilter;
        this.caseInsensitive = caseInsensitive;
        this.defaultOps = defaultOps;
        this.bodyLimitSize = bodyLimitSize;
    }

    public String getWsdl() {
        return wsdl;
    }

    public String getPort() {
        return port;
    }

    public boolean isPassthrough() {
        return passthrough;
    }

    public String getDescription() {
        return description;
    }

    public String getBasepath() {
        return basepath;
    }

    public String getvHosts() {
        return vHosts;
    }

    public boolean isCors() {
        return cors;
    }

    public boolean isApiKey() {
        return apiKey;
    }

    public boolean isOauth() {
        return oauth;
    }

    public boolean isQuota() {
        return quota;
    }

    public String getOperationsFilter() {
        return operationsFilter;
    }

    public String getTemplateFolder() {
        return templateFolder;
    }

    public boolean isBasicAuth() {
        return basicAuth;
    }

    public boolean isCalloutACL() {
        return calloutACL;
    }

    public boolean isHeader() {
        return header;
    }

    public boolean isCaseInsensitive() {
        return caseInsensitive;
    }

    public int getBodyLimitSize() { return bodyLimitSize; }

    public String getDefaultOps() { return defaultOps; }
}
