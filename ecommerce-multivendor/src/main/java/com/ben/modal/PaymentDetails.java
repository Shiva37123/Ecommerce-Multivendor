package com.ben.modal;


import com.ben.domain.PaymentStatus;
import lombok.Data;

@Data
public class PaymentDetails {

    private String paymentId;
    private String razorPaymentLinkId;
    private String razorPaymentLinkReferenceId;
    private String razorPaymentLinkStatus;
    private String razorPaymentLinkIdZmSP;
    private PaymentStatus status;


}
