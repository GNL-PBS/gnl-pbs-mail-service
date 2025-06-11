package com.osm.gnl.pbs.mail.gnl_pbs_mail_service.web.controller;

import com.osm.gnl.pbs.mail.gnl_pbs_mail_service.web.model.MailRequest;
import com.osm.gnl.pbs.mail.gnl_pbs_mail_service.web.model.MailResponse;
import com.osm.gnl.pbs.mail.gnl_pbs_mail_service.web.model.UserCreationMailRequest;
import com.osm.gnl.pbs.mail.gnl_pbs_mail_service.web.service.MailerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/pbs/v1/mail")
@RequiredArgsConstructor
@Tag(name = "Mail Service")
public class MailController {

    private final MailerService mailerService;

    @Operation(
            description = "Send email with optional attachments",
            summary = "Send email with attachments",
            responses = {
                    @ApiResponse(description = "OK", responseCode = "200"),
                    @ApiResponse(description = "Bad Request", responseCode = "400")
            })
    @PostMapping("/send-with-attachments")
    public ResponseEntity<MailResponse> sendMailWithAttachments(@RequestBody MailRequest mailRequest) {
        MailResponse response = mailerService.sendMailWithAttachments(mailRequest);
        return ResponseEntity.ok(response);
    }

    @Operation(
            description = "Send password reset email",
            summary = "Send password reset email",
            responses = {
                    @ApiResponse(description = "OK", responseCode = "200"),
                    @ApiResponse(description = "Bad Request", responseCode = "400")
            })
    @PostMapping("/send-password-reset")
    public ResponseEntity<MailResponse> sendPasswordResetMail(@RequestBody MailRequest mailRequest) {
        MailResponse response = mailerService.sendMailForPasswordReset(mailRequest);
        return ResponseEntity.ok(response);
    }

    @Operation(
            description = "Send user creation email with login details",
            summary = "Send user creation email",
            responses = {
                    @ApiResponse(description = "OK", responseCode = "200"),
                    @ApiResponse(description = "Bad Request", responseCode = "400")
            })
    @PostMapping("/send-user-creation")
    public ResponseEntity<MailResponse> sendUserCreationMail(@RequestBody UserCreationMailRequest userCreationMailRequest) {
        MailResponse response = mailerService.sendUserCreationMail(userCreationMailRequest);
        return ResponseEntity.ok(response);
    }
}
