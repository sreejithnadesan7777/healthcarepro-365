package com.healthcarepro.healthcarepro.model;

public class Medication {
    private String medicationid;
    private String medicationname;
    private String medicationtype;
    private String medicationdayssupply;
    private String medicationrefill;
    private String providerid;

    public String getMedicationid() {
        return medicationid;
    }

    public void setMedicationid(String medicationid) {
        this.medicationid = medicationid;
    }

    public String getMedicationname() {
        return medicationname;
    }

    public void setMedicationname(String medicationname) {
        this.medicationname = medicationname;
    }

    public String getMedicationtype() {
        return medicationtype;
    }

    public void setMedicationtype(String medicationtype) {
        this.medicationtype = medicationtype;
    }

    public String getMedicationdayssupply() {
        return medicationdayssupply;
    }

    public void setMedicationdayssupply(String medicationdayssupply) {
        this.medicationdayssupply = medicationdayssupply;
    }

    public String getMedicationrefill() {
        return medicationrefill;
    }

    public void setMedicationrefill(String medicationrefill) {
        this.medicationrefill = medicationrefill;
    }

    public String getProviderid() {
        return providerid;
    }

    public void setProviderid(String providerid) {
        this.providerid = providerid;
    }
}
