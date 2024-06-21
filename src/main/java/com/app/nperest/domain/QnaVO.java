package com.app.nperest.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.stereotype.Component;

import java.io.Serializable;

//@Component
//이 클래스를 빈(Bean)으로 관리
//클래스의 인스턴스를 자동으로 생성
//@ToString(callSuper = true)
//callSuper를 true로 하면 부모 클래스의 필드도 tostring에 포함함
//상속관계에서 사용
@Component
@Getter
@Setter
@ToString(callSuper = true)
public class QnaVO extends Period implements Serializable {
    private Long id;
    private String questionTitle;
    private String questionContent;
    private boolean status;
    private Long categoryId;
    private Long memberId;
}
