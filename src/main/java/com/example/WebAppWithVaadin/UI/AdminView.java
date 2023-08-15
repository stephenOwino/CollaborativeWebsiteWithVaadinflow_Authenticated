package com.example.WebAppWithVaadin.UI;

import com.example.WebAppWithVaadin.Backend.Book;
import com.example.WebAppWithVaadin.Backend.BookService;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouterLink;
import jakarta.annotation.security.RolesAllowed;
import org.vaadin.crudui.crud.impl.GridCrud;

@Route("admin")
@RolesAllowed("ADMIN")
public class AdminView extends VerticalLayout {

    public AdminView(BookService bookService) {
        var crud = new GridCrud<Book>(Book.class,bookService);
        crud.getGrid().setColumns("tittle","published","rating");
        crud.getCrudFormFactory().setVisibleProperties("tittle","published","rating");
        crud.setAddOperationVisible(false);
        crud.getCrudLayout().addToolbarComponent(new RouterLink("New Book",NewView.class));
        add(
                new H1("Admin View"),
                crud
        );
    }
}
