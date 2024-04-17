package com.example.momointerator.commons.data.dto.momo;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class UserInfoMomo {
    private String name;
    private String phoneNumber;
    private String email;
}
