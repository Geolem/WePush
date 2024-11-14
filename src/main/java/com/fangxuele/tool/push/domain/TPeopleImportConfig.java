package com.fangxuele.tool.push.domain;

public class TPeopleImportConfig {
    private Integer id;

    private Integer peopleId;

    private String lastWay;

    private String lastFilePath;

    private String lastSql;

    private String appVersion;

    private String lastWayConfig;

    private String lastDataVersion;

    private String remark;

    private String createTime;

    private String modifiedTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getPeopleId() {
        return peopleId;
    }

    public void setPeopleId(Integer peopleId) {
        this.peopleId = peopleId;
    }

    public String getLastWay() {
        return lastWay;
    }

    public void setLastWay(String lastWay) {
        this.lastWay = lastWay == null ? null : lastWay.trim();
    }

    public String getLastFilePath() {
        return lastFilePath;
    }

    public void setLastFilePath(String lastFilePath) {
        this.lastFilePath = lastFilePath == null ? null : lastFilePath.trim();
    }

    public String getLastSql() {
        return lastSql;
    }

    public void setLastSql(String lastSql) {
        this.lastSql = lastSql == null ? null : lastSql.trim();
    }

    public String getAppVersion() {
        return appVersion;
    }

    public void setAppVersion(String appVersion) {
        this.appVersion = appVersion == null ? null : appVersion.trim();
    }

    public String getLastWayConfig() {
        return lastWayConfig;
    }

    public void setLastWayConfig(String lastWayConfig) {
        this.lastWayConfig = lastWayConfig;
    }

    public String getLastDataVersion() {
        return lastDataVersion;
    }

    public void setLastDataVersion(String lastDataVersion) {
        this.lastDataVersion = lastDataVersion;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime == null ? null : createTime.trim();
    }

    public String getModifiedTime() {
        return modifiedTime;
    }

    public void setModifiedTime(String modifiedTime) {
        this.modifiedTime = modifiedTime == null ? null : modifiedTime.trim();
    }
}