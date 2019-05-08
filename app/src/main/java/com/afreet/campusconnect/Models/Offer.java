package com.afreet.campusconnect.Models;

public class Offer {

    private String offerId;
    private String vendorId;
    private String offerName;
    private String offerDescription;
    private String offerValidity;
    private String offerPasscode;
    private String offerDateCreated;
    private String offerRedeemsCount;

    public Offer() {
    }

    public Offer(String offerId, String vendorId, String offerName, String offerDescription, String offerValidity, String offerPasscode, String offerDateCreated, String offerRedeemsCount) {
        this.offerId = offerId;
        this.vendorId = vendorId;
        this.offerName = offerName;
        this.offerDescription = offerDescription;
        this.offerValidity = offerValidity;
        this.offerPasscode = offerPasscode;
        this.offerDateCreated = offerDateCreated;
        this.offerRedeemsCount = offerRedeemsCount;
    }

    public void addOffer(String offerId, String vendorId, String offerName, String offerDescription, String offerValidity, String offerPasscode, String offerDateCreated, String offerRedeemsCount) {
        this.offerId = offerId;
        this.vendorId = vendorId;
        this.offerName = offerName;
        this.offerDescription = offerDescription;
        this.offerValidity = offerValidity;
        this.offerPasscode = offerPasscode;
        this.offerDateCreated = offerDateCreated;
        this.offerRedeemsCount = offerRedeemsCount;
    }

    public String getOfferId() {
        return offerId;
    }

    public void setOfferId(String offerId) {
        this.offerId = offerId;
    }

    public String getVendorId() {
        return vendorId;
    }

    public void setVendorId(String vendorId) {
        this.vendorId = vendorId;
    }

    public String getOfferName() {
        return offerName;
    }

    public void setOfferName(String offerName) {
        this.offerName = offerName;
    }

    public String getOfferDescription() {
        return offerDescription;
    }

    public void setOfferDescription(String offerDescription) {
        this.offerDescription = offerDescription;
    }

    public String getOfferValidity() {
        return offerValidity;
    }

    public void setOfferValidity(String offerValidity) {
        this.offerValidity = offerValidity;
    }

    public String getOfferPasscode() {
        return offerPasscode;
    }

    public void setOfferPasscode(String offerPasscode) {
        this.offerPasscode = offerPasscode;
    }

    public String getOfferDateCreated() {
        return offerDateCreated;
    }

    public void setOfferDateCreated(String offerDateCreated) {
        this.offerDateCreated = offerDateCreated;
    }

    public String getOfferRedeemsCount() {
        return offerRedeemsCount;
    }

    public void setOfferRedeemsCount(String offerRedeemsCount) {
        this.offerRedeemsCount = offerRedeemsCount;
    }

    @Override
    public String toString() {
        return "Offer{" +
                "offerId='" + offerId + '\'' +
                ", vendorId='" + vendorId + '\'' +
                ", offerName='" + offerName + '\'' +
                ", offerDescription='" + offerDescription + '\'' +
                ", offerValidity='" + offerValidity + '\'' +
                ", offerPasscode='" + offerPasscode + '\'' +
                ", offerDateCreated='" + offerDateCreated + '\'' +
                ", offerRedeemsCount='" + offerRedeemsCount + '\'' +
                '}';
    }
}
