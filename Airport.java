public class Airport {
    private String code;
    private String city;
    private boolean isPaid;

    public Airport(String code, String city, boolean isPaid) {
        this.code = code;
        this.city = city;
        this.isPaid = isPaid;
    }

    public String getCode() {
        return this.code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getCity() {
        return this.city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public boolean getIsPaid() {
        return this.isPaid;
    }

    public void setIsPaid(boolean isPaid) {
        this.isPaid = isPaid;
    }
}
