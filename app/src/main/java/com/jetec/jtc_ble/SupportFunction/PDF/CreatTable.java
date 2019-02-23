package com.jetec.jtc_ble.SupportFunction.PDF;

import android.content.Context;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.jetec.jtc_ble.SupportFunction.LogMessage;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CreatTable {

    private Context context;
    private String TAG = "CreatTable";
    private LogMessage logMessage = new LogMessage();
    private List<Integer> setColumn = new ArrayList<>();
    private List<Integer> setPage = new ArrayList<>();
    private int count = 0, page = 0, devicetitle = 0, writter = 0;

    public void setList(Context context, List<String> pdftitle, List<Integer> device_count, ArrayList<List<String>> recordList){
        this.context = context;
        try {
            String address = "";
            int getcount = 0;
            setColumn.clear();
            setPage.clear();

            for(int i = 0; i < recordList.size(); i++){
                List<String> dataList = new ArrayList<>();
                dataList.clear();
                dataList = recordList.get(i);
                if(address.matches("")){
                    address = dataList.get(0);
                    JSONArray jsonArray = new JSONArray(dataList.get(3));
                    for(int j = 0; j < jsonArray.length(); j = j + 4){
                        count++;
                    }
                    if(count <= 3){
                        if(device_count.get(getcount) % 100 == 0){
                            devicetitle = device_count.get(getcount) / 100;
                        }
                        else {
                            devicetitle = (device_count.get(getcount) / 100) + 1;
                        }
                        getcount++;
                        setPage.add(devicetitle);
                    }
                    else {
                        if(device_count.get(getcount) % 50 == 0){
                            devicetitle = device_count.get(getcount) / 50;
                        }
                        else {
                            devicetitle = (device_count.get(getcount) / 50) + 1;
                        }
                        getcount++;
                        setPage.add(devicetitle);
                    }
                    setColumn.add(count);
                    count = 0;
                }
                else {
                    if(!dataList.get(0).matches(address)){
                        address = dataList.get(0);
                        JSONArray jsonArray = new JSONArray(dataList.get(3));
                        for(int j = 0; j < jsonArray.length(); j = j + 4){
                            count++;
                        }
                        if(count <= 3){
                            if(device_count.get(getcount) % 100 == 0){
                                devicetitle = device_count.get(getcount) / 100;
                            }
                            else {
                                devicetitle = (device_count.get(getcount) / 100) + 1;
                            }
                            getcount++;
                            setPage.add(devicetitle);
                        }
                        else {
                            if(device_count.get(getcount) % 50 == 0){
                                devicetitle = device_count.get(getcount) / 50;
                            }
                            else {
                                devicetitle = (device_count.get(getcount) / 50) + 1;
                            }
                            getcount++;
                            setPage.add(devicetitle);
                        }
                        setColumn.add(count);
                        count = 0;
                    }
                }
            }
            logMessage.showmessage(TAG,"setColumn = " + setColumn);
            logMessage.showmessage(TAG,"setPage = " + setPage);
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }


    public PdfPTable createTable(Document document) throws IOException, DocumentException {

        String a = "", b = "", c = "";
        float fntSize, lineSpacing;
        fntSize = 6.0f;
        lineSpacing = 10f;
        /*if (count == 1) {
            PdfPTable table = new PdfPTable((count + 2) * 4);
            PdfPCell cell, cell1;

            if (Value.name.get(0).toString().matches("I")) {
                a = context.getString(R.string.pdf1st);
            } else if (Value.name.get(0).toString().matches("T")) {
                a = context.getString(R.string.pdfT);
            } else if (Value.name.get(0).toString().matches("H")) {
                a = context.getString(R.string.pdfH);
            } else if (Value.name.get(0).toString().matches("C")) {
                a = context.getString(R.string.pdfC);
            }

            for (int i = 0; i < page; i++) {
                for(int j = 0; j < 4; j++) {
                    cell = new PdfPCell(new Paragraph(new Phrase(lineSpacing,context.getString(R.string.pdftime),
                            FontFactory.getFont(FontFactory.HELVETICA_BOLD, fntSize))));
                    cell.setColspan(2);
                    cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
                    cell.setHorizontalAlignment(PdfPCell.ALIGN_RIGHT);
                    table.addCell(cell);

                    cell1 = new PdfPCell(new Paragraph(new Phrase(lineSpacing, a, FontFactory
                            .getFont(FontFactory.HELVETICA_BOLD, fntSize))));
                    cell1.setHorizontalAlignment(PdfPCell.ALIGN_RIGHT);
                    table.addCell(cell1);
                }

                for (int k = (300 * i); k < 75 + (300 * i); k++) {
                    for (int l = 0; l < 4; l++) {
                        if (k % 75 == 0) {
                            if ((k + (l * 75)) < Charttime.size()) {
                                cell = new PdfPCell(new Paragraph(new Phrase(lineSpacing, Charttime.get((k + (l * 75))).substring(3,Charttime.get((k + (l * 75))).length()),
                                        FontFactory.getFont(FontFactory.HELVETICA, fntSize))));
                                cell.setColspan(2);
                                cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
                                cell.setHorizontalAlignment(PdfPCell.ALIGN_RIGHT);
                                table.addCell(cell);
                            } else {
                                cell = new PdfPCell(new Paragraph(new Phrase(lineSpacing, "",
                                        FontFactory.getFont(FontFactory.HELVETICA, fntSize))));
                                cell.setColspan(2);
                                cell.setHorizontalAlignment(PdfPCell.ALIGN_RIGHT);
                                table.addCell(cell);
                            }
                        } else {
                            if ((k + (l * 75)) < Charttime.size()) {
                                cell = new PdfPCell(new Paragraph(new Phrase(lineSpacing, Charttime.get((k + (l * 75))).substring(3,Charttime.get((k + (l * 75))).length()),
                                        FontFactory.getFont(FontFactory.HELVETICA, fntSize))));
                                cell.setColspan(2);
                                cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
                                cell.setHorizontalAlignment(PdfPCell.ALIGN_RIGHT);
                                table.addCell(cell);
                            } else {
                                cell = new PdfPCell(new Paragraph(new Phrase(lineSpacing, "",
                                        FontFactory.getFont(FontFactory.HELVETICA, fntSize))));
                                cell.setColspan(2);
                                cell.setHorizontalAlignment(PdfPCell.ALIGN_RIGHT);
                                table.addCell(cell);
                            }
                        }
                        if ((k + (l * 75)) < Firstlist.size()) {
                            cell1 = new PdfPCell(new Paragraph(new Phrase(lineSpacing, Firstlist.get((k + (l * 75))),
                                    FontFactory.getFont(FontFactory.HELVETICA, fntSize))));
                            cell1.setHorizontalAlignment(PdfPCell.ALIGN_RIGHT);
                            table.addCell(cell1);
                        } else {
                            cell1 = new PdfPCell(new Paragraph(new Phrase(lineSpacing,"",
                                    FontFactory.getFont(FontFactory.HELVETICA, fntSize))));
                            cell1.setHorizontalAlignment(PdfPCell.ALIGN_RIGHT);
                            table.addCell(cell1);
                        }
                    }
                }
            }
            return table;
        }
        else if (count == 2) {
            PdfPTable table = new PdfPTable((count + 2) * 4);
            PdfPCell cell, cell1, cell2;

            if (Value.name.get(0).toString().matches("I")) {
                a = context.getString(R.string.pdf1st);
            } else if (Value.name.get(0).toString().matches("T")) {
                a = context.getString(R.string.pdfT);
            } else if (Value.name.get(0).toString().matches("H")) {
                a = context.getString(R.string.pdfH);
            } else if (Value.name.get(0).toString().matches("C")) {
                a = context.getString(R.string.pdfC);
            }

            if (Value.name.get(1).toString().matches("I")) {
                b = context.getString(R.string.pdf2nd);
            } else if (Value.name.get(1).toString().matches("T")) {
                b = context.getString(R.string.pdfT);
            } else if (Value.name.get(1).toString().matches("H")) {
                b = context.getString(R.string.pdfH);
            } else if (Value.name.get(1).toString().matches("C")) {
                b = context.getString(R.string.pdfC);
            }

            for (int i = 0; i < page; i++) {
                for(int j = 0; j < 4; j++) {
                    cell = new PdfPCell(new Paragraph(new Phrase(lineSpacing,context.getString(R.string.pdftime),
                            FontFactory.getFont(FontFactory.HELVETICA_BOLD, fntSize))));
                    cell.setColspan(2);
                    cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
                    cell.setHorizontalAlignment(PdfPCell.ALIGN_RIGHT);
                    table.addCell(cell);

                    cell1 = new PdfPCell(new Paragraph(new Phrase(lineSpacing, a, FontFactory
                            .getFont(FontFactory.HELVETICA_BOLD, fntSize))));
                    cell1.setHorizontalAlignment(PdfPCell.ALIGN_RIGHT);
                    table.addCell(cell1);

                    cell2 = new PdfPCell(new Paragraph(new Phrase(lineSpacing, b, FontFactory
                            .getFont(FontFactory.HELVETICA_BOLD, fntSize))));
                    cell2.setHorizontalAlignment(PdfPCell.ALIGN_RIGHT);
                    table.addCell(cell2);
                }

                for (int k = (300 * i); k < 75 + (300 * i); k++) {
                    for (int l = 0; l < 4; l++) {
                        if (k % 75 == 0) {
                            if ((k + (l * 75)) < Charttime.size()) {
                                cell = new PdfPCell(new Paragraph(new Phrase(lineSpacing, Charttime.get((k + (l * 75))).substring(3,Charttime.get((k + (l * 75))).length()),
                                        FontFactory.getFont(FontFactory.HELVETICA, fntSize))));
                                cell.setColspan(2);
                                cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
                                cell.setHorizontalAlignment(PdfPCell.ALIGN_RIGHT);
                                table.addCell(cell);
                            } else {
                                cell = new PdfPCell(new Paragraph(new Phrase(lineSpacing, "",
                                        FontFactory.getFont(FontFactory.HELVETICA, fntSize))));
                                cell.setColspan(2);
                                cell.setHorizontalAlignment(PdfPCell.ALIGN_RIGHT);
                                table.addCell(cell);
                            }
                        } else {
                            if ((k + (l * 75)) < Charttime.size()) {
                                cell = new PdfPCell(new Paragraph(new Phrase(lineSpacing, Charttime.get((k + (l * 75))).substring(3,Charttime.get((k + (l * 75))).length()),
                                        FontFactory.getFont(FontFactory.HELVETICA, fntSize))));
                                cell.setColspan(2);
                                cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
                                cell.setHorizontalAlignment(PdfPCell.ALIGN_RIGHT);
                                table.addCell(cell);
                            } else {
                                cell = new PdfPCell(new Paragraph(new Phrase(lineSpacing, "",
                                        FontFactory.getFont(FontFactory.HELVETICA, fntSize))));
                                cell.setColspan(2);
                                cell.setHorizontalAlignment(PdfPCell.ALIGN_RIGHT);
                                table.addCell(cell);
                            }
                        }
                        if ((k + (l * 75)) < Firstlist.size()) {
                            cell1 = new PdfPCell(new Paragraph(new Phrase(lineSpacing, Firstlist.get((k + (l * 75))),
                                    FontFactory.getFont(FontFactory.HELVETICA, fntSize))));
                            cell1.setHorizontalAlignment(PdfPCell.ALIGN_RIGHT);
                            table.addCell(cell1);
                        } else {
                            cell1 = new PdfPCell(new Paragraph(new Phrase(lineSpacing,"",
                                    FontFactory.getFont(FontFactory.HELVETICA, fntSize))));
                            cell1.setHorizontalAlignment(PdfPCell.ALIGN_RIGHT);
                            table.addCell(cell1);
                        }
                        if ((k + (l * 75)) < Secondlist.size()) {
                            cell2 = new PdfPCell(new Paragraph(new Phrase(lineSpacing, Secondlist.get((k + (l * 75))),
                                    FontFactory.getFont(FontFactory.HELVETICA, fntSize))));
                            cell2.setHorizontalAlignment(PdfPCell.ALIGN_RIGHT);
                            table.addCell(cell2);
                        } else {
                            cell2 = new PdfPCell(new Paragraph(new Phrase(lineSpacing, "",
                                    FontFactory.getFont(FontFactory.HELVETICA, fntSize))));
                            cell2.setHorizontalAlignment(PdfPCell.ALIGN_RIGHT);
                            table.addCell(cell2);
                        }
                    }
                }
            }
            return table;
        }
        else if (count == 3) {
            PdfPTable table = new PdfPTable((count + 2) * 4);
            PdfPCell cell, cell1, cell2, cell3;

            if (Value.name.get(0).toString().matches("I")) {
                a = context.getString(R.string.pdf1st);
            } else if (Value.name.get(0).toString().matches("T")) {
                a = context.getString(R.string.pdfT);
            } else if (Value.name.get(0).toString().matches("H")) {
                a = context.getString(R.string.pdfH);
            } else if (Value.name.get(0).toString().matches("C")) {
                a = context.getString(R.string.pdfC);
            }

            if (Value.name.get(1).toString().matches("I")) {
                b = context.getString(R.string.pdf2nd);
            } else if (Value.name.get(1).toString().matches("T")) {
                b = context.getString(R.string.pdfT);
            } else if (Value.name.get(1).toString().matches("H")) {
                b = context.getString(R.string.pdfH);
            } else if (Value.name.get(1).toString().matches("C")) {
                b = context.getString(R.string.pdfC);
            }

            if (Value.name.get(2).toString().matches("I")) {
                c = context.getString(R.string.pdf3rd);
            } else if (Value.name.get(2).toString().matches("T")) {
                c = context.getString(R.string.pdfT);
            } else if (Value.name.get(2).toString().matches("H")) {
                c = context.getString(R.string.pdfH);
            } else if (Value.name.get(2).toString().matches("C")) {
                c = context.getString(R.string.pdfC);
            }

            for (int i = 0; i < page; i++) {
                for(int j = 0; j < 4; j++) {
                    cell = new PdfPCell(new Paragraph(new Phrase(lineSpacing,context.getString(R.string.pdftime),
                            FontFactory.getFont(FontFactory.HELVETICA_BOLD, fntSize))));
                    cell.setColspan(2);
                    cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
                    cell.setHorizontalAlignment(PdfPCell.ALIGN_RIGHT);
                    table.addCell(cell);

                    cell1 = new PdfPCell(new Paragraph(new Phrase(lineSpacing, a, FontFactory
                            .getFont(FontFactory.HELVETICA_BOLD, fntSize))));
                    cell1.setHorizontalAlignment(PdfPCell.ALIGN_RIGHT);
                    table.addCell(cell1);

                    cell2 = new PdfPCell(new Paragraph(new Phrase(lineSpacing, b, FontFactory
                            .getFont(FontFactory.HELVETICA_BOLD, fntSize))));
                    cell2.setHorizontalAlignment(PdfPCell.ALIGN_RIGHT);
                    table.addCell(cell2);

                    cell3 = new PdfPCell(new Paragraph(new Phrase(lineSpacing, c, FontFactory
                            .getFont(FontFactory.HELVETICA_BOLD, fntSize))));
                    cell3.setHorizontalAlignment(PdfPCell.ALIGN_RIGHT);
                    table.addCell(cell3);
                }

                for (int k = (300 * i); k < 75 + (300 * i); k++) {
                    for (int l = 0; l < 4; l++) {
                        if (k % 75 == 0) {
                            if ((k + (l * 75)) < Charttime.size()) {
                                cell = new PdfPCell(new Paragraph(new Phrase(lineSpacing, Charttime.get((k + (l * 75))).substring(3,Charttime.get((k + (l * 75))).length()),
                                        FontFactory.getFont(FontFactory.HELVETICA, fntSize))));
                                cell.setColspan(2);
                                cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
                                cell.setHorizontalAlignment(PdfPCell.ALIGN_RIGHT);
                                table.addCell(cell);
                            } else {
                                cell = new PdfPCell(new Paragraph(new Phrase(lineSpacing, "",
                                        FontFactory.getFont(FontFactory.HELVETICA, fntSize))));
                                cell.setColspan(2);
                                cell.setHorizontalAlignment(PdfPCell.ALIGN_RIGHT);
                                table.addCell(cell);
                            }
                        } else {
                            if ((k + (l * 75)) < Charttime.size()) {
                                cell = new PdfPCell(new Paragraph(new Phrase(lineSpacing, Charttime.get((k + (l * 75))).substring(3,Charttime.get((k + (l * 75))).length()),
                                        FontFactory.getFont(FontFactory.HELVETICA, fntSize))));
                                cell.setColspan(2);
                                cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
                                cell.setHorizontalAlignment(PdfPCell.ALIGN_RIGHT);
                                table.addCell(cell);
                            } else {
                                cell = new PdfPCell(new Paragraph(new Phrase(lineSpacing, "",
                                        FontFactory.getFont(FontFactory.HELVETICA, fntSize))));
                                cell.setColspan(2);
                                cell.setHorizontalAlignment(PdfPCell.ALIGN_RIGHT);
                                table.addCell(cell);
                            }
                        }
                        if ((k + (l * 75)) < Firstlist.size()) {
                            cell1 = new PdfPCell(new Paragraph(new Phrase(lineSpacing, Firstlist.get((k + (l * 75))),
                                    FontFactory.getFont(FontFactory.HELVETICA, fntSize))));
                            cell1.setHorizontalAlignment(PdfPCell.ALIGN_RIGHT);
                            table.addCell(cell1);
                        } else {
                            cell1 = new PdfPCell(new Paragraph(new Phrase(lineSpacing,"",
                                    FontFactory.getFont(FontFactory.HELVETICA, fntSize))));
                            cell1.setHorizontalAlignment(PdfPCell.ALIGN_RIGHT);
                            table.addCell(cell1);
                        }
                        if ((k + (l * 75)) < Secondlist.size()) {
                            cell2 = new PdfPCell(new Paragraph(new Phrase(lineSpacing, Secondlist.get((k + (l * 75))),
                                    FontFactory.getFont(FontFactory.HELVETICA, fntSize))));
                            cell2.setHorizontalAlignment(PdfPCell.ALIGN_RIGHT);
                            table.addCell(cell2);
                        } else {
                            cell2 = new PdfPCell(new Paragraph(new Phrase(lineSpacing, "",
                                    FontFactory.getFont(FontFactory.HELVETICA, fntSize))));
                            cell2.setHorizontalAlignment(PdfPCell.ALIGN_RIGHT);
                            table.addCell(cell2);
                        }
                        if ((k + (l * 75)) < Thirdlist.size()) {
                            cell3 = new PdfPCell(new Paragraph(new Phrase(lineSpacing, Thirdlist.get((k + (l * 75))),
                                    FontFactory.getFont(FontFactory.HELVETICA, fntSize))));
                            cell3.setHorizontalAlignment(PdfPCell.ALIGN_RIGHT);
                            table.addCell(cell3);
                        } else {
                            cell3 = new PdfPCell(new Paragraph(new Phrase(lineSpacing, "",
                                    FontFactory.getFont(FontFactory.HELVETICA, fntSize))));
                            cell3.setHorizontalAlignment(PdfPCell.ALIGN_RIGHT);
                            table.addCell(cell3);
                        }
                    }
                }
            }
            return table;
        }*/
        /*else*/
            return null;
    }
}
