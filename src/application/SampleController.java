package application;

import java.io.File;
import java.util.Arrays;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.chart.PieChart;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuItem;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;

public class SampleController {
	
	Hashtable<String,  Hashtable<String, Integer>> dadosCursos = new Hashtable<>();

    @FXML
    private MenuItem fileClose;

    @FXML
    private MenuItem fileOpen;

    @FXML
    private ListView<String> listView;

    @FXML
    private PieChart pieChart;

    /**
     * Método de encerramento do programa
     * 
     * @param event
     */
    @FXML
    void onFileClose(ActionEvent event) {
    	Platform.exit();
    	System.exit(0);
    }

    /**
     * Método para abertura do arquivo que contém os dados do Sisu
     * 
     * @param event
     */
    @FXML
    void onFileOpen(ActionEvent event) {
    	FileChooser fileChooser = new FileChooser();
    	File file = fileChooser.showOpenDialog(null);
    	dadosCursos = Candidato.parserListaSisu(file);
    	String cursos [] = dadosCursos.keySet().toArray(new String[0]);
    	Arrays.sort(cursos);
    	listView.getItems().clear();
    	for(String item : cursos) {
    		listView.getItems().add(item);
    	}
    }

    /**
     * Método para criação do gráfico toda vez que um curso da lista for selecionado 
     * 
     * @param event
     */
    @FXML
    void onMouseClickedLV(MouseEvent event) {
    	String curso = listView.getSelectionModel().getSelectedItem();
    	
    	Set<String> chaves = dadosCursos.get(curso).keySet();    	
    	
    	pieChart.setTitle(curso);
    	ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList();
    	for (String chave : chaves){
    		if(chave != null){
    	    	pieChartData.add(new PieChart.Data(chave, dadosCursos.get(curso).get(chave)));
    	    }
    	}
    	pieChart.setData(pieChartData);
    	pieChart.setStartAngle(90);
    }
    

}
