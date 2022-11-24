package com.golab.swimteach.model;

import lombok.Getter;
import lombok.ToString;

import javax.persistence.*;
import java.util.Objects;

@Getter
@ToString
@Entity
public class SkillDetails {

    @Id
    private Long id;

    private String title;

    @Lob
    private String description;

    private String url;

    public SkillDetails() {

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SkillDetails that = (SkillDetails) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
