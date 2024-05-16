package com.drugsystem.DrugSystem.user;

import lombok.*;

@ToString
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RoleDto {

    private Long id;
    private String currentValue;
    private String note;

}
