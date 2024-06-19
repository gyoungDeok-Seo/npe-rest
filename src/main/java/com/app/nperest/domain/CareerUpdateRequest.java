package com.app.nperest.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString(callSuper = true)
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false)
public class CareerUpdateRequest {
    private CareerDTO createCareer;
    private ModifyCareerInListInfo listInfo;
}
