package com.osm.gnl.pbs.mail.gnl_pbs_mail_service.web.service;


import com.osm.gnl.pbs.mail.gnl_pbs_mail_service.domain.Mailer;
import com.osm.gnl.pbs.mail.gnl_pbs_mail_service.web.model.MailRequest;
import com.osm.gnl.pbs.mail.gnl_pbs_mail_service.web.model.MailResponse;
import com.osm.gnl.pbs.mail.gnl_pbs_mail_service.web.model.UserCreationMailRequest;

public interface MailerService {

    MailResponse sendMailWithAttachments(MailRequest mailRequest);

    MailResponse sendMailForPasswordReset(MailRequest mailRequest);

    MailResponse sendUserCreationMail(UserCreationMailRequest userCreationMailRequest);

    boolean sendMailWithAttachments(Mailer mailer, byte[] attachmentData, String attachmentName);

    boolean sendMailForPasswordReset(Mailer mailer);
}
