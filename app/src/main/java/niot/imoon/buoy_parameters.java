package niot.imoon;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class buoy_parameters {
    private String parameter,value;

    public buoy_parameters(String parameter, String value) {
        this.parameter = parameter;
        this.value = value;
    }

    public String getParameter() {
        return parameter;
    }

    public void setParameter(String parameter) {
        this.parameter = parameter;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}