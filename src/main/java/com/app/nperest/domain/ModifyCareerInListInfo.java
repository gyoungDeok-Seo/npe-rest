package com.app.nperest.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString(callSuper = true)
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false)
public class ModifyCareerInListInfo {
    private List<CareerIndustryDTO> addIndustryList;
    private List<CareerIndustryDTO> removeIndustryList;
    private List<CareerSkillDTO> addSkillList;
    private List<CareerSkillDTO> removeSkillList;
}
