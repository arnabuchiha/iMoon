
/**
 * Created by Risabh Mishra on 1/19/2018.
 */

package niot.imoon;

        import com.google.gson.annotations.Expose;
        import com.google.gson.annotations.SerializedName;

        import com.google.gson.annotations.Expose;
        import com.google.gson.annotations.SerializedName;

public class Row {

    @SerializedName("Parameter_____________Buoy_ID")
    @Expose
    private String parameterBuoyID;
    @SerializedName("GTS_ID")
    @Expose
    private String gTSID;
    @SerializedName("Date___Time")
    @Expose
    private String dateTime;
    @SerializedName("Batterydischarge__V_")
    @Expose
    private String batterydischargeV;
    @SerializedName("Batteryvoltage__V_")
    @Expose
    private String batteryvoltageV;
    @SerializedName("Latitude__deg_")
    @Expose
    private String latitudeDeg;
    @SerializedName("Longitude__deg_")
    @Expose
    private String longitudeDeg;
    @SerializedName("Humidity____")
    @Expose
    private String humidity;
    @SerializedName("Airpressure__hPa_")
    @Expose
    private String airpressureHPa;
    @SerializedName("Airtemperature__degC_")
    @Expose
    private String airtemperatureDegC;
    @SerializedName("Winddirection__deg_")
    @Expose
    private String winddirectionDeg;
    @SerializedName("Windspeed__m_s_")
    @Expose
    private String windspeedMS;
    @SerializedName("Windgust__m_s_")
    @Expose
    private String windgustMS;
    @SerializedName("Currentspeed___cm_s_")
    @Expose
    private String currentspeedCmS;
    @SerializedName("Currentdirection___deg_")
    @Expose
    private String currentdirectionDeg;
    @SerializedName("SST_1m__degC_")
    @Expose
    private String sST1mDegC;
    @SerializedName("Conductivity__mmho_cm_")
    @Expose
    private String conductivityMmhoCm;
    @SerializedName("Hm0__m_")
    @Expose
    private String hm0M;

    public String getParameterBuoyID() {
        return parameterBuoyID;
    }

    public void setParameterBuoyID(String parameterBuoyID) {
        this.parameterBuoyID = parameterBuoyID;
    }

    public String getGTSID() {
        return gTSID;
    }

    public void setGTSID(String gTSID) {
        this.gTSID = gTSID;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public String getBatterydischargeV() {
        return batterydischargeV;
    }

    public void setBatterydischargeV(String batterydischargeV) {
        this.batterydischargeV = batterydischargeV;
    }

    public String getBatteryvoltageV() {
        return batteryvoltageV;
    }

    public void setBatteryvoltageV(String batteryvoltageV) {
        this.batteryvoltageV = batteryvoltageV;
    }

    public String getLatitudeDeg() {
        return latitudeDeg;
    }

    public void setLatitudeDeg(String latitudeDeg) {
        this.latitudeDeg = latitudeDeg;
    }

    public String getLongitudeDeg() {
        return longitudeDeg;
    }

    public void setLongitudeDeg(String longitudeDeg) {
        this.longitudeDeg = longitudeDeg;
    }

    public String getHumidity() {
        return humidity;
    }

    public void setHumidity(String humidity) {
        this.humidity = humidity;
    }

    public String getAirpressureHPa() {
        return airpressureHPa;
    }

    public void setAirpressureHPa(String airpressureHPa) {
        this.airpressureHPa = airpressureHPa;
    }

    public String getAirtemperatureDegC() {
        return airtemperatureDegC;
    }

    public void setAirtemperatureDegC(String airtemperatureDegC) {
        this.airtemperatureDegC = airtemperatureDegC;
    }

    public String getWinddirectionDeg() {
        return winddirectionDeg;
    }

    public void setWinddirectionDeg(String winddirectionDeg) {
        this.winddirectionDeg = winddirectionDeg;
    }

    public String getWindspeedMS() {
        return windspeedMS;
    }

    public void setWindspeedMS(String windspeedMS) {
        this.windspeedMS = windspeedMS;
    }

    public String getWindgustMS() {
        return windgustMS;
    }

    public void setWindgustMS(String windgustMS) {
        this.windgustMS = windgustMS;
    }

    public String getCurrentspeedCmS() {
        return currentspeedCmS;
    }

    public void setCurrentspeedCmS(String currentspeedCmS) {
        this.currentspeedCmS = currentspeedCmS;
    }

    public String getCurrentdirectionDeg() {
        return currentdirectionDeg;
    }

    public void setCurrentdirectionDeg(String currentdirectionDeg) {
        this.currentdirectionDeg = currentdirectionDeg;
    }

    public String getSST1mDegC() {
        return sST1mDegC;
    }

    public void setSST1mDegC(String sST1mDegC) {
        this.sST1mDegC = sST1mDegC;
    }

    public String getConductivityMmhoCm() {
        return conductivityMmhoCm;
    }

    public void setConductivityMmhoCm(String conductivityMmhoCm) {
        this.conductivityMmhoCm = conductivityMmhoCm;
    }

    public String getHm0M() {
        return hm0M;
    }

    public void setHm0M(String hm0M) {
        this.hm0M = hm0M;
    }

}