package com.gdsc.wero.global.auth.oauth.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class OauthUserDto {
    private String email;
    private String name;
    private String picture;

    @Builder
    public OauthUserDto(String email, String name, String picture) {
        this.email = email;
        this.name = name;
        this.picture = picture;
    }


}
