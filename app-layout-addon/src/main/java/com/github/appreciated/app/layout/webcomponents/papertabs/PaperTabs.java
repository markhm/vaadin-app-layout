package com.github.appreciated.app.layout.webcomponents.papertabs;


import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.HasComponents;
import com.vaadin.flow.component.HasSize;
import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.dependency.HtmlImport;

@Tag("paper-tabs")
@HtmlImport("bower_components/paper-tabs/paper-tabs.html")
public class PaperTabs extends Component implements HasComponents, HasSize {

    public void setScrollable(boolean scrollable) {
        getElement().setAttribute("scrollable", scrollable);
    }

    public void setFitContainer(boolean fit) {
        getElement().setAttribute("fit-container", fit);
    }

    public void setSelected(int i) {
        getElement().setAttribute("selected", String.valueOf(i));
    }
}
