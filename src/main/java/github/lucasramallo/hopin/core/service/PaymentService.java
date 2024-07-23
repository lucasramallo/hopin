package github.lucasramallo.hopin.core.service;

import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.PaymentIntent;
import com.stripe.param.PaymentIntentCreateParams;
import github.lucasramallo.hopin.api.dtos.customer.PaymentIntentDTO;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class PaymentService {
    @Value("${stripe.api.key}")
    String stripeApiKey;

    @PostConstruct
    public void init() {
        Stripe.apiKey = this.stripeApiKey;
    }

    public PaymentIntentDTO execute() {
        // DOC: https://docs.stripe.com/api/payment_intents/create?lang=java&shell=true&api=true&resource=payment_intents&action=create
        PaymentIntentCreateParams params =
                PaymentIntentCreateParams.builder()
                        .setAmount(2000L)
                        .setCurrency("usd")
                        .setPaymentMethod("pm_card_visa")
                        .setAutomaticPaymentMethods(
                                PaymentIntentCreateParams.AutomaticPaymentMethods.builder()
                                        .setEnabled(true)
                                        .build()
                        )
                        .build();

        try {
            PaymentIntent paymentIntent = PaymentIntent.create(params);
            return new PaymentIntentDTO(
                    paymentIntent.getId(),
                    paymentIntent.getPaymentMethod(),
                    paymentIntent.getClientSecret(),
                    paymentIntent.getAmount(),
                    paymentIntent.getCurrency(),
                    paymentIntent.getCreated(),
                    paymentIntent.getStatus()
            );

        } catch (StripeException e) {
            throw new RuntimeException(e);
        }
    }
}
