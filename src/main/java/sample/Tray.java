package sample;

import javax.imageio.ImageIO;
import java.awt.*;

public class Tray {
  public void setupTray() {
    final PopupMenu popup = new PopupMenu();

    try {
      final TrayIcon trayIcon = new TrayIcon(ImageIO.read(new ResourceManager().getAppIcon()).getScaledInstance(16, 16, Image.SCALE_DEFAULT));

      final SystemTray tray = SystemTray.getSystemTray();

      CheckboxMenuItem cb1 = new CheckboxMenuItem("Run on startup");

      CheckboxMenuItem alwaysOnTopCheckBoxMenuItem = new CheckboxMenuItem("Always on top");
      alwaysOnTopCheckBoxMenuItem.setState(new PropertiesManager().getAlwaysOnTopProperty());
      alwaysOnTopCheckBoxMenuItem.addItemListener(itemListener -> {
        boolean alwaysOnTopCheckBoxMenuItemState = ((CheckboxMenuItem)itemListener.getSource()).getState();
        try {
          new PropertiesManager().updateProperty(PropertiesManager.Property.isAlwaysOnTop, alwaysOnTopCheckBoxMenuItemState);
        } catch (Exception e) {
          e.printStackTrace();
        }
      });

      MenuItem exitItem = new MenuItem("Exit");
      exitItem.addActionListener(eventListener -> System.exit(0));

      popup.add(cb1);
      popup.add(alwaysOnTopCheckBoxMenuItem);
      popup.addSeparator();
      popup.add(exitItem);

      trayIcon.setPopupMenu(popup);

      tray.add(trayIcon);
    } catch (Exception e) {
      System.out.println("TrayIcon could not be added.");
    }
  }
}
