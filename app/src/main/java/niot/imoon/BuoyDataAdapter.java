package niot.imoon;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class BuoyDataAdapter {

    @SerializedName("Row")
    @Expose
    private List<Row> row = null;

    public List<Row> getRow() {
        return row;
    }

    public void setRow(List<Row> row) {
        this.row = row;
    }

}