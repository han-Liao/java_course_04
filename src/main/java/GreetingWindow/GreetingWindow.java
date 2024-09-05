package GreetingWindow;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingUtilities;

public class GreetingWindow {

    public static void main(String[] args) {
        // 确保 GUI 更新在事件分发线程中进行
        SwingUtilities.invokeLater(() -> {
            // 创建并显示 GUI
            createAndShowGUI();
        });
    }

    private static void createAndShowGUI() {
        // 创建一个新的 JFrame 实例
        JFrame frame = new JFrame("Greeting Window");

        // 创建一个 JLabel 显示打招呼的消息
        JLabel label = new JLabel("Hello, World!", JLabel.CENTER);

        // 将标签添加到框架的内容面板
        frame.getContentPane().add(label);

        // 设置默认关闭操作
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // 设置框架的大小
        frame.setSize(200, 100);

        // 设置框架的可见性
        frame.setVisible(true);
    }
}
