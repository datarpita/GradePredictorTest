
package com.azureml.studentperf.pojo.single;

import java.util.HashMap;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "age",
    "famrel",
    "absences",
    "grade1",
    "grade2"
})
public class Cand {

    @JsonProperty("age")
    private String age;
    @JsonProperty("famrel")
    private String famrel;
    @JsonProperty("absences")
    private String absences;
    @JsonProperty("grade1")
    private String grade1;
    @JsonProperty("grade2")
    private String grade2;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("age")
    public String getAge() {
        return age;
    }

    @JsonProperty("age")
    public void setAge(String age) {
        this.age = age;
    }

    public Cand withAge(String age) {
        this.age = age;
        return this;
    }

    @JsonProperty("famrel")
    public String getFamrel() {
        return famrel;
    }

    @JsonProperty("famrel")
    public void setFamrel(String famrel) {
        this.famrel = famrel;
    }

    public Cand withFamrel(String famrel) {
        this.famrel = famrel;
        return this;
    }

    @JsonProperty("absences")
    public String getAbsences() {
        return absences;
    }

    @JsonProperty("absences")
    public void setAbsences(String absences) {
        this.absences = absences;
    }

    public Cand withAbsences(String absences) {
        this.absences = absences;
        return this;
    }

    @JsonProperty("grade1")
    public String getGrade1() {
        return grade1;
    }

    @JsonProperty("grade1")
    public void setGrade1(String grade1) {
        this.grade1 = grade1;
    }

    public Cand withGrade1(String grade1) {
        this.grade1 = grade1;
        return this;
    }

    @JsonProperty("grade2")
    public String getGrade2() {
        return grade2;
    }

    @JsonProperty("grade2")
    public void setGrade2(String grade2) {
        this.grade2 = grade2;
    }

    public Cand withGrade2(String grade2) {
        this.grade2 = grade2;
        return this;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

    public Cand withAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
        return this;
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(age).append(famrel).append(absences).append(grade1).append(grade2).append(additionalProperties).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof Cand) == false) {
            return false;
        }
        Cand rhs = ((Cand) other);
        return new EqualsBuilder().append(age, rhs.age).append(famrel, rhs.famrel).append(absences, rhs.absences).append(grade1, rhs.grade1).append(grade2, rhs.grade2).append(additionalProperties, rhs.additionalProperties).isEquals();
    }

}
