
package com.azureml.studentperf.pojo.single;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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
    "ColumnNames",
    "ColumnTypes",
    "Values"
})
public class Value {

    @JsonProperty("ColumnNames")
    private List<String> columnNames = new ArrayList<String>();
    @JsonProperty("ColumnTypes")
    private List<String> columnTypes = new ArrayList<String>();
    @JsonProperty("Values")
    private List<List<String>> values = new ArrayList<List<String>>();
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("ColumnNames")
    public List<String> getColumnNames() {
        return columnNames;
    }

    @JsonProperty("ColumnNames")
    public void setColumnNames(List<String> columnNames) {
        this.columnNames = columnNames;
    }

    public Value withColumnNames(List<String> columnNames) {
        this.columnNames = columnNames;
        return this;
    }

    @JsonProperty("ColumnTypes")
    public List<String> getColumnTypes() {
        return columnTypes;
    }

    @JsonProperty("ColumnTypes")
    public void setColumnTypes(List<String> columnTypes) {
        this.columnTypes = columnTypes;
    }

    public Value withColumnTypes(List<String> columnTypes) {
        this.columnTypes = columnTypes;
        return this;
    }

    @JsonProperty("Values")
    public List<List<String>> getValues() {
        return values;
    }

    @JsonProperty("Values")
    public void setValues(List<List<String>> values) {
        this.values = values;
    }

    public Value withValues(List<List<String>> values) {
        this.values = values;
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

    public Value withAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
        return this;
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(columnNames).append(columnTypes).append(values).append(additionalProperties).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof Value) == false) {
            return false;
        }
        Value rhs = ((Value) other);
        return new EqualsBuilder().append(columnNames, rhs.columnNames).append(columnTypes, rhs.columnTypes).append(values, rhs.values).append(additionalProperties, rhs.additionalProperties).isEquals();
    }

}
