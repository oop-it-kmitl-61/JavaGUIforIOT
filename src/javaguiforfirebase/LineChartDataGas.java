/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaguiforfirebase;

/**
 *
 * @author Paoyimpae
 */
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import org.jfree.chart.*;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.*;

public class LineChartDataGas extends JFrame implements ChangeListener {
   
    JScrollBar scroller;
    SlidingCategoryDataset t;
    int dataCount = 0;

   public LineChartDataGas( String applicationTitle , String chartTitle ) {
      super(applicationTitle);
      
      try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(LineChartDataTemp.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(LineChartDataTemp.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(LineChartDataTemp.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnsupportedLookAndFeelException ex) {
            Logger.getLogger(LineChartDataTemp.class.getName()).log(Level.SEVERE, null, ex);
        }
      
      this.setLayout(new BorderLayout());
      this.getContentPane().setBackground(new java.awt.Color(34, 34, 34));
      this.setSize(850, 650);
      
      t = createDataset();
      
      JFreeChart lineChart = ChartFactory.createLineChart(
         chartTitle,
         "Date (YYYYMMDD)","Average in Date (PPM)",
         t,
         PlotOrientation.VERTICAL,
         true,true,false);
      lineChart.setBackgroundPaint(new java.awt.Color(34, 34, 34));
      lineChart.getTitle().setPaint(new java.awt.Color(255, 255, 255));
      lineChart.getTitle().setFont(new java.awt.Font("RSU", 0, 24));
      lineChart.getPlot().setBackgroundPaint(new java.awt.Color(100, 100, 100));
      lineChart.getCategoryPlot().getRenderer().setBaseItemLabelPaint(new java.awt.Color(0, 0, 255));
      lineChart.getCategoryPlot().getDomainAxis().setLabelPaint(new java.awt.Color(255, 255, 255));
      lineChart.getCategoryPlot().getDomainAxis().setLabelFont(new java.awt.Font("RSU", 0, 18));
      lineChart.getCategoryPlot().getDomainAxis().setTickLabelPaint(new java.awt.Color(255, 255, 255));
      lineChart.getCategoryPlot().getDomainAxis().setTickLabelFont(new java.awt.Font("RSU", 0, 18));
      lineChart.getCategoryPlot().getRangeAxis().setLabelPaint(new java.awt.Color(255, 255, 255));
      lineChart.getCategoryPlot().getRangeAxis().setLabelFont(new java.awt.Font("RSU", 0, 18));
      lineChart.getCategoryPlot().getRangeAxis().setTickLabelPaint(new java.awt.Color(255, 255, 255));
      lineChart.getCategoryPlot().getRangeAxis().setTickLabelFont(new java.awt.Font("RSU", 0, 18));
      lineChart.getCategoryPlot().getRenderer().setSeriesPaint(0, new java.awt.Color(126, 152, 248));
      /* lineChart.s
      lineChart.setFont(new java.awt.Font("RSU", 0, 18)); // NOI18N
      lineChart.setForeground(new java.awt.Color(255, 255, 255)); */
     
      
      ChartPanel chartPanel = new ChartPanel( lineChart );
      chartPanel.setPreferredSize( new java.awt.Dimension( 828 , 550 ) );
      chartPanel.setBackground( Color.RED );
      /* setContentPane( chartPanel ); */
      this.add(chartPanel, BorderLayout.CENTER);
      
      if (dataCount > 5){
        this.scroller = new JScrollBar(SwingConstants.HORIZONTAL, 0, 5, 0, dataCount);
        this.scroller.getModel().addChangeListener(this);
        this.add(this.scroller, BorderLayout.PAGE_END);
      }
      
      this.setVisible(true);
   }

   private SlidingCategoryDataset createDataset( ) {
      DefaultCategoryDataset tempo = new DefaultCategoryDataset( );
      // SecurityUtil.setUp();
      DataServices.initialSmokeData();
      DataServices.waiting();
      for (HistoryData data: DataServices.getSmokeData()) {
          tempo.addValue( data.getAverage(), "Smoke", data.getDate() );
      }
      DataServices.turnOff();
      dataCount = DataServices.getSmokeData().size();
      SlidingCategoryDataset dataset2 = new SlidingCategoryDataset(tempo, 0, 5);
      return dataset2;
   }
   
   public static void main( String[ ] args ) {
      LineChartDataGas chart = new LineChartDataGas(
         "Smoke Average Value Analysis" ,
         "Summary the Average Value of Smoke");
     
   }

    @Override
    public void stateChanged(ChangeEvent e) {
        this.t.setFirstCategoryIndex(this.scroller.getValue());
    }


}
