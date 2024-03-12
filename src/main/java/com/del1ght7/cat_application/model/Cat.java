package com.del1ght7.cat_application.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Cat {
    private String breed;
    private String country;
    private String origin;
    private String coat;
    private String pattern;
}
