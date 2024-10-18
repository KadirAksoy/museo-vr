package com.kadiraksoy.museo_vr.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "travels")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Travel extends BaseEntity {

    private String title;
    private String description;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "travel", cascade = CascadeType.ALL)
    private List<TravelImage> images;

    @OneToMany(mappedBy = "travel", cascade = CascadeType.ALL)
    private List<Comment> comments;

    @OneToMany(mappedBy = "travel", cascade = CascadeType.ALL)
    private List<Like> likes;

}
