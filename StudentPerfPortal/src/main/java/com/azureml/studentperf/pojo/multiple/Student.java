
package com.azureml.studentperf.pojo.multiple;

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
    "absences",
    "famrel",
    "goout",
    "traveltime",
    "freetime",
    "dalc",
    "gradem1",
    "gradem2",
    "gradep1",
    "gradep2"
})
public class Student {

    @JsonProperty("age")
    private String age;
    @JsonProperty("absences")
    private String absences;
    @JsonProperty("famrel")
    private String famrel;
    @JsonProperty("goout")
    private String goout;
    @JsonProperty("traveltime")
    private String traveltime;
    @JsonProperty("freetime")
    private String freetime;
    @JsonProperty("dalc")
    private String dalc;
    @JsonProperty("gradem1")
    private String gradem1;
    @JsonProperty("gradem2")
    private String gradem2;
    @JsonProperty("gradep1")
    private String gradep1;
    @JsonProperty("gradep2")
    private String gradep2;
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

    public Student withAge(String age) {
        this.age = age;
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

    public Student withAbsences(String absences) {
        this.absences = absences;
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

    public Student withFamrel(String famrel) {
        this.famrel = famrel;
        return this;
    }

    @JsonProperty("goout")
    public String getGoout() {
        return goout;
    }

    @JsonProperty("goout")
    public void setGoout(String goout) {
        this.goout = goout;
    }

    public Student withGoout(String goout) {
        this.goout = goout;
        return this;
    }

    @JsonProperty("traveltime")
    public String getTraveltime() {
        return traveltime;
    }

    @JsonProperty("traveltime")
    public void setTraveltime(String traveltime) {
        this.traveltime = traveltime;
    }

    public Student withTraveltime(String traveltime) {
        this.traveltime = traveltime;
        return this;
    }

    @JsonProperty("freetime")
    public String getFreetime() {
        return freetime;
    }

    @JsonProperty("freetime")
    public void setFreetime(String freetime) {
        this.freetime = freetime;
    }

    public Student withFreetime(String freetime) {
        this.freetime = freetime;
        return this;
    }

    @JsonProperty("dalc")
    public String getDalc() {
        return dalc;
    }

    @JsonProperty("dalc")
    public void setDalc(String dalc) {
        this.dalc = dalc;
    }

    public Student withDalc(String dalc) {
        this.dalc = dalc;
        return this;
    }

    @JsonProperty("gradem1")
    public String getGradem1() {
        return gradem1;
    }

    @JsonProperty("gradem1")
    public void setGradem1(String gradem1) {
        this.gradem1 = gradem1;
    }

    public Student withGradem1(String gradem1) {
        this.gradem1 = gradem1;
        return this;
    }

    @JsonProperty("gradem2")
    public String getGradem2() {
        return gradem2;
    }

    @JsonProperty("gradem2")
    public void setGradem2(String gradem2) {
        this.gradem2 = gradem2;
    }

    public Student withGradem2(String gradem2) {
        this.gradem2 = gradem2;
        return this;
    }

    @JsonProperty("gradep1")
    public String getGradep1() {
        return gradep1;
    }

    @JsonProperty("gradep1")
    public void setGradep1(String gradep1) {
        this.gradep1 = gradep1;
    }

    public Student withGradep1(String gradep1) {
        this.gradep1 = gradep1;
        return this;
    }

    @JsonProperty("gradep2")
    public String getGradep2() {
        return gradep2;
    }

    @JsonProperty("gradep2")
    public void setGradep2(String gradep2) {
        this.gradep2 = gradep2;
    }

    public Student withGradep2(String gradep2) {
        this.gradep2 = gradep2;
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

    public Student withAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
        return this;
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(age).append(absences).append(famrel).append(goout).append(traveltime).append(freetime).append(dalc).append(gradem1).append(gradem2).append(gradep1).append(gradep2).append(additionalProperties).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof Student) == false) {
            return false;
        }
        Student rhs = ((Student) other);
        return new EqualsBuilder().append(age, rhs.age).append(absences, rhs.absences).append(famrel, rhs.famrel).append(goout, rhs.goout).append(traveltime, rhs.traveltime).append(freetime, rhs.freetime).append(dalc, rhs.dalc).append(gradem1, rhs.gradem1).append(gradem2, rhs.gradem2).append(gradep1, rhs.gradep1).append(gradep2, rhs.gradep2).append(additionalProperties, rhs.additionalProperties).isEquals();
    }

}
