package com.healthcarepro.healthcarepro.model;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "provider")
public class Provider {
    private String providerid;
    private String providername;
    private String providerspecialty;
    private String providercertification;
    private String medicalcredentaials;
    private String provideraffilication;
    private String providergraduationyear;
    private String medicalschool;
    private String provideraddress;
    private String providercity;
    private String providerstate;
    private String provideremail;
    private String providerphone;
    private String providerzip;
    private String providerhospital;
    private int providerrating;
    private int inthealthcareproexprating;
    private int healthcareprocosteffectiverating;
    private String providerqualification;

    public String getProviderid() {
        return providerid;
    }

    public void setProviderid(String providerid) {
        this.providerid = providerid;
    }

    public String getProvidername() {
        return providername;
    }

    public void setProvidername(String providername) {
        this.providername = providername;
    }

    public String getProviderspecialty() {
        return providerspecialty;
    }

    public void setProviderspecialty(String providerspecialty) {
        this.providerspecialty = providerspecialty;
    }

    public String getProvidercertification() {
        return providercertification;
    }

    public void setProvidercertification(String providercertification) {
        this.providercertification = providercertification;
    }

    public String getMedicalcredentaials() {
        return medicalcredentaials;
    }

    public void setMedicalcredentaials(String medicalcredentaials) {
        this.medicalcredentaials = medicalcredentaials;
    }

    public String getProvideraffilication() {
        return provideraffilication;
    }

    public void setProvideraffilication(String provideraffilication) {
        this.provideraffilication = provideraffilication;
    }

    public String getProvidergraduationyear() {
        return providergraduationyear;
    }

    public void setProvidergraduationyear(String providergraduationyear) {
        this.providergraduationyear = providergraduationyear;
    }

    public String getMedicalschool() {
        return medicalschool;
    }

    public void setMedicalschool(String medicalschool) {
        this.medicalschool = medicalschool;
    }

    public String getProvideraddress() {
        return provideraddress;
    }

    public void setProvideraddress(String provideraddress) {
        this.provideraddress = provideraddress;
    }

    public String getProvidercity() {
        return providercity;
    }

    public void setProvidercity(String providercity) {
        this.providercity = providercity;
    }

    public String getProviderstate() {
        return providerstate;
    }

    public void setProviderstate(String providerstate) {
        this.providerstate = providerstate;
    }

    public String getProvideremail() {
        return provideremail;
    }

    public void setProvideremail(String provideremail) {
        this.provideremail = provideremail;
    }

    public String getProviderphone() {
        return providerphone;
    }

    public void setProviderphone(String providerphone) {
        this.providerphone = providerphone;
    }

    public String getProviderzip() {
        return providerzip;
    }

    public void setProviderzip(String providerzip) {
        this.providerzip = providerzip;
    }

    public int getProviderrating() {
        return providerrating;
    }

    public void setProviderrating(int providerrating) {
        this.providerrating = providerrating;
    }

    public int getInthealthcareproexprating() {
        return inthealthcareproexprating;
    }

    public void setInthealthcareproexprating(int inthealthcareproexprating) {
        this.inthealthcareproexprating = inthealthcareproexprating;
    }

    public int getHealthcareprocosteffectiverating() {
        return healthcareprocosteffectiverating;
    }

    public void setHealthcareprocosteffectiverating(int healthcareprocosteffectiverating) {
        this.healthcareprocosteffectiverating = healthcareprocosteffectiverating;
    }
    public String getProviderhospital() {
        return providerhospital;
    }

    public void setProviderhospital(String providerhospital) {
        this.providerhospital = providerhospital;
    }

    public String getProviderqualification() {
        return providerqualification;
    }

    public void setProviderqualification(String providerqualification) {
        this.providerqualification = providerqualification;
    }
}
