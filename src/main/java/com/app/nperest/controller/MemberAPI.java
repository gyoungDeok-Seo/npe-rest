package com.app.nperest.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/members/api")
@RequiredArgsConstructor
// http://localhost:10000/swagger-ui/index.html
@Tag(name = "Member", description = "Member API")
public class MemberAPI {
}
