package com.fangxuele.tool.push;

import cn.hutool.core.thread.ThreadUtil;
import com.fangxuele.tool.push.ui.Init;
import com.fangxuele.tool.push.ui.UiConsts;
import com.fangxuele.tool.push.ui.dialog.AboutDialog;
import com.fangxuele.tool.push.ui.dialog.SettingDialog;
import com.fangxuele.tool.push.ui.form.LoadingForm;
import com.fangxuele.tool.push.ui.form.MainWindow;
import com.fangxuele.tool.push.ui.frame.MainFrame;
import com.fangxuele.tool.push.ui.listener.TaskListener;
import com.fangxuele.tool.push.util.ConfigUtil;
import com.fangxuele.tool.push.util.MybatisUtil;
import com.fangxuele.tool.push.util.UpgradeUtil;
import com.formdev.flatlaf.extras.FlatDesktop;
import com.formdev.flatlaf.util.SystemInfo;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.ibatis.session.SqlSession;

import javax.swing.*;
import java.awt.*;

/**
 * <pre>
 * Main Enter!
 * </pre>
 *
 * @author <a href="https://github.com/rememberber">RememBerBer</a>
 * @since 2019/4/20.
 */
@Slf4j
public class App {
    public static MainFrame mainFrame;

    public static ConfigUtil config = ConfigUtil.getInstance();

    public static SqlSession sqlSession = MybatisUtil.getSqlSession();

    public static SystemTray tray;

    public static TrayIcon trayIcon;

    public static void main(String[] args) {
        if (SystemInfo.isMacOS) {
//            java -Xdock:name="WePush" -Xdock:icon=WePush.jpg ... (whatever else you normally specify here)
//            java -Xms64m -Xmx256m -Dapple.awt.application.name="WePush" -Dcom.apple.mrj.application.apple.menu.about.name="WePush" -cp "./lib/*" com.luoboduner.moo.tool.App
            System.setProperty("apple.laf.useScreenMenuBar", "true");
            System.setProperty("apple.awt.application.name", UiConsts.APP_NAME);
            System.setProperty("com.apple.mrj.application.apple.menu.about.name", UiConsts.APP_NAME);
            System.setProperty("apple.awt.application.appearance", "system");
            System.setProperty("flatlaf.useRoundedPopupBorder", "true");

            FlatDesktop.setAboutHandler(() -> {
                try {
                    AboutDialog dialog = new AboutDialog();

                    dialog.pack();
                    dialog.setVisible(true);
                } catch (Exception e2) {
                    log.error(ExceptionUtils.getStackTrace(e2));
                }
            });
            FlatDesktop.setPreferencesHandler(() -> {
                try {
                    SettingDialog dialog = new SettingDialog();

                    dialog.pack();
                    dialog.setVisible(true);
                } catch (Exception e2) {
                    log.error(ExceptionUtils.getStackTrace(e2));
                }
            });
            FlatDesktop.setQuitHandler(FlatDesktop.QuitResponse::performQuit);

        }

        Init.initTheme();
        mainFrame = new MainFrame();
        mainFrame.init();
        JPanel loadingPanel = new LoadingForm().getLoadingPanel();
        mainFrame.add(loadingPanel);
        mainFrame.pack();
        mainFrame.setVisible(true);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        if (config.isDefaultMaxWindow() || screenSize.getWidth() <= 1366) {
            // 低分辨率下自动最大化窗口
            mainFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        }
        UpgradeUtil.smoothUpgrade();

        mainFrame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        Init.initGlobalFont();
        mainFrame.setContentPane(MainWindow.getInstance().getMainPanel());
        MainWindow.getInstance().init();
        Init.initAllTab();
        Init.initOthers();
        mainFrame.addListeners();
        mainFrame.remove(loadingPanel);
        Init.fontSizeGuide();
        Init.initTray();

        // 重启应用时把所有定时任务重新加入到任务队列
        ThreadUtil.execute(TaskListener::addAllScheduledTask);

    }
}
