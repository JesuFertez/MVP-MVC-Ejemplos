package com.artesanoskuad.mvpejemplo.presenter;

public interface DonationView {

    void updateTotalDonation(int totalAmount);
    void displayConfirmationMessage();
    void displayErrorMessage();
    void clearEditText();

}
