package com.upgrad.mba.entities;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;

@Entity
@Cacheable
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Language {
    @Id
    @GeneratedValue
    private int languageId;

    @Column(length = 20, nullable = false, unique = true)
    private String languageName;
    public Language(){}
    public Language(int languageId,String languageName)
    {
        this.languageId=languageId;
        this.languageName=languageName;
    }
    public Language(String languageName)
    {
        this.languageName=languageName;
    }
    @OneToMany(mappedBy = "language",fetch = FetchType.EAGER)
    private Set<Theatre> theatres;
    public int getLanguageId() {
        return languageId;
    }

    public void setLanguageId(int languageId) {
        this.languageId = languageId;
    }

    public String getLanguageName() {
        return languageName;
    }

    public void setLanguageName(String languageName) {
        this.languageName = languageName;
    }
    public Set<Theatre> getTheatres()
    {
        return theatres;
    }
    public void setTheatres(Set<Theatre> theatres)
    {
        this.theatres=theatres;
    }
    @Override
    public String toString() {
        return "Language{" +
                "languageId=" + languageId +
                ", languageName='" + languageName + '\'' +
                ", theatres="+theatres+
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Language language = (Language) o;
        return languageId == language.languageId && Objects.equals(languageName, language.languageName) && Objects.equals(theatres, language.theatres);
    }

    @Override
    public int hashCode() {
        return Objects.hash(languageId, languageName, theatres);
    }
}
