package com.app.nperest.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.stereotype.Component;

@ApiModel(description = "회원 정보")
@Component
@Getter @Setter @ToString(callSuper = true)
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false)
public class MemberVO extends Period {
    @EqualsAndHashCode.Include
    private Long id;
    @ApiModelProperty(value="회원 카카오 이메일", example = "test@gmail.com", required = true)
    private String kakaoEmail;
}
