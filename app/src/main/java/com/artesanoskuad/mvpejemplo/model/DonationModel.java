package com.artesanoskuad.mvpejemplo.model;

public class DonationModel {
    private int totalAmount;

    public DonationModel() {

        this.totalAmount = 0;
    }

    public boolean saveDonation(Donation donation) {
        this.totalAmount += donation.getAmount();
        return true;
    }

    public int getTotalAmount() {
        return totalAmount;
    }
}
