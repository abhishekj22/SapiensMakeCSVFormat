import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.TreeMap;

public class ReadAndWriteToCSV {

    public static void main(String[] args) {
        System.out.println("Job Started at " + java.time.LocalTime.now());
        readDataLineByLine();
        System.out.println("Job Finished at " + java.time.LocalTime.now());

    }

    private static void readDataLineByLine() {
        String csvFile = "src/Postcode 2 ka 2.csv";
        String outputCsvFile = "src/output4.csv";
        String line = "";
        String cvsSplitBy = ",";


        long id = 1000000;
        int product_id = 1000000;
        int update_user = 1;
        int update_version = 0;
        int reserved_value_flag = 0;
        int is_default = 0;
        int sort_order = 0;
        int riskTypeID;
        String riskValue = null;
        String update_date = "\"2019-02-18 00:56:01\"";


        TreeMap<String, Long> acronRAG = acronRAGSetUp(new TreeMap<String, Long>(String.CASE_INSENSITIVE_ORDER));
        TreeMap<String, Long> mapflow = mapflowSetUp(new TreeMap<String, Long>(String.CASE_INSENSITIVE_ORDER));
        TreeMap<String, Long> segment = segmentSetUp(new TreeMap<String, Long>(String.CASE_INSENSITIVE_ORDER));



        try (BufferedReader inputFileBR = new BufferedReader(new FileReader(csvFile))) {
            try (BufferedWriter outputFileBW = new BufferedWriter(new FileWriter(outputCsvFile))) {

                outputFileBW.write("ID,PRODUCT_ID,RISK_TYPE_ID,KEY_VALUE,RISK_VALUE,UPDATE_USER,UPDATE_DATE,UPDATE_VERSION,RESERVED_VALUE_FLAG,IS_DEFAULT,EXTERNAL_CODE,DISCONTINUE_DATE,DEVELOPER_DESC,SORT_ORDER");
                outputFileBW.newLine();

                while ((line = inputFileBR.readLine()) != null) {
                    String[] columnNo = line.split(cvsSplitBy);
                    int i = 1;

                    while (i < 17) {
                        riskValue = columnNo[i];
                        switch (i) {
                            case 1:
                                // MapflowCallRequiredSubs
                                riskTypeID = 1000000;
                                riskValue = mapflow.get(riskValue).toString();
                                break;
                            case 2:
                                // Buildings(1-10)
                                riskTypeID = 1000001;
                                break;
                            case 3:
                                //Contents(1-50)
                                riskTypeID = 1000002;
                                break;
                            case 4:
                                // PCMean
                                riskTypeID = 1000003;
                                break;
                            case 5:
                                // AcornTypeCode
                                riskTypeID = 1000005;
                                break;
                            case 6:
                                // AcornRAG
                                riskTypeID = 1000006;
                                riskValue = acronRAG.get(riskValue).toString();
                                break;
                            case 7:
                                // decile_res_brick
                                riskTypeID = 1000008;
                                break;
                            case 8:
                                // //Decile_domestic_burglary
                                riskTypeID = 1000007;
                                break;
                            case 9:
                                // Flood-M
                                riskTypeID = 1000009;
                                riskValue = mapflow.get(riskValue).toString();
                                break;
                            case 10:
                                // Flood-H
                                riskTypeID = 1000010;
                                break;
                            case 11:
                                // BrickRatio
                                riskTypeID = 1000011;
                                break;
                            case 12:
                                // Segment
                                riskTypeID = 3000000;
                                if (segment.get(riskValue) == null)
                                    riskValue = null;
                                else
                                    riskValue = segment.get(riskValue).toString();
                                break;
                            case 13:
                                // PC Sector
                                riskTypeID = 0;
                                break;
                            case 14:
                                // Motor_Property_Area
                                riskTypeID = 1000012;
                                break;
                            case 15:
                                // Motor_Liability_Area
                                riskTypeID = 1000013;
                                break;
                            case 16:
                                // SC_Property_Content_Area
                                riskTypeID = 1000021;
                                break;
                            default:
                                riskTypeID = 0;
                                break;
                        }

                        if (riskValue != null) {
                            outputFileBW.write(id++ + "," + product_id + "," + riskTypeID + "," + columnNo[0] + "," + riskValue + "," + update_user + "," + update_date + "," + update_version + "," + reserved_value_flag + "," + is_default + "," + "" + "," + "" + "," + "" + "," + sort_order);
                            //id        //product_id    //riskType              //keyValue           //riskValue          //update_user    //update_date       //update_version        //valueFLag                    //is_default //externalCode  //disconDate //devDesc //sortOder
                            outputFileBW.newLine();
                        }
                        i++;
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static TreeMap<String, Long> acronRAGSetUp(TreeMap<String, Long> acronRAG) {
        acronRAG.put("Black", 1L);
        acronRAG.put("Green", 2L);
        acronRAG.put("White", 3L);
        acronRAG.put("Grey", 4L);
        acronRAG.put("Yellow", 5L);
        acronRAG.put("Beige", 6L);
        acronRAG.put("Red", 7L);
        acronRAG.put("Brown", 8L);
        acronRAG.put("Blue", 9L);
        acronRAG.put("Orange", 10L);
        acronRAG.put("Bordeaux", 11L);
        acronRAG.put("Other", 12L);
        acronRAG.put("Salmon1", 20015L);
        acronRAG.put("Default color", 20017L);
        acronRAG.put("Font Color", 20018L);
        acronRAG.put("AMBER", 1000000L);
        acronRAG.put("UNK", 1000001L); // todo: replaced Unknown -> UNK
        acronRAG.put("Turkiz", 1001904L);
        acronRAG.put("White1", 1001905L);
        return acronRAG;
    }

    private static TreeMap<String, Long> mapflowSetUp(TreeMap<String, Long> mapflow) {
        mapflow.put("Clean", 1000004L);
        mapflow.put("Decline", 1000009L);
        mapflow.put("Low", 1000003L);
        mapflow.put("Moderate", 1000002L);
        mapflow.put("Refer", 1000008L);
        mapflow.put("Scotland", 1000007L);
        mapflow.put("Significant", 1000001L);
        mapflow.put("Unknown", 1000005L); //TODO: to verify which unknown to take
        mapflow.put("Unknown", 1000006L);
        return mapflow;
    }

    private static TreeMap<String, Long> segmentSetUp(TreeMap<String, Long> segment) {
        segment.put("Decline", 1000001L);
        segment.put("Bronze", 1000002L);
        segment.put("Silver", 1000003L);
        segment.put("Gold", 1000004L);
        segment.put("Platinum", 1000005L);
        return segment;
    }
}
