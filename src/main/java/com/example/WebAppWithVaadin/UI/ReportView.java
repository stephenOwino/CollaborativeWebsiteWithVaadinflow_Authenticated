package com.example.WebAppWithVaadin.UI;

import com.example.WebAppWithVaadin.Backend.Book;
import com.example.WebAppWithVaadin.Backend.BookService;
import com.vaadin.flow.component.html.Anchor;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import jakarta.annotation.security.RolesAllowed;
import org.vaadin.reports.PrintPreviewReport;


@Route("report")
@RolesAllowed("ADMIN")
public class ReportView extends VerticalLayout {


    public ReportView(BookService bookService) {
       var report = new PrintPreviewReport<>(Book.class,"tittle","published","rating");
       report.setItems(bookService.findAll());
       report.getReportBuilder().setTitle("Books");

     var pdf =  report.getStreamResource("books.pdf",bookService::findAll, PrintPreviewReport.Format.PDF);
     var csv = report.getStreamResource("books.csv",bookService::findAll,PrintPreviewReport.Format.CSV);

       add(

               new HorizontalLayout(
                       new Anchor(pdf,"PDF"),
                       new Anchor(csv,"CSV")
               ),
               report
       );


    }
}
