package com.chengjuiot.util;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@ConfigurationProperties(prefix = "file")
@Component
public class FileUrlConfig {
    private String mulu_path;
    private String zhangjie_path;
    private String linshiDir;
    private String index_path;
    private String replaceStr;
    private String oss_host;
    private String LookupPrefix;
    public String getIndex_path() {
        return index_path;
    }

    public String getLinshiDir() {
        return linshiDir;
    }

    public void setLinshiDir(String linshiDir) {
        this.linshiDir = linshiDir;
    }

    public void setIndex_path(String index_path) {
        this.index_path = index_path;
    }

    public String getLookupPrefix() {
        return LookupPrefix;
    }

    public void setLookupPrefix(String lookupPrefix) {
        LookupPrefix = lookupPrefix;
    }

    public String getOss_host() {
        return oss_host;
    }

    public void setOss_host(String oss_host) {
        this.oss_host = oss_host;
    }

    public String getMulu_path() {
        return mulu_path;
    }

    public void setMulu_path(String mulu_path) {
        this.mulu_path = mulu_path;
    }

    public String getZhangjie_path() {
        return zhangjie_path;
    }

    public void setZhangjie_path(String zhangjie_path) {
        this.zhangjie_path = zhangjie_path;
    }

    public String getReplaceStr() {
        return replaceStr;
    }

    public void setReplaceStr(String replaceStr) {
        this.replaceStr = replaceStr;
    }
}
