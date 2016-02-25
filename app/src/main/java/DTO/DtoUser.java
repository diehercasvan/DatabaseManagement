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
    private String sUri;
    private int iID;

    public DtoUser() {
    }

    public DtoUser(int iID,String sName, String sLast_Name, String sTelephone , String sMail, String sUri) {
        this.iID = iID;
        this.sName = sName;
        this.sLast_Name = sLast_Name;
        this.sTelephone = sTelephone;
        this.sMail = sMail;
        this.sUri = sUri;

    }



    public int getiID() {
        return iID;
    }

    public void setiID(int iID) {
        this.iID = iID;
    }

    public String getsUri() {
        return sUri;
    }

    public void setsUri(String sUri) {
        this.sUri = sUri;
    }

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
