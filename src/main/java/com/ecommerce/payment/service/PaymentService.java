package com.ecommerce.payment.service;

import com.ecommerce.order.model.Order;
import com.ecommerce.order.service.OrderService;
import com.stripe.exception.StripeException;
import com.stripe.model.PaymentIntent;
import com.stripe.param.PaymentIntentCreateParams;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PaymentService {

    private final OrderService orderService;

    public String createPaymentIntent(UUID orderId) throws StripeException {
        Order order = orderService.getOrderById(orderId);

        PaymentIntentCreateParams params = PaymentIntentCreateParams.builder()
                .setAmount((long) (order.getTotalAmount() * 100))
                .setCurrency("inr")
                .putMetadata("order_id", order.getId().toString())
                .build();

        PaymentIntent intent = PaymentIntent.create(params);
        return intent.getClientSecret();
    }
}
