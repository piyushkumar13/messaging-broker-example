package org.example.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Piyush Kumar.
 * @since 02/06/21.
 */

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Student {

    private int id;
    private String name;
    private String subject;
}
