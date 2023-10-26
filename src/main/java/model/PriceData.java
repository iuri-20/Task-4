package model;

import java.util.Objects;

public class PriceData {
    private String instances;
    private String series;
    private String machineType;
    private String typeOfGPU;
    private String numberOfGPUs;
    private String localSSD;
    private String datacenterLocation;
    private String committedUsage;

    public PriceData(String numberOfInstances, String series, String machineType, String typeOfGPU, String numberOfGPUs,
                     String localSSD, String datacenterLocation, String committedUsage) {
        this.instances = numberOfInstances;
        this.series = series;
        this.machineType = machineType;
        this.typeOfGPU= typeOfGPU;
        this.numberOfGPUs = numberOfGPUs;
        this.localSSD = localSSD;
        this.datacenterLocation = datacenterLocation;
        this.committedUsage = committedUsage;
    }

    public String getInstances() {
        return instances;
    }

    public void setInstances(String instances) {
        this.instances = instances;
    }

    public String getSeries() {
        return series;
    }

    public void setSeries(String series) {
        this.series = series;
    }

    public String getMachineType() {
        return machineType;
    }

    public void setMachineType(String machineType) {
        this.machineType = machineType;
    }

    public String getTypeOfGPU() {
        return typeOfGPU;
    }

    public void setTypeOfGPU(String typeOfGPU) {
        this.typeOfGPU = typeOfGPU;
    }

    public String getNumberOfGPUs() {
        return numberOfGPUs;
    }

    public void setNumberOfGPUs(String numberOfGPUs) {
        this.numberOfGPUs = numberOfGPUs;
    }

    public String getLocalSSD() {
        return localSSD;
    }

    public void setLocalSSD(String localSSD) {
        this.localSSD = localSSD;
    }

    public String getDatacenterLocation() {
        return datacenterLocation;
    }

    public void setDatacenterLocation(String datacenterLocation) {
        this.datacenterLocation = datacenterLocation;
    }

    public String getCommittedUsage() {
        return committedUsage;
    }

    public void setCommittedUsage(String committedUsage) {
        this.committedUsage = committedUsage;
    }



    @Override
    public String toString() {
        return "PriceCalculator{" +
                "instances='" + instances + '\'' +
                ", series='" + series + '\'' +
                ", machineType='" + machineType + '\'' +
                ", typeOfGPU='" + typeOfGPU + '\'' +
                ", numberOfGPUs='" + numberOfGPUs + '\'' +
                ", localSSD='" + localSSD + '\'' +
                ", datacenterLocation='" + datacenterLocation + '\'' +
                ", committedUsage='" + committedUsage + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PriceData)) return false;
        PriceData form = (PriceData) o;
        return Objects.equals(getInstances(), form.getInstances()) &&
                Objects.equals(getSeries(), form.getSeries()) &&
                Objects.equals(getMachineType(), form.getMachineType()) &&
                Objects.equals(getTypeOfGPU(), form.getTypeOfGPU()) &&
                Objects.equals(getNumberOfGPUs(), form.getNumberOfGPUs()) &&
                Objects.equals(getLocalSSD(), form.getLocalSSD()) &&
                Objects.equals(getDatacenterLocation(), form.getDatacenterLocation()) &&
                Objects.equals(getCommittedUsage(), form.getCommittedUsage());

    }

    @Override
    public int hashCode() {
        return Objects.hash(getInstances(), getSeries(), getMachineType(), getTypeOfGPU(),
                getNumberOfGPUs(), getLocalSSD(), getDatacenterLocation(), getCommittedUsage());
    }

}
