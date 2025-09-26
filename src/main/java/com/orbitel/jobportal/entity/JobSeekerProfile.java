package com.orbitel.jobportal.entity;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "job_seeker_profiles")
public class JobSeekerProfile {

    @Id
    private Long id;  

    @OneToOne
    @MapsId
    @JoinColumn(name = "user_id")
    private User user;

    private String headline;        
    private String summary;         

    @ElementCollection
    @CollectionTable(
      name = "seeker_education",
      joinColumns = @JoinColumn(name = "seeker_id"))
    @Column(name = "education")
    private List<String> education; 

    @ElementCollection
    @CollectionTable(
      name = "seeker_experience",
      joinColumns = @JoinColumn(name = "seeker_id"))
    @Column(name = "experience")
    private List<String> experience; 

    @ElementCollection
    @CollectionTable(
      name = "seeker_skills",
      joinColumns = @JoinColumn(name = "seeker_id"))
    @Column(name = "skill")
    private List<String> skills;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getHeadline() {
        return headline;
    }

    public void setHeadline(String headline) {
        this.headline = headline;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public List<String> getEducation() {
        return education;
    }

    public void setEducation(List<String> education) {
        this.education = education;
    }

    public List<String> getExperience() {
        return experience;
    }

    public void setExperience(List<String> experience) {
        this.experience = experience;
    }

    public List<String> getSkills() {
        return skills;
    }

    public void setSkills(List<String> skills) {
        this.skills = skills;
    }
}
