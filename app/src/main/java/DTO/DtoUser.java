package DTO;

/**
 * Created by DIEGO CASALLAS on 22/02/2016.
 */
public class DtoUser {
    private String sName;
    private String sLast_Name;
    private String sMail;
    private String sTelephone;
    private String sPhoto;
    private int iImgUrl;

    public int getiImgUrl() {
        return iImgUrl;
    }

    public void setiImgUrl(int iImgUrl) {
        this.iImgUrl = iImgUrl;
    }

    public String getsName() {
        return sName;
    }

    public void setsName(String sName) {
        this.sName = sName;
    }

    public String getsLast_Name() {
        return sLast_Name;
    }

    public void setsLast_Name(String sLast_Name) {
        this.sLast_Name = sLast_Name;
    }

    public String getsMail() {
        return sMail;
    }

    public void setsMail(String sMail) {
        this.sMail = sMail;
    }

    public String getsTelephone() {
        return sTelephone;
    }

    public void setsTelephone(String sTelephone) {
        this.sTelephone = sTelephone;
    }

    public String getsPhoto() {
        return sPhoto;
    }

    public void setsPhoto(String sPhoto) {
        this.sPhoto = sPhoto;
    }
}
