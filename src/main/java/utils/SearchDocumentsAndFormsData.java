package utils;

import java.util.Arrays;
import java.util.List;

/**
 * Holds test data and constants used for the 'Search Documents and Forms' section validations.
 */
public class SearchDocumentsAndFormsData {

    // ========= Expected Table Headers =========
    /**
     * Expected headers of the search results table in the "Documents and Forms" section.
     */
//    public static final List<String> expectedHeaders = Arrays.asList(
//            "שם המסמך",        // Document name
//            "ביטוח ראשי",       // Primary insurance
//            "ביטוח משני",       // Secondary insurance
//            "הורדה למחשב",     // Download to PC
//            "מילוי אונליין"     // Online fillable
//    );
    public static final List<String> expectedHeaders = Arrays.asList(
            "שם המסמך פוליסה לביטוח רכב פרטי ומסחרי עד 3.5 טון – מהדורה 07.2024",
            "ביטוח ראשי רכב",
            "ביטוח משני ביטוח מקיף",
            "הורדה למחשב",
            "" // שים לב: השדה האחרון ריק, כך שהמחרוזת כאן היא ריקה
    );


    // ========= Text Constants =========
    /**
     * General insurance keyword used in free text search.
     */
    public static final String insurance = "ביטוח";
}
