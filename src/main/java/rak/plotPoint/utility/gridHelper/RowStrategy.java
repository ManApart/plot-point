package rak.plotPoint.utility.gridHelper;

import javafx.scene.Node;

public interface RowStrategy<RowParam extends RowParameter> {

	Node getNode(RowParam param);

}
