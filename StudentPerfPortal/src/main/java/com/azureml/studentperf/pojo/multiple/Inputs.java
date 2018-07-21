
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
    "input1",
    "input2"
})
public class Inputs {

    @JsonProperty("input1")
    private Input1 input1;
    @JsonProperty("input2")
    private Input2 input2;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("input1")
    public Input1 getInput1() {
        return input1;
    }

    @JsonProperty("input1")
    public void setInput1(Input1 input1) {
        this.input1 = input1;
    }

    public Inputs withInput1(Input1 input1) {
        this.input1 = input1;
        return this;
    }

    @JsonProperty("input2")
    public Input2 getInput2() {
        return input2;
    }

    @JsonProperty("input2")
    public void setInput2(Input2 input2) {
        this.input2 = input2;
    }

    public Inputs withInput2(Input2 input2) {
        this.input2 = input2;
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

    public Inputs withAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
        return this;
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(input1).append(input2).append(additionalProperties).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof Inputs) == false) {
            return false;
        }
        Inputs rhs = ((Inputs) other);
        return new EqualsBuilder().append(input1, rhs.input1).append(input2, rhs.input2).append(additionalProperties, rhs.additionalProperties).isEquals();
    }

}