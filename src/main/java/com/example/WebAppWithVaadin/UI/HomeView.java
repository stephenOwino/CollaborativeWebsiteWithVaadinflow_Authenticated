package com.example.WebAppWithVaadin.UI;

import com.vaadin.flow.component.cookieconsent.CookieConsent;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.auth.AnonymousAllowed;


@Route("/")
@AnonymousAllowed
public class HomeView extends VerticalLayout {
    public HomeView() {
        add(
                new H1("Vaadin Flow Framework!!"),
               new CookieConsent()
        );
    }
}
