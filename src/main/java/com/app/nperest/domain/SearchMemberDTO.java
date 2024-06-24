package com.app.nperest.domain;

import io.swagger.annotations.ApiModel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.List;

@ApiModel(description = "회원 정보")
@Component
@Getter
@Setter
@ToString(callSuper = true)
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false)
public class SearchMemberDTO implements Serializable {
    private Long id;
    private String memberName;
    private String kakaoProfileUrl;
    private String memberPosition;
    private List<CareerDTO> careers;
}
