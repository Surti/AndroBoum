package com.example.leman.androboum;

/**
 * Created by leman on 19/12/2017.
 */

public class Profil {

    // les différents status d'un utilisateur
    enum BombStatut {IDLE, AWAITING, BOMBER, BOMBED};
    // mon email
    private String email;
    // mon statut de connexion (vrai ou faux)
    boolean isConnected;
    // mon identifiant unique
    private String uid;
    // mon statut actuel
    private BombStatut statut = BombStatut.IDLE;
    // l'identifiant de mon adversaire
    private String otherUserUID;
    // l'email de mon adversaire
    private String otherUseremail;
    // mon score
    private long score = 0;
    // coordonnées GPS
    private double latitude = 49.80;
    private double longitude = 3.233333;

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getLatitude() {

        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public Profil() {     }

    public String getEmail() {         return email;     }

    public void setEmail(String email) {         this.email = email;     }

    public boolean isConnected() {         return isConnected;     }

    public void setConnected(boolean connected) {         isConnected = connected;     }

    public void setStatut(BombStatut statut) {
        this.statut = statut;
    }

    public void setOtherUserUID(String otherUserUID) {
        this.otherUserUID = otherUserUID;
    }

    public void setOtherUseremail(String otherUseremail) {
        this.otherUseremail = otherUseremail;
    }

    public void setScore(long score) {
        this.score = score;
    }

    public BombStatut getStatut() {

        return statut;
    }

    public String getOtherUserUID() {
        return otherUserUID;
    }

    public String getOtherUseremail() {
        return otherUseremail;
    }

    public long getScore() {

        return score;
    }

    public String getUid() {         return uid;     }

    public void setUid(String uid) {         this.uid = uid;     } }
