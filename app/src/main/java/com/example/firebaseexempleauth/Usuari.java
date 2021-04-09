package com.example.firebaseexempleauth;

public class Usuari {
    private String mUid;
    private String mEmail;
    private String mNom;
    private String mTelefon;



    public String getmUid() {
        return mUid;
    }

    public void setmUid(String mUid) {
        this.mUid = mUid;
    }

    public String getmEmail() {
        return mEmail;
    }

    public void setmEmail(String mEmail) {
        this.mEmail = mEmail;
    }

    public String getmNom() {
        return mNom;
    }

    public void setmNom(String mNom) {
        this.mNom = mNom;
    }

    public String getmTelefon() {
        return mTelefon;
    }

    public void setmTelefon(String mTelefon) {
        this.mTelefon = mTelefon;
    }

    @Override
    public String toString() {
        return "Usuari{" +
                "mUid='" + mUid + '\'' +
                ", mEmail='" + mEmail + '\'' +
                ", mNom='" + mNom + '\'' +
                ", mTelefon='" + mTelefon + '\'' +
                '}';
    }
}
