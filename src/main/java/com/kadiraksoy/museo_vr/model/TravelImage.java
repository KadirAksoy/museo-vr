package com.kadiraksoy.museo_vr.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "travel_images")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TravelImage extends BaseEntity{

    private String name;
    private String type;
    private String content;

    @Lob
    private byte[] imageData;

    @ManyToOne
    @JoinColumn(name = "travel_id")
    private Travel travel;
}
