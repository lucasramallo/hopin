package github.lucasramallo.hopin.api.dtos.customer;

public record PaymentIntentDTO(
        String id,
        String paymentMethodId,
        String clientSecret,
        Long amount,
        String currency,
        Long created,
        String status
) {
}
