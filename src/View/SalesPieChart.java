
package View;

import java.sql.Connection;
   import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.chart.ChartPanel;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JFrame;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.plot.PlotOrientation;
public class SalesPieChart {
      
    void loadChartp() {
        // Database connection parameters
        String jdbcURL = "jdbc:mysql://localhost:3306/applestore-02";
        String username = "root";
        String password = "";

       DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        try {
            // Establish the database connection
            Connection connection = DriverManager.getConnection(jdbcURL, username, password);
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT product_name, SUM(sells_quantity) as total_quantity FROM sells_details GROUP BY product_name");

            while (resultSet.next()) {
                String product = resultSet.getString("product_name");
                int totalQuantity = resultSet.getInt("total_quantity");
              dataset.addValue(totalQuantity, "sells_details", product);
            }

            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Create the pie chart
      JFreeChart chart = ChartFactory.createBarChart(
                "Combined Product Sales Bar Chart",
                "Product",
                "Total Quantity",
                dataset,
                PlotOrientation.VERTICAL,
                true,
                true,
                false
        );

      // Display the pie chart in a Java Swing application
        ChartPanel chartPanel = new ChartPanel(chart);
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(chartPanel);
        frame.pack();
        frame.setVisible(true);
    }
}
