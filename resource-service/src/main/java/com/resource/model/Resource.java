package com.resource.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.resource.enums.Type;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Resource {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private int quantity;

    @Enumerated(EnumType.STRING)
    @Column(name = "type", nullable = false, length = 255)
    private Type type;

    private String provider;

    @Column(name = "task_id" , nullable = false)
    private Long taskId;

}
