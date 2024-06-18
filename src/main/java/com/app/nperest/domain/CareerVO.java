package com.app.nperest.domain;

import io.swagger.annotations.ApiModel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.time.YearMonth;

@ApiModel(description = "회원 정보")
@Component @Getter @Setter
@ToString(callSuper = true)
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false)
public class CareerVO extends Period implements Serializable {
    private Long id;
    private String companyName;
    private String memberPosition;
    private YearMonth careerStart;
    private YearMonth careerEnd;
    private String description;
    private String careerUrl;
    private boolean status;
    private Long memberId;
}
