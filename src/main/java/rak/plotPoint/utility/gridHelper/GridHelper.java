package rak.plotPoint.utility.gridHelper;

import java.util.List;
import java.util.logging.Logger;

import javafx.scene.Node;
import javafx.scene.layout.GridPane;

public class GridHelper <Strategy extends RowStrategy<RowParam>, RowParam extends RowParameter>{
	private static final int NUMBER_OF_HEADER_ROWS = 1;
	private static final Logger LOG = Logger.getLogger(GridHelper.class.getName()); 
	private GridPane grid;
	private Strategy strategy;
	
	public GridHelper(GridPane grid, Strategy strategy){
		this.grid = grid;
		this.strategy = strategy;
	}
	
	public void refreshList(List<RowParam> params){
		if (grid != null){
			clearAllRowsButHeader();
			addRows(params);
		}
	}
	
	private void clearAllRowsButHeader() {
		for (int i=grid.getChildren().size()-1; i>0; i--){
			Node child = grid.getChildren().get(i);
			if (isBodyRow(child)){
				grid.getChildren().remove(i);
			}
		}
	}
	
	private boolean isBodyRow(Node child){
		Integer row = GridPane.getRowIndex(child);
		return row != null && row > NUMBER_OF_HEADER_ROWS-1;
	}

	private void addRows(List<RowParam> params) {
		for (int i=0; i<params.size(); i++){
			RowParam param = params.get(i);
			int row = i + NUMBER_OF_HEADER_ROWS;
			addRow(row, param);
		}
	}

	private void addRow(int row, RowParam param) {
		Node node = strategy.getNode(param);
			
		GridPane.setRowIndex(node, row);
		GridPane.setColumnIndex(node, 0);
		grid.getChildren().add(node);
	}

}
