package com.example.WebAppWithVaadin.UI;

import com.example.WebAppWithVaadin.Backend.Book;
import com.example.WebAppWithVaadin.Backend.BookService;
import com.vaadin.collaborationengine.CollaborationBinder;
import com.vaadin.collaborationengine.CollaborationMessageInput;
import com.vaadin.collaborationengine.CollaborationMessageList;
import com.vaadin.collaborationengine.UserInfo;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.IntegerField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import jakarta.annotation.security.RolesAllowed;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;


@Route("new")
@RolesAllowed("ADMIN")
public class NewView extends VerticalLayout {

    private TextField tittle = new TextField("Tittle");
    private DatePicker published = new DatePicker("Published");
    private IntegerField rating = new IntegerField("Rating");

    public NewView(BookService bookService) {

       UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

       String username = userDetails.getUsername();
        var userInfo = new UserInfo(username,username);

        var binder = new CollaborationBinder<>(Book.class,userInfo);
        binder.bindInstanceFields(this);

        binder.setTopic("new-book",()->new Book());

        var messageList = new CollaborationMessageList(userInfo,"new-book");
        add(
                new HorizontalLayout(
                        new VerticalLayout(
                                new H1("New Book"),
                                new FormLayout(tittle,published,rating),
                                new Button("Save",event-> {
                                    var book = new Book();
                                    binder.writeBeanIfValid(book);
                                    bookService.add(book);
                                    Notification.show("Book saved.");
                                    binder.reset(new Book());

                                })
                ),
                        new VerticalLayout(
                                messageList,
                                new CollaborationMessageInput(messageList)

                        )


                        )
        );

    }
}

