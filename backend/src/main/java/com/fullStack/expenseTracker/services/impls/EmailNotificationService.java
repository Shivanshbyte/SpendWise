
package com.fullStack.expenseTracker.services.impls;

import com.fullStack.expenseTracker.models.User;
import com.fullStack.expenseTracker.services.NotificationService;
import jakarta.mail.MessagingException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientResponseException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.HtmlUtils;

import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Map;

@Component
@Slf4j
public class EmailNotificationService implements NotificationService {

    private final RestTemplate restTemplate = new RestTemplate();

    @Value("${brevo.api.url}")
    private String apiUrl;

    @Value("${brevo.api.key}")
    private String apiKey;

    @Value("${brevo.sender.email}")
    private String fromEmail;

    @Value("${brevo.sender.name:SpendWise}")
    private String senderName;

    @Override
    public void sendUserRegistrationVerificationEmail(User user)
            throws MessagingException, UnsupportedEncodingException {

        String username = HtmlUtils.htmlEscape(user.getUsername());
        String code = HtmlUtils.htmlEscape(user.getVerificationCode());

        String subject = "SpendWise - Verify your registration";

        String htmlContent = """
                <p>Dear %s,</p>
                <p>Thank you for joining SpendWise!</p>
                <p>Enter this verification code to complete your registration:</p>
                <h2>%s</h2>
                <p>This code expires within 15 minutes.</p>
                <p>Thank you,<br>SpendWise Team</p>
                """.formatted(username, code);

        sendEmail(user.getEmail(), subject, htmlContent);
    }

    @Override
    public void sendForgotPasswordVerificationEmail(User user)
            throws MessagingException, UnsupportedEncodingException {

        String username = HtmlUtils.htmlEscape(user.getUsername());
        String code = HtmlUtils.htmlEscape(user.getVerificationCode());

        String subject = "SpendWise - Password reset verification";

        String htmlContent = """
                <p>Dear %s,</p>
                <p>To reset your password, enter this verification code:</p>
                <h2>%s</h2>
                <p>This code expires within 15 minutes.</p>
                <p>Thank you,<br>SpendWise Team</p>
                """.formatted(username, code);

        sendEmail(user.getEmail(), subject, htmlContent);
    }

    private void sendEmail(
            String recipient,
            String subject,
            String htmlContent) {

        HttpHeaders headers = new HttpHeaders();
        headers.set("api-key", apiKey);
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(List.of(MediaType.APPLICATION_JSON));

        Map<String, Object> payload = Map.of(
                "sender", Map.of(
                        "name", senderName,
                        "email", fromEmail
                ),
                "to", List.of(
                        Map.of("email", recipient)
                ),
                "subject", subject,
                "htmlContent", htmlContent
        );

        HttpEntity<Map<String, Object>> request =
                new HttpEntity<>(payload, headers);

        try {
            ResponseEntity<String> response = restTemplate.postForEntity(
                    apiUrl,
                    request,
                    String.class
            );

            log.info("Brevo accepted email for recipient {}. Status: {}",
                    recipient, response.getStatusCode());

        } catch (RestClientResponseException e) {
            log.error(
                    "Brevo email request failed. Status: {}, response: {}",
                    e.getStatusCode(),
                    e.getResponseBodyAsString()
            );
            throw new IllegalStateException(
                    "Unable to send email through Brevo."
            );
        } catch (Exception e) {
            log.error("Failed to send email through Brevo", e);
            throw new IllegalStateException(
                    "Unable to send email through Brevo."
            );
        }
    }
}
