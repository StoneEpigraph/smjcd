package cn.sm.algorithms.base.str;

import java.io.File;
import java.net.URLEncoder;
import java.time.LocalDate;

public class StringFormatTest {
    public static void main(String[] args) {
        LocalDate date = LocalDate.now();
        String str = String.format("%s%s%02d%s%02d%s", date.getYear(), File.separator, date.getMonthValue(), File.separator, date.getDayOfMonth(), File.separator);
        System.out.println(str);
        LocalDate currentArchiveDate = LocalDate.now();
        String P = File.separator;
        String zipHead = "FileHead";

        String entryFileName = String.format("%s%s%s%s%02d%s%02d%s%s%s", zipHead, P, currentArchiveDate.getYear(), P, currentArchiveDate.getMonthValue(), P, currentArchiveDate.getDayOfMonth(), P, "京B12345", ".txt");
        System.out.println(entryFileName);
    }
}
