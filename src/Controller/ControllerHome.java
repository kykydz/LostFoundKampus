package Controller;

public class ControllerHome {

    private final HomeViewContract view;

    public ControllerHome(HomeViewContract view) {
        this.view = view;
    }

    public void handleOpenLogin() {
        if (view == null) {
            return;
        }

        view.openLogin();
    }

    public void handleOpenRegister() {
        if (view == null) {
            return;
        }

        view.openRegister();
    }

    public void handleExit() {
        if (view == null) {
            return;
        }

        if (view.confirmExit()) {
            view.exitApplication();
        }
    }
}

