package com.github.appreciated.app.layout.component;

import com.github.appreciated.app.layout.builder.Provider;
import com.github.appreciated.app.layout.builder.entities.NotificationHolder;
import com.github.appreciated.app.layout.builder.window.NotificationWindow;
import com.vaadin.ui.Button;
import com.vaadin.ui.Component;
import com.vaadin.ui.UI;

import static com.vaadin.icons.VaadinIcons.BELL;

public class NotificationAppBarButton extends AppBarBadgeButton {

    private UI ui;
    private NotificationWindow window;

    Provider<NotificationWindow, NotificationHolder> provider;

    public NotificationAppBarButton(NotificationHolder holder) {
        this(holder, info -> new NotificationWindow(info));
    }

    public NotificationAppBarButton(NotificationHolder holder, Provider<NotificationWindow, NotificationHolder> windowProvider) {
        super(BELL, holder);
        provider = windowProvider;
        getButton().addClickListener((Button.ClickListener) this::buttonClick);
    }

    @Override
    public void attach() {
        super.attach();
        ui = getUI();
    }

    private void buttonClick(Button.ClickEvent clickEvent) {
        if (window == null) {
            ui.access(() -> {
                window = provider.getComponent(getNotificationHolder());
                window.show(clickEvent);
                window.addCloseListener(closeEvent -> {
                    new Thread(() -> {
                        try {
                            Thread.sleep(200);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        window = null;
                    }).start();
                });
            });
        } else {
            window = null;
        }
    }

    public void refreshNotifications(NotificationHolder notificationHolder, Component component) {
        getUI().access(() -> {
            super.refreshNotifications(notificationHolder, component);
            if (window != null) {
                window.addNewNotification(component);
            }
        });
    }
}
