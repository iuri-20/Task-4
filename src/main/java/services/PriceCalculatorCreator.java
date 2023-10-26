package services;

import model.PriceData;

public class PriceCalculatorCreator {

    public static final String TESTDATA_CALCULATOR_INSTANCES = "testdata.calculator.instances";
    public static final String TESTDATA_CALCULATOR_SERIES = "testdata.calculator.series";
    public static final String TESTDATA_CALCULATOR_MACHINE_TYPE = "testdata.calculator.machineType";
    public static final String TESTDATA_CALCULATOR_TYPE_OF_GPU = "testdata.calculator.typeOfGPU";
    public static final String TESTDATA_CALCULATOR_NUMBER_OF_GPUS = "testdata.calculator.numberOfGPUs";
    public static final String TESTDATA_CALCULATOR_LOCAL_SSD = "testdata.calculator.localSSD";
    public static final String TESTDATA_CALCULATOR_DATACENTER_LOCATION = "testdata.calculator.datacenterLocation";
    public static final String TESTDATA_CALCULATOR_COMMITTED_USAGE= "testdata.calculator.committedUsage";


    public static PriceData withCredentialsFromProperty(){
        return new PriceData(TestDataReader.getTestData(TESTDATA_CALCULATOR_INSTANCES),
                TestDataReader.getTestData(TESTDATA_CALCULATOR_SERIES),
                TestDataReader.getTestData(TESTDATA_CALCULATOR_MACHINE_TYPE),
                TestDataReader.getTestData(TESTDATA_CALCULATOR_TYPE_OF_GPU),
                TestDataReader.getTestData(TESTDATA_CALCULATOR_NUMBER_OF_GPUS),
                TestDataReader.getTestData(TESTDATA_CALCULATOR_LOCAL_SSD),
                TestDataReader.getTestData(TESTDATA_CALCULATOR_DATACENTER_LOCATION),
                TestDataReader.getTestData(TESTDATA_CALCULATOR_COMMITTED_USAGE));
    }

}
