package com.git.oauth;

import com.git.domain.cart.Cart;
import com.git.domain.member.Member;
import com.git.domain.member.Role;
import com.git.domain.member.SocialType;
import com.git.oauth.userinfo.KakaoOAuth2UserInfo;
import com.git.oauth.userinfo.Oauth2UserInfo;
import lombok.Builder;
import lombok.Getter;

import java.util.Map;

import static com.git.domain.member.Role.GUEST;
import static com.git.domain.member.SocialType.KAKAO;

@Getter
public class OAuthAttributes {

    private String nameAttributeKey;
    private Oauth2UserInfo oauth2UserInfo;

    @Builder
    public OAuthAttributes(String nameAttributeKey, Oauth2UserInfo oauth2UserInfo) {
        this.nameAttributeKey = nameAttributeKey;
        this.oauth2UserInfo = oauth2UserInfo;
    }

    protected OAuthAttributes() {
    }

    public static OAuthAttributes of(SocialType socialType, String usernameAttributeName, Map<String, Object> attributes) {
        if(socialType.equals(KAKAO)) {
            return ofKakao(usernameAttributeName, attributes);
        }
        return null;
    }

    public static OAuthAttributes ofKakao(String usernameAttributeName, Map<String, Object> attributes) {
        return OAuthAttributes.builder()
                .nameAttributeKey(usernameAttributeName)
                .oauth2UserInfo(new KakaoOAuth2UserInfo(attributes))
                .build();
    }

    public Member toEntity(SocialType socialType, Oauth2UserInfo oauth2UserInfo) {
        return Member.builder()
                .socialType(socialType)
                .socialId(oauth2UserInfo.getId())
                .username(oauth2UserInfo.getNickName())
                .role(GUEST)
                .email(oauth2UserInfo.getEmail())
                .cart(Cart.createCart())
                .build();
    }
}
























