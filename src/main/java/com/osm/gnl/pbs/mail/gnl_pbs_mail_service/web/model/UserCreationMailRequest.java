package com.osm.gnl.pbs.mail.gnl_pbs_mail_service.web.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserCreationMailRequest {
    private String email;
    private String firstName;
    private String lastName;
    private String userName;
    private String password;
}
