package com.hojin.com.hojin.model;

/**
 * Created by hojin on 15. 9. 23.
 */
public class HeritageVO {
    private String heritageId;
    private String heritageTitle;
    private String heritageSize;
    private String heritageInfo;

    public HeritageVO(String heritageId, String heritageTitle, String heritageSize){
        super();
        this.heritageId=heritageId;
        this.heritageTitle=heritageTitle;
        this.heritageSize=heritageSize;
    }

    public HeritageVO(String heritageId, String heritageTitle, String heritageSize, String heritageInfo){
        super();
        this.heritageId=heritageId;
        this.heritageTitle=heritageTitle;
        this.heritageSize=heritageSize;
        this.heritageInfo=heritageInfo;
    }

    public String getHeritageId() {
        return heritageId;
    }

    public void setHeritageId(String heritageId) {
        this.heritageId = heritageId;
    }

    public String getHeritageTitle() {
        return heritageTitle;
    }

    public void setHeritageTitle(String heritageTitle) {
        this.heritageTitle = heritageTitle;
    }

    public String getHeritageSize() {
        return heritageSize;
    }

    public void setHeritageSize(String heritageSize) {
        this.heritageSize = heritageSize;
    }

    public String getHeritageInfo() {
        return heritageInfo;
    }

    public void setHeritageInfo(String heritageInfo) {
        this.heritageInfo = heritageInfo;
    }
}
