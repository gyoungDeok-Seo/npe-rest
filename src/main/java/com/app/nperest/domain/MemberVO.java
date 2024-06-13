package com.app.nperest.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.stereotype.Component;

import java.io.Serializable;

@ApiModel(description = "회원 정보")
@Component
@Getter @Setter @ToString(callSuper = true)
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false)
public class MemberVO extends Period implements Serializable {
    @EqualsAndHashCode.Include
    private Long id;
    @ApiModelProperty(value="회원 카카오 이메일", example = "test@gmail.com", required = true)
    private String kakaoEmail;
    @ApiModelProperty(value = "회원 카카오 프로필 이미지", example = "test/set~", required = true)
    private String kakaoProfileUrl;
    @ApiModelProperty(value = "회원 이름", example = "홍길동", required = true)
    private String memberName;
    @ApiModelProperty(value = "회원 직책", example = "개발부 팀장", required = true)
    private String memberPosition;
    @ApiModelProperty(value = "회원 소개", example = "블로그하는 개발자입니다.", required = true)
    private String memberIntro;
    @ApiModelProperty(value = "회원 상태", example = "0: 탈퇴, 1: 활동중", required = true)
    private boolean status;
}
