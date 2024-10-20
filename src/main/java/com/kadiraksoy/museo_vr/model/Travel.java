package com.kadiraksoy.museo_vr.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "travels")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Travel extends BaseEntity {

    private String title;
    private String description;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "content_image_id", referencedColumnName = "id")
    private TravelImage contentImage;

    @OneToMany(mappedBy = "travel", cascade = CascadeType.ALL)
    private List<TravelImage> images;

    @OneToMany(mappedBy = "travel", cascade = CascadeType.ALL)
    private List<Comment> comments;

    @OneToMany(mappedBy = "travel", cascade = CascadeType.ALL)
    private List<Like> likes;

}
