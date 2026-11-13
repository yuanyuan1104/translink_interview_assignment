package ca.translink.transit.model;

public class VehicleDescription {

    private String id;
    private String label;

    public VehicleDescription(String id, String label) {
        this.id = id;
        this.label = label;
    }

    public String getId() {
        return id;
    }

    public String getLabel() {
        return label;
    }
}
