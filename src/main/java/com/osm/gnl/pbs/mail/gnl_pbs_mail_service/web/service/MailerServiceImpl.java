package com.osm.gnl.pbs.mail.gnl_pbs_mail_service.web.service;

import com.osm.gnl.pbs.mail.gnl_pbs_mail_service.domain.Mailer;
import com.osm.gnl.pbs.mail.gnl_pbs_mail_service.repository.MailerRepository;
import com.osm.gnl.pbs.mail.gnl_pbs_mail_service.web.model.MailRequest;
import com.osm.gnl.pbs.mail.gnl_pbs_mail_service.web.model.MailResponse;
import com.osm.gnl.pbs.mail.gnl_pbs_mail_service.web.model.UserCreationMailRequest;
import jakarta.mail.internet.MimeMessage;
import jakarta.mail.util.ByteArrayDataSource;
import lombok.RequiredArgsConstructor;
import org.springframework.lang.Nullable;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.concurrent.TimeUnit;

@Service
@RequiredArgsConstructor
public class MailerServiceImpl implements MailerService {

    private final JavaMailSender mailSender;
    private final MailerRepository mailerRepository;

    private static final String FROM_EMAIL = "ogsg_ippms_notif@gnlsystems.com";

    @Override
    public MailResponse sendMailWithAttachments(MailRequest mailRequest) {
        Mailer mailer = mapToMailer(mailRequest);

        try {
            boolean success = sendMailWithAttachments(mailer, mailRequest.getAttachmentData(), mailRequest.getAttachmentName());
            mailer.setStatusInd(success ? 0 : 1);
            if (!success) {
                mailer.setErrMsg("Failed to send email");
            }
        } catch (Exception ex) {
            mailer.setStatusInd(1);
            mailer.setErrMsg(ex.getMessage());
        } finally {
            mailer = mailerRepository.save(mailer);
        }

        return MailResponse.builder()
                .mailId(mailer.getId())
                .success(mailer.getStatusInd() == 0)
                .errorMessage(mailer.getErrMsg())
                .build();
    }

    @Override
    public MailResponse sendMailForPasswordReset(MailRequest mailRequest) {
        Mailer mailer = mapToMailer(mailRequest);

        try {
            boolean success = sendMailForPasswordReset(mailer);
            mailer.setStatusInd(success ? 0 : 1);
            if (!success) {
                mailer.setErrMsg("Failed to send password reset email");
            }
        } catch (Exception ex) {
            mailer.setStatusInd(1);
            mailer.setErrMsg(ex.getMessage());
        } finally {
            mailer = mailerRepository.save(mailer);
        }

        return MailResponse.builder()
                .mailId(mailer.getId())
                .success(mailer.getStatusInd() == 0)
                .errorMessage(mailer.getErrMsg())
                .build();
    }

    @Override
    public MailResponse sendUserCreationMail(UserCreationMailRequest userCreationMailRequest) {
        Mailer mailer = new Mailer();
        mailer.setRecipient(userCreationMailRequest.getEmail());
        mailer.setName(userCreationMailRequest.getFirstName() + " " + userCreationMailRequest.getLastName());
        mailer.setSubject("OGS-PBS Login Details");

        Timestamp tmpTimeStamp = Timestamp.from(Instant.now());
        tmpTimeStamp.setTime(tmpTimeStamp.getTime() + TimeUnit.MINUTES.toMillis(7));

        mailer.setMessage("Dear " + userCreationMailRequest.getLastName() + " " + userCreationMailRequest.getFirstName() + ", </br></br>" +
                " Please your default login details below </br></br>" +
                " Username : " + userCreationMailRequest.getUserName() + "</br> Password : " + userCreationMailRequest.getPassword() + "</br></br>" +
                "<b><font color=green> NOTE: Password Expires At - " + DateTimeFormatter.ofPattern("dd/MM/yyyy h:mm a").format(
                tmpTimeStamp.toLocalDateTime()) + "</font></b>");

        mailer.setExpirationDateTime(tmpTimeStamp);
        mailer.setSentDate(LocalDate.now());
        mailer.setAutoGenPassword(userCreationMailRequest.getPassword());

        try {
            boolean success = sendMailWithAttachments(mailer, null, null);
            mailer.setStatusInd(success ? 0 : 1);
            if (!success) {
                mailer.setErrMsg("Failed to send user creation email");
            }
        } catch (Exception ex) {
            mailer.setStatusInd(1);
            mailer.setErrMsg(ex.getMessage());
        } finally {
            mailer = mailerRepository.save(mailer);
        }

        return MailResponse.builder()
                .mailId(mailer.getId())
                .success(mailer.getStatusInd() == 0)
                .errorMessage(mailer.getErrMsg())
                .build();
    }

    @Override
    public boolean sendMailWithAttachments(Mailer mailer, @Nullable byte[] attachmentData, @Nullable String attachmentName) {
        try {
            MimeMessage msg = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(msg, true);

            msg.setSentDate(Calendar.getInstance().getTime());
            helper.setTo(mailer.getRecipient());
            helper.setSubject(mailer.getSubject());
            helper.setFrom(FROM_EMAIL, mailer.getName());
            helper.setText(mailer.getMessage(), true);

            if (attachmentData != null && attachmentName != null) {
                ByteArrayDataSource dataSource = new ByteArrayDataSource(attachmentData, "application/octet-stream");
                helper.addAttachment(attachmentName, dataSource);
            }

            mailSender.send(msg);
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public boolean sendMailForPasswordReset(Mailer mailer) {
        SimpleMailMessage msg = new SimpleMailMessage();
        msg.setTo(mailer.getRecipient());
        msg.setFrom(FROM_EMAIL);
        msg.setSubject(mailer.getSubject());
        msg.setText(mailer.getMessage());
        msg.setSentDate(Calendar.getInstance().getTime());

        try {
            mailSender.send(msg);
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
        return true;
    }

    private Mailer mapToMailer(MailRequest mailRequest) {
        Mailer mailer = new Mailer();
        mailer.setCopier(mailRequest.getCopier());
        mailer.setRecipient(mailRequest.getRecipient());
        mailer.setName(mailRequest.getName());
        mailer.setSubject(mailRequest.getSubject());
        mailer.setMessage(mailRequest.getMessage());
        mailer.setSentDate(mailRequest.getSentDate() != null ? mailRequest.getSentDate() : LocalDate.now());
        mailer.setAutoGenPassword(mailRequest.getAutoGenPassword());
        mailer.setExpirationDateTime(mailRequest.getExpirationDateTime());
        return mailer;
    }
}
